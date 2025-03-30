package com.LocHai.HotelManagement.user.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tien_nghi")
public class TienNghi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tien_Nghi")
    private int idTienNghi;

    @Column(name = "ten_tien_nghi")
    private String tenThietBi;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_loai_phong", nullable = false)
    private LoaiPhong loaiPhong;


    public int getIdTienNghi() {
        return idTienNghi;
    }

    public void setIdTienNghi(int idTienNghi) {
        this.idTienNghi = idTienNghi;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }
}
