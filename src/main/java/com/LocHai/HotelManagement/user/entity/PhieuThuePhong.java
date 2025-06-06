package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TrangThaiPhieuThue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "phieu_thue_phong")
public class PhieuThuePhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phieu_thue_phong")
    private int idPhieuThuePhong;

    @Column(name = "ngay_dat_phong", updatable = false)
    private Date ngayDatPhong;

    @Column(name = "ngay_nhan_phong", updatable = false)
    private Date ngayNhanPhong;

    @Column(name = "ngay_tra_phong", updatable = false)
    private Date ngayTraPhong;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "trang_thai")
    private TrangThaiPhieuThue trangThaiPhieuThePhong;

    @Column(name = "tong_tien")
    private double tongTien;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_tai_khoan", nullable = false)
    @JsonBackReference
    private TaiKhoan taiKhoan;

    @OneToOne(mappedBy = "phieuThuePhong", cascade = CascadeType.ALL)
    private PhieuThueDichVu phieuThueDichVu;

    @OneToOne(mappedBy = "phieuThuePhong", cascade = CascadeType.ALL)
    private HoaDonThanhToan hoaDonThanhToan;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "phong_phieu_thue_phong",
            joinColumns = @JoinColumn(name="id_phieu_thue_phong"),
            inverseJoinColumns = @JoinColumn(name = "id_phong")
    )
    List<Phong> phong;

    public PhieuThuePhong() {
    }

    public PhieuThuePhong(int idPhieuThuePhong, List<Phong> phong, HoaDonThanhToan hoaDonThanhToan, PhieuThueDichVu phieuThueDichVu, double tongTien, TaiKhoan taiKhoan, TrangThaiPhieuThue trangThaiPhieuThePhong, Date ngayTraPhong, Date ngayNhanPhong, Date ngayDatPhong) {
        this.idPhieuThuePhong = idPhieuThuePhong;
        this.phong = phong;
        this.hoaDonThanhToan = hoaDonThanhToan;
        this.phieuThueDichVu = phieuThueDichVu;
        this.tongTien = tongTien;
        this.taiKhoan = taiKhoan;
        this.trangThaiPhieuThePhong = trangThaiPhieuThePhong;
        this.ngayTraPhong = ngayTraPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayDatPhong = ngayDatPhong;
    }

    public int getIdPhieuThuePhong() {
        return idPhieuThuePhong;
    }

    public void setIdPhieuThuePhong(int idPhieuThuePhong) {
        this.idPhieuThuePhong = idPhieuThuePhong;
    }

    public List<Phong> getPhong() {
        return phong;
    }

    public void setPhong(List<Phong> phong) {
        this.phong = phong;
    }

    public HoaDonThanhToan getHoaDonThanhToan() {
        return hoaDonThanhToan;
    }

    public void setHoaDonThanhToan(HoaDonThanhToan hoaDonThanhToan) {
        this.hoaDonThanhToan = hoaDonThanhToan;
    }

    public PhieuThueDichVu getPhieuThueDichVu() {
        return phieuThueDichVu;
    }

    public void setPhieuThueDichVu(PhieuThueDichVu phieuThueDichVu) {
        this.phieuThueDichVu = phieuThueDichVu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public TrangThaiPhieuThue getTrangThaiPhieuThePhong() {
        return trangThaiPhieuThePhong;
    }

    public void setTrangThaiPhieuThePhong(TrangThaiPhieuThue trangThaiPhieuThePhong) {
        this.trangThaiPhieuThePhong = trangThaiPhieuThePhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(Date ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }
}
