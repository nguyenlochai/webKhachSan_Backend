package com.LocHai.HotelManagement.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tai_khoan_ngan_hang")
public class TaiKhoanNganHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tai_khoan_ngan_hang")
    private int idTaiKhoanNganHang;

    @Column(name = "ten_ngan_hang")
    private String tenNganHang;

    @Column(name = "chu_tai_khoan")
    private String chuTaiKhoan;

    @Column(name = "so_tai_khoan")
    private String soTaiKhoan;

    @OneToMany(mappedBy = "taiKhoanNganHang", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, orphanRemoval = true)
    private List<HoaDonThanhToan> danhSachHoaDonThanhToan;

    public int getIdTaiKhoanNganHang() {
        return idTaiKhoanNganHang;
    }

    public void setIdTaiKhoanNganHang(int idTaiKhoanNganHang) {
        this.idTaiKhoanNganHang = idTaiKhoanNganHang;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public List<HoaDonThanhToan> getDanhSachHoaDonThanhToan() {
        return danhSachHoaDonThanhToan;
    }

    public void setDanhSachHoaDonThanhToan(List<HoaDonThanhToan> danhSachHoaDonThanhToan) {
        this.danhSachHoaDonThanhToan = danhSachHoaDonThanhToan;
    }

    public String getChuTaiKhoan() {
        return chuTaiKhoan;
    }

    public void setChuTaiKhoan(String chuTaiKhoan) {
        this.chuTaiKhoan = chuTaiKhoan;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }
}
