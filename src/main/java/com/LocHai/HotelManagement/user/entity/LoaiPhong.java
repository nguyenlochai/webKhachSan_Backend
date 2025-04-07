package com.LocHai.HotelManagement.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "loai_phong")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoaiPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_phong")
    private int idLoaiPhong;

    @Column(name = "ten_loai_phong")
    private String tenLoaiPhong;

    @OneToMany(mappedBy = "loaiPhong", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("loaiPhong")
    private List<TienNghi> danhSachTienNghi;

    @OneToMany(mappedBy = "loaiPhong", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("loaiPhong")
    private List<Phong> danhSachPhong;

    public LoaiPhong() {
    }

    public int getIdLoaiPhong() {
        return idLoaiPhong;
    }

    public void setIdLoaiPhong(int idLoaiPhong) {
        this.idLoaiPhong = idLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public List<TienNghi> getDanhSachTienNghi() {
        return danhSachTienNghi;
    }

    public void setDanhSachTienNghi(List<TienNghi> danhSachTienNghi) {
        this.danhSachTienNghi = danhSachTienNghi;
    }

    public List<Phong> getDanhSachPhong() {
        return danhSachPhong;
    }

    public void setDanhSachPhong(List<Phong> danhSachPhong) {
        this.danhSachPhong = danhSachPhong;
    }
}
