package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.PhieuThuePhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "phieu-thue-phong")
public interface PhieuThuePhongRepositoty extends JpaRepository<PhieuThuePhong, Integer> {
}
