package com.LocHai.HotelManagement.user.Service;

import com.LocHai.HotelManagement.user.entity.Phong;
import com.LocHai.HotelManagement.user.repository.PhongRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PhongService {

    private final PhongRepository phongRepository;

    public PhongService(PhongRepository phongRepository) {
        this.phongRepository = phongRepository;
    }

    public List<Phong> getAvailablePhong(LocalDate startDate, LocalDate endDate, int sucChua) {
        return phongRepository.findAvailablePhong(startDate, endDate, sucChua);
    }

}
