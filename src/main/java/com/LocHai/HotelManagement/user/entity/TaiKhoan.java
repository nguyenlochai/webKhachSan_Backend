package com.LocHai.HotelManagement.user.entity;

import com.LocHai.HotelManagement.user.enum2.GioiTinh;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tai_khoan")
    private int idTaiKhoan;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    private GioiTinh gioiTinh;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "mat_khau")
    private String matKhau;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "quyen_tai_khoan",
            joinColumns = @JoinColumn(name="id_tai_khoan"),
            inverseJoinColumns = @JoinColumn(name = "id_quyen")
    )
    private List<Quyen> danhSachQuyen;

    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<PhieuThueDichVu> danhSachPhieuThueDichVu;

    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<PhieuThuePhong> danhSachPhieuThuePhong;


    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public List<Quyen> getDanhSachQuyen() {
        return danhSachQuyen;
    }

    public void setDanhSachQuyen(List<Quyen> danhSachQuyen) {
        this.danhSachQuyen = danhSachQuyen;
    }

    public List<PhieuThueDichVu> getDanhSachPhieuThueDichVu() {
        return danhSachPhieuThueDichVu;
    }

    public void setDanhSachPhieuThueDichVu(List<PhieuThueDichVu> danhSachPhieuThueDichVu) {
        this.danhSachPhieuThueDichVu = danhSachPhieuThueDichVu;
    }

    public List<PhieuThuePhong> getDanhSachPhieuThuePhong() {
        return danhSachPhieuThuePhong;
    }

    public void setDanhSachPhieuThuePhong(List<PhieuThuePhong> danhSachPhieuThuePhong) {
        this.danhSachPhieuThuePhong = danhSachPhieuThuePhong;
    }
}
