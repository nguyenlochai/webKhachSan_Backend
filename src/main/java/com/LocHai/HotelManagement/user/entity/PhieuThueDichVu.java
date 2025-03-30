package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TinhTrangPhong;
import com.LocHai.HotelManagement.user.enum2.TrangThaiPhieuThue;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "phieu_thue_dich_vu")
public class PhieuThueDichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phieu_thue_dich_vu")
    private int idPhieuThueDichVu;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "ngay_dat")
    private Date ngayDat;

    @Column(name = "thoi_gian_nhan_dich_vu", updatable = false) // chỉ được update cột này 1 lần
    private Date thoiGianNhanDichVu;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "trang_thai")
    private TrangThaiPhieuThue trangThaiPhieuTheDichVu;

    @Column(name = "tong_tien")
    private double tongTien;

    @OneToMany(mappedBy = "phieuThueDichVu", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, orphanRemoval = true)
    List<DichVu> danhSachDichVu;

    @OneToOne(mappedBy = "phieuThueDichVu",cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, orphanRemoval = true)
    private HoaDonThanhToan hoaDonThanhToan;

    @OneToOne
    @JoinColumn(name = "id_phieu_thue_phong", unique = true)
    private PhieuThuePhong phieuThuePhong;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_tai_khoan", nullable = false)
    private TaiKhoan taiKhoan;





}
