package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.PhieuThueDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "phieu-thue-dich-vu")
public interface PhieuThueDichVuRepository extends JpaRepository<PhieuThueDichVu, Integer> {
}
