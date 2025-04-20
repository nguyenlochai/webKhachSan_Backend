package com.LocHai.HotelManagement.user.controller;


import com.LocHai.HotelManagement.user.Service.PhongService;

import com.LocHai.HotelManagement.user.dto.PhongDto;
import com.LocHai.HotelManagement.user.dto.ThongBaoResDto;
import com.LocHai.HotelManagement.user.entity.HinhAnh;
import com.LocHai.HotelManagement.user.entity.LoaiPhong;
import com.LocHai.HotelManagement.user.entity.Phong;

import com.LocHai.HotelManagement.user.enum2.TinhTrangPhong;
import com.LocHai.HotelManagement.user.repository.LoaiPhongRepository;
import com.LocHai.HotelManagement.user.repository.PhongRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/phong")
public class PhongController {


    private final PhongService phongService;
    private PhongRepository phongRepository;
    private LoaiPhongRepository loaiPhongRepository;

    @Autowired
    public PhongController(PhongService phongService, PhongRepository phongRepository, LoaiPhongRepository loaiPhongRepository) {
        this.phongService = phongService;
        this.phongRepository = phongRepository;
        this.loaiPhongRepository = loaiPhongRepository;
    }

    @GetMapping("/trong")
    public List<Phong> getAvailablePhong(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "sucChua", defaultValue = "1") int sucChua
    ) {
        return phongService.getAvailablePhong(startDate, endDate, sucChua);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // dịa chỉ lưu
            String uploadDir = "src/main/resources/static/images/";
            // đặt lại tên file tránh trùng lập
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            // lưu uploadDir + fileName vào path
            Path path = Paths.get(uploadDir + fileName);
            // Nếu thư mục cha (images/) chưa tồn tại → tạo mới
            Files.createDirectories(path.getParent());
            // Ghi nội dung file ảnh vào đúng path trên server (lưu)
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "images/" + fileName;
            return ResponseEntity.ok().body(Map.of(
                    "tenHinhAnh", fileName,
                    "duongDan", imageUrl
            ));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload thất bại: " + e.getMessage());
        }
    }


    @PostMapping("/themPhong")
    // phong nhận được giá, tên, sức chứa, số phòng, mô tả, loại phòng, danh sách hình ảnh (tên hình ảnh, đường dẫn)
    public ResponseEntity<?> themPhong(@RequestBody PhongDto phong) throws ThongBaoResDto {
        LoaiPhong loaiPhongInput = phong.getLoaiPhong();
        if (loaiPhongInput == null || loaiPhongInput.getIdLoaiPhong() == 0) {
            return ResponseEntity.badRequest().body(new ThongBaoResDto("Thiếu mã loại phòng!"));
        }
        LoaiPhong loaiPhong = loaiPhongRepository.findById(loaiPhongInput.getIdLoaiPhong())
                .orElseThrow(() -> new ThongBaoResDto("Loại phòng không tồn tại!"));
        Phong newRoom = new Phong();
        newRoom.setGiaPhong(phong.getGiaPhong());
        newRoom.setSoPhong(phong.getSoPhong());
        newRoom.setTenPhong(phong.getTenPhong());
        newRoom.setSucChua(phong.getSucChua());
        newRoom.setMoTa(phong.getMoTa());
        newRoom.setTinhTrangPhong(TinhTrangPhong.HOAT_DONG);
        newRoom.setLoaiPhong(loaiPhong);
        List<HinhAnh> hinhAnhs = phong.getDanhSachHinhAnh();
        if (hinhAnhs != null) {
            for (HinhAnh h : hinhAnhs) {
                h.setPhong(newRoom);
            }
            newRoom.setDanhSachHinhAnh(hinhAnhs);
        }
        Phong savedRoom = phongRepository.save(newRoom);
        return ResponseEntity.ok(savedRoom);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaPhong(@PathVariable int id) {
        if (!phongRepository.existsById(id)) {
            return ResponseEntity.notFound().build();  // 404 nếu không có phòng
        }
        phongRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 nếu xóa thành công
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatPhong(@PathVariable int id, @RequestBody Phong phongMoi) {
        Optional<Phong> phongCuOpt = phongRepository.findById(id);
        if (phongCuOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Phong phongCu = phongCuOpt.get();
        phongCu.setTenPhong(phongMoi.getTenPhong());
        phongCu.setSoPhong(phongMoi.getSoPhong());
        phongCu.setSucChua(phongMoi.getSucChua());
        phongCu.setGiaPhong(phongMoi.getGiaPhong());
        phongCu.setTinhTrangPhong(phongMoi.getTinhTrangPhong());

        phongRepository.save(phongCu);

        return ResponseEntity.ok(phongCu);
    }


}
