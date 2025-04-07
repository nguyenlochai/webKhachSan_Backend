package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "danh-sach-dich-vu")
public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
}
