package com.LocHai.HotelManagement.user.Service;

import com.LocHai.HotelManagement.user.entity.TaiKhoan;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public TaiKhoan findByUsername(String soDienThoai);
}
