package com.LocHai.HotelManagement.user.controller;

import com.LocHai.HotelManagement.user.config.VNPayConfig;
import com.LocHai.HotelManagement.user.dto.*;
import com.LocHai.HotelManagement.user.entity.*;
import com.LocHai.HotelManagement.user.enum2.TrangThaiPhieuThue;
import com.LocHai.HotelManagement.user.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class ThanhToanController {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PhieuThuePhongRepositoty phieuThuePhongRepositoty;

    @Autowired
    PhongRepository phongRepository;

    @Autowired
    DichVuRepository dichVuRepository;

    @Autowired
    PhieuThueDichVuRepository phieuThueDichVuRepository;

    @PostMapping("")
    public ResponseEntity<?> createPayment(HttpServletRequest req ,HttpServletResponse response, @RequestBody DatPhongRequest datPhongRequest) throws UnsupportedEncodingException {

        String orderType = "other";
        long amount = (long) datPhongRequest.getPhong().getGiaPhong()*100;
        String bankCode = req.getParameter("bankCode");

        PhieuThuePhong phieuThuePhong = new PhieuThuePhong();
        phieuThuePhong.setNgayDatPhong(new Date(System.currentTimeMillis()));
        phieuThuePhong.setNgayNhanPhong(datPhongRequest.getCheckInDate());
        phieuThuePhong.setNgayTraPhong(datPhongRequest.getCheckOutDate());
        phieuThuePhong.setTrangThaiPhieuThePhong(TrangThaiPhieuThue.DA_DAT);

        phieuThuePhong.setTongTien(datPhongRequest.getPhong().getGiaPhong());
        Integer idTaiKhoan = datPhongRequest.getIdTaiKhoan();
        TaiKhoan tk = taiKhoanRepository.findById(idTaiKhoan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
        phieuThuePhong.setTaiKhoan(tk);

        List<Phong> phongs = phongRepository.findAllById(Collections.singleton(datPhongRequest.getPhong().getIdPhong()));
        phieuThuePhong.setPhong(phongs);


        phieuThuePhongRepositoty.save(phieuThuePhong);

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = VNPayConfig.getIpAddress(req);
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_OrderType", orderType);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        PaymentResDto paymentResDto = new PaymentResDto();
        paymentResDto.setStatus("OKeee");
        paymentResDto.setMessage("Thành công");
        paymentResDto.setURL(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(paymentResDto);

    }

    @GetMapping("/payment/info")
    public void transaction(
            @RequestParam("vnp_Amount") String amount,
            @RequestParam("vnp_BankCode") String bankCode,
            @RequestParam("vnp_OrderInfo") String order,
            @RequestParam("vnp_ResponseCode") String responseCode,
            HttpServletResponse response
    ) throws IOException {
        // Encode order để tránh lỗi dấu cách và ký tự đặc biệt
        String encodedOrder = URLEncoder.encode(order, "UTF-8");

        String redirectUrl = String.format(
                "http://localhost:3000/payment/result?vnp_Amount=%s&vnp_BankCode=%s&vnp_OrderInfo=%s&vnp_ResponseCode=%s",
                amount, bankCode, encodedOrder, responseCode
        );

        response.sendRedirect(redirectUrl);
    }

    @PostMapping("/payment/dichVu")
    public ResponseEntity<?> thanhToanDichVu(@RequestBody DichVuDto dichVu) throws IOException, ThongBaoResDto {
        if (dichVu == null){
            return  ResponseEntity.badRequest().body("không có dịch vụ");

        }
        TaiKhoan taiKhoan = taiKhoanRepository.findById(dichVu.getIdTaiKhoan()).orElseThrow(() -> new ThongBaoResDto("Tài khoản không tồn tại"));
        DichVu dichVunew = dichVuRepository.findById(dichVu.getIdDichVu()).orElseThrow(() -> new ThongBaoResDto("Dịch vụ phòng không tồn tại!"));

        PhieuThueDichVu phieuThueDichVuNew = new PhieuThueDichVu();
        phieuThueDichVuNew.setDichVu(dichVunew);
        phieuThueDichVuNew.setTaiKhoan(taiKhoan);
        phieuThueDichVuNew.setTongTien(dichVunew.getGiaDichVu());
        PhieuThueDichVu phieuThueDichVu = phieuThueDichVuRepository.save(phieuThueDichVuNew);
        return ResponseEntity.ok(phieuThueDichVu);

    }





}