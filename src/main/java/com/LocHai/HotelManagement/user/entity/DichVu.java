package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TrangThaiDichVu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "dich_vu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "trang_thai")
    private TrangThaiDichVu trangThai;


    @OneToMany(mappedBy = "dichVu", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, orphanRemoval = true)
    @JsonIgnore
    private List<PhieuThueDichVu> phieuThueDichVus;


    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public List<PhieuThueDichVu> getPhieuThueDichVus() {
        return phieuThueDichVus;
    }

    public void setPhieuThueDichVus(List<PhieuThueDichVu> phieuThueDichVus) {
        this.phieuThueDichVus = phieuThueDichVus;
    }

    public TrangThaiDichVu getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiDichVu trangThai) {
        this.trangThai = trangThai;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

