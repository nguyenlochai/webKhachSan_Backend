package com.LocHai.HotelManagement.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "binh_luan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_binh_luan")
    private int idBinhLuan;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "so_sao")
    private int soSao;

    @Column(name = "ngay_binh_luan")
    private LocalDateTime ngayBinhLuan = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_tai_khoan", nullable = false) // cột này không được null
    private TaiKhoan taiKhoan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_phong", nullable = false) // cột này không được null
    @JsonIgnore
    private Phong phong;


    public BinhLuan() {
    }

    public int getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(int idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public LocalDateTime getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(LocalDateTime ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
