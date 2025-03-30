package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.LoaiPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "loai-phong")
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer> {

}
