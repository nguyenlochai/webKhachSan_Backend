package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TrangThaiDichVu;
import com.LocHai.HotelManagement.user.enum2.TrangThaiHoaDon;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "hoa_don_thanh_toan")
public class HoaDonThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoa_don_thanh_toan")
    private int idHoaDonThanhToan;

    @Column(name = "ngay_lap", updatable = false) // chỉ được update cột này 1 lần
    private Date ngayLap;

    @Column(name = "phuong_thuc_thanh_toan")
    private String phuongThucThanhToan;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "trang_thai")
    private TrangThaiHoaDon trangThai;

    @Column(name = "tong_tien")
    private double tongTien;

    @OneToOne
    @JoinColumn(name = "id_phieu_thue_phong", unique = true) // Đảm bảo giá trị trong cột không trùng lặp
    private PhieuThuePhong phieuThuePhong;


    @OneToOne
    @JoinColumn(name = "id_phieu_thue_dich_vu", unique = true)
    private PhieuThueDichVu phieuThueDichVu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_tai_khoan_ngan_hang", nullable = false) // cột này không được null
    private TaiKhoanNganHang taiKhoanNganHang;


    public int getIdHoaDonThanhToan() {
        return idHoaDonThanhToan;
    }

    public void setIdHoaDonThanhToan(int idHoaDonThanhToan) {
        this.idHoaDonThanhToan = idHoaDonThanhToan;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public TrangThaiHoaDon getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiHoaDon trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public PhieuThuePhong getPhieuThuePhong() {
        return phieuThuePhong;
    }

    public void setPhieuThuePhong(PhieuThuePhong phieuThuePhong) {
        this.phieuThuePhong = phieuThuePhong;
    }

    public PhieuThueDichVu getPhieuThueDichVu() {
        return phieuThueDichVu;
    }

    public void setPhieuThueDichVu(PhieuThueDichVu phieuThueDichVu) {
        this.phieuThueDichVu = phieuThueDichVu;
    }

    public TaiKhoanNganHang getTaiKhoanNganHang() {
        return taiKhoanNganHang;
    }

    public void setTaiKhoanNganHang(TaiKhoanNganHang taiKhoanNganHang) {
        this.taiKhoanNganHang = taiKhoanNganHang;
    }
}
