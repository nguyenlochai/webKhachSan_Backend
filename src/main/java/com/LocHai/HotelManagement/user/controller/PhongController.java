package com.LocHai.HotelManagement.user.controller;


import com.LocHai.HotelManagement.user.Service.PhongService;

import com.LocHai.HotelManagement.user.entity.Phong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/phong")
public class PhongController {


    private final PhongService phongService;

    @Autowired
    public PhongController(PhongService phongService) {
        this.phongService = phongService;
    }

    @GetMapping("/trong")
    public List<Phong> getAvailablePhong(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "sucChua", defaultValue = "1") int sucChua
    ) {
        return phongService.getAvailablePhong(startDate, endDate, sucChua);
    }

}
