package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.TinhTrangPhong;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Phong")
public class Phong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phong")
    private int idPhong;

    @Column(name = "gia_phong")
    private double giaPhong;

    @Column(name = "ten_phong")
    private String tenPhong;

    @Column(name = "suc_chua") // sức chứa
    private byte sucChua;

    @Column(name = "so_phong")
    private int soPhong;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi (VARCHAR)
    @Column(name = "tinh_trang_phong")
    private TinhTrangPhong tinhTrangPhong;

    @JsonIgnore
    @OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    } , orphanRemoval = true)
    private List<HinhAnh> danhSachHinhAnh;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_loai_phong", nullable = false)
    private LoaiPhong loaiPhong;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "phong_phieu_thue_phong",
            joinColumns = @JoinColumn(name="id_phong"),
            inverseJoinColumns = @JoinColumn(name = "id_phieu_thue_phong")
    )
    private List<PhieuThuePhong> danhSachPhieuThuePhong;

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public byte getSucChua() {
        return sucChua;
    }

    public void setSucChua(byte sucChua) {
        this.sucChua = sucChua;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public TinhTrangPhong getTinhTrangPhong() {
        return tinhTrangPhong;
    }

    public void setTinhTrangPhong(TinhTrangPhong tinhTrangPhong) {
        this.tinhTrangPhong = tinhTrangPhong;
    }

    public List<HinhAnh> getDanhSachHinhAnh() {
        return danhSachHinhAnh;
    }

    public void setDanhSachHinhAnh(List<HinhAnh> danhSachHinhAnh) {
        this.danhSachHinhAnh = danhSachHinhAnh;
    }

    public List<PhieuThuePhong> getDanhSachPhieuThuePhong() {
        return danhSachPhieuThuePhong;
    }

    public void setDanhSachPhieuThuePhong(List<PhieuThuePhong> danhSachPhieuThuePhong) {
        this.danhSachPhieuThuePhong = danhSachPhieuThuePhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
