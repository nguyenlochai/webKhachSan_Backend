package com.LocHai.HotelManagement.user.controller;

import com.LocHai.HotelManagement.user.dto.BinhLuanDTO;
import com.LocHai.HotelManagement.user.entity.BinhLuan;
import com.LocHai.HotelManagement.user.repository.BinhLuanRepository;
import com.LocHai.HotelManagement.user.repository.PhongRepository;
import com.LocHai.HotelManagement.user.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/binh-luan")
public class BinhLuanController {

    @Autowired
    private BinhLuanRepository binhLuanRepo;
    @Autowired private PhongRepository phongRepo;
    @Autowired private TaiKhoanRepository taiKhoanRepo;

    @PostMapping
    public ResponseEntity<?> themBinhLuan(@RequestBody BinhLuanDTO dto) {
        var phong = phongRepo.findById(dto.getPhongId()).orElseThrow();
        var taiKhoan = taiKhoanRepo.findById(dto.getTaiKhoanId()).orElseThrow();

        var bl = new BinhLuan();
        bl.setNoiDung(dto.getNoiDung());
        bl.setSoSao(dto.getSoSao());
        bl.setPhong(phong);
        bl.setTaiKhoan(taiKhoan);
        bl.setNgayBinhLuan(LocalDateTime.now());

        binhLuanRepo.save(bl);
        return ResponseEntity.ok("Đã lưu bình luận");
    }

    @GetMapping("/phong/{id}")
    public ResponseEntity<List<BinhLuan>> layBinhLuanPhong(@PathVariable int id) {
        return ResponseEntity.ok(binhLuanRepo.findByPhong_IdPhong(id));
    }

    // Tính điểm trung bình đánh giá phòng
    @GetMapping("/phong/{id}/diem-trung-binh")
    public ResponseEntity<Double> diemTrungBinh(@PathVariable int id) {
        Double tb = binhLuanRepo.tinhDiemTrungBinh(id);
        return ResponseEntity.ok(tb != null ? tb : 0.0);
    }
}

