package com.LocHai.HotelManagement.user.Service;

import com.LocHai.HotelManagement.user.dto.ThongBaoResDto;
import com.LocHai.HotelManagement.user.entity.Quyen;
import com.LocHai.HotelManagement.user.entity.TaiKhoan;
import com.LocHai.HotelManagement.user.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaiKhoanService {


    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> dangKyTaiKhoan(TaiKhoan taiKhoan) {

        if (taiKhoanRepository.existsBySoDienThoai(taiKhoan.getHoTen())) {

            return ResponseEntity.badRequest().body(new ThongBaoResDto("Tên đăng nhập đã tồn tại"));
        }

        if (taiKhoanRepository.existsByEmail(taiKhoan.getEmail())) {

            return ResponseEntity.badRequest().body(new ThongBaoResDto("Tên đăng nhập đã tồn tại"));
        }


        String encryptPassword = passwordEncoder().encode(taiKhoan.getMatKhau());
        taiKhoan.setMatKhau(encryptPassword);
//
        Quyen quyen = new Quyen();
        quyen.setTenQuyen("USER");
        List<Quyen> list = new ArrayList<>();
        list.add(quyen);
        taiKhoan.setDanhSachQuyen(list);

        // Gán và gửi thông tin kích hoạt
        // taiKhoan.setMaKichHoat(taoMaKichHoat());
//        nguoiDung.setDaKichHoat(false);

        //lưu
        taiKhoanRepository.save(taiKhoan);

        // gửi email kích hoạt
//        guiEmailKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());

        return ResponseEntity.ok("Đăng ký thành công");

    }
}
