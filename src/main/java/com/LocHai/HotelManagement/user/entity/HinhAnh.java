package com.LocHai.HotelManagement.user.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "hinh_anh")
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_anh")
    private int idhinhAnh;

    @Column(name = "ten_hinh_anh", length = 256)
    private String tenHinhAnh;

    @Column(name = "duong_dan")
    private String duongDan;

    @Column(name = "du_lieu_anh", columnDefinition = "LONGTEXT")
    @Lob
    private String duLieuAnh;

    // bên nào nhiều thì bên đó chưa id
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_phong", nullable = false)
    private Phong phong;


    public int getIdhinhAnh() {
        return idhinhAnh;
    }

    public void setIdhinhAnh(int idhinhAnh) {
        this.idhinhAnh = idhinhAnh;
    }

    public String getTenHinhAnh() {
        return tenHinhAnh;
    }

    public void setTenHinhAnh(String tenHinhAnh) {
        this.tenHinhAnh = tenHinhAnh;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getDuLieuAnh() {
        return duLieuAnh;
    }

    public void setDuLieuAnh(String duLieuAnh) {
        this.duLieuAnh = duLieuAnh;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
