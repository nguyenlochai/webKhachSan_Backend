package com.LocHai.HotelManagement.user.security;


import lombok.Data;



public class JwtResponse {
    private final String jwt;


    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }


    public String getJwt() {
        return jwt;
    }
}
