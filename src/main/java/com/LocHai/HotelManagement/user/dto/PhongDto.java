package com.LocHai.HotelManagement.user.dto;

import com.LocHai.HotelManagement.user.entity.HinhAnh;
import com.LocHai.HotelManagement.user.entity.LoaiPhong;

import java.util.List;

public class PhongDto {
    private int idPhong;
    private double giaPhong;
    private String tenPhong;
    private byte sucChua;
    private int soPhong;
    private String moTa;
    private String tinhTrangPhong; // Lưu enum dưới dạng String

    private LoaiPhong loaiPhong;
    private List<HinhAnh> danhSachHinhAnh;

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public List<HinhAnh> getDanhSachHinhAnh() {
        return danhSachHinhAnh;
    }

    public void setDanhSachHinhAnh(List<HinhAnh> danhSachHinhAnh) {
        this.danhSachHinhAnh = danhSachHinhAnh;
    }

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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTinhTrangPhong() {
        return tinhTrangPhong;
    }

    public void setTinhTrangPhong(String tinhTrangPhong) {
        this.tinhTrangPhong = tinhTrangPhong;
    }
}
