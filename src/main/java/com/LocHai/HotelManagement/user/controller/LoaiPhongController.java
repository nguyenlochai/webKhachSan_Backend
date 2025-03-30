package com.LocHai.HotelManagement.user.controller;

import com.LocHai.HotelManagement.user.entity.LoaiPhong;
import com.LocHai.HotelManagement.user.repository.LoaiPhongRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/loaiPhong")
public class LoaiPhongController {

    private final LoaiPhongRepository loaiPhongRepository;

    public LoaiPhongController(LoaiPhongRepository loaiPhongRepository) {
        this.loaiPhongRepository = loaiPhongRepository;
    }

    @GetMapping
    public ResponseEntity<List<LoaiPhong>> getAllLoaiPhong() {
        List<LoaiPhong> list = loaiPhongRepository.findAll();
        System.out.println("🔍 Dữ liệu trước khi trả về API: " + list);

        return ResponseEntity.ok(list);
    }

}

