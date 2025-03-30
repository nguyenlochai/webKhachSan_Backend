
package com.LocHai.HotelManagement.user.dto;

public class ThongBaoResDto extends Throwable{

    private String thongBao;


    public ThongBaoResDto(String thongBao) {
        this.thongBao = thongBao;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }
}
