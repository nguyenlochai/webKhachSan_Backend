package com.LocHai.HotelManagement.user.repository;


import com.LocHai.HotelManagement.user.entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;


@RepositoryRestResource(path = "phong")
public interface PhongRepository extends JpaRepository<Phong, Integer> {

    @Query(value = """
        SELECT p.*
             FROM phong p
             WHERE p.tinh_trang_phong = 'HOAT_DONG'
             AND p.suc_chua = :sucChua
             AND NOT EXISTS (
                 SELECT 1
                 FROM phieu_thue_phong ptp
                 JOIN phong_phieu_thue_phong pptp ON ptp.id_phieu_thue_phong = pptp.id_phieu_thue_phong
                 WHERE pptp.id_phong = p.id_phong
                 AND ptp.trang_thai = 'DA_DAT'
                 AND (
                     (ptp.ngay_nhan_phong <= :endDate AND ptp.ngay_tra_phong >= :startDate)
                 )
             )
    """, nativeQuery = true)
    List<Phong> findAvailablePhong(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   @Param("sucChua") int sucChua);
}
