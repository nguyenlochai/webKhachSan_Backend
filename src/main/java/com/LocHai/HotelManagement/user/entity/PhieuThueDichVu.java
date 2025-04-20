package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TinhTrangPhong;
import com.LocHai.HotelManagement.user.enum2.TrangThaiPhieuThue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dich_vu", nullable = false)
    private DichVu dichVu;

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

    public int getIdPhieuThueDichVu() {
        return idPhieuThueDichVu;
    }

    public void setIdPhieuThueDichVu(int idPhieuThueDichVu) {
        this.idPhieuThueDichVu = idPhieuThueDichVu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public PhieuThuePhong getPhieuThuePhong() {
        return phieuThuePhong;
    }

    public void setPhieuThuePhong(PhieuThuePhong phieuThuePhong) {
        this.phieuThuePhong = phieuThuePhong;
    }

    public HoaDonThanhToan getHoaDonThanhToan() {
        return hoaDonThanhToan;
    }

    public void setHoaDonThanhToan(HoaDonThanhToan hoaDonThanhToan) {
        this.hoaDonThanhToan = hoaDonThanhToan;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public TrangThaiPhieuThue getTrangThaiPhieuTheDichVu() {
        return trangThaiPhieuTheDichVu;
    }

    public void setTrangThaiPhieuTheDichVu(TrangThaiPhieuThue trangThaiPhieuTheDichVu) {
        this.trangThaiPhieuTheDichVu = trangThaiPhieuTheDichVu;
    }

    public Date getThoiGianNhanDichVu() {
        return thoiGianNhanDichVu;
    }

    public void setThoiGianNhanDichVu(Date thoiGianNhanDichVu) {
        this.thoiGianNhanDichVu = thoiGianNhanDichVu;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
