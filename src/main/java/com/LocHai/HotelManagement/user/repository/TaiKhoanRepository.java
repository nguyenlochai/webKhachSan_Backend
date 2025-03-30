package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tai-khoan")
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {

    public boolean existsBySoDienThoai(String soDienThoai);

    public boolean existsByEmail(String email);

    public TaiKhoan findBySoDienThoai(String soDienThoai);

//    public TaiKhoan findByEmail(String email);
}
