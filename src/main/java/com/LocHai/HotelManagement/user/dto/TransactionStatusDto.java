package com.LocHai.HotelManagement.user.dto;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionStatusDto implements Serializable {

    private String status;
    private String message;
    private String data;

    public TransactionStatusDto() {
    }

    public TransactionStatusDto(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
