package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TrangThaiDichVu;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "dich_vu")
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dich_vu")
    private int idDichVu;

    @Column(name = "ten_dich_vu", length = 20)
    private String tenDichVu;

    @Column(name = "gia_dich_vu")
    private double giaDichVu;

    @Column(name = "mo_ta", columnDefinition = "text")
    private String moTa;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "trang_thai")
    private TrangThaiDichVu trangThai;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_phieu_thue_dich_vu", nullable = false) // cột này không được null
    private PhieuThueDichVu phieuThueDichVu;

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public PhieuThueDichVu getPhieuThueDichVu() {
        return phieuThueDichVu;
    }

    public void setPhieuThueDichVu(PhieuThueDichVu phieuThueDichVu) {
        this.phieuThueDichVu = phieuThueDichVu;
    }

    public TrangThaiDichVu getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiDichVu trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(double giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }
}

