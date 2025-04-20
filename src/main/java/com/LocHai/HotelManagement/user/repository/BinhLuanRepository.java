package com.LocHai.HotelManagement.user.repository;

import com.LocHai.HotelManagement.user.entity.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "binh_luan")
public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {

    List<BinhLuan> findByPhong_IdPhong(int idPhong);

    @Query("SELECT AVG(b.soSao) FROM BinhLuan b WHERE b.phong.idPhong = :idPhong")
    Double tinhDiemTrungBinh(@Param("idPhong") int idPhong);
}

