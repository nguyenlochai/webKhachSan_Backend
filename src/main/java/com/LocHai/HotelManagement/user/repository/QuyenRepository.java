package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "phong")
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {

    public Quyen findByTenQuyen(String tenQuyen);
}
