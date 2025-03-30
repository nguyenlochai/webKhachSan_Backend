package com.LocHai.HotelManagement.user.controller;

import com.LocHai.HotelManagement.user.Service.JwtService;
import com.LocHai.HotelManagement.user.Service.TaiKhoanService;
import com.LocHai.HotelManagement.user.Service.UserService;
import com.LocHai.HotelManagement.user.entity.TaiKhoan;
import com.LocHai.HotelManagement.user.repository.TaiKhoanRepository;
import com.LocHai.HotelManagement.user.security.JwtResponse;
import com.LocHai.HotelManagement.user.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tai-khoan")
public class TaiKhoanController {

    TaiKhoanRepository taiKhoanRepository;
    TaiKhoanService taiKhoanService;
    AuthenticationManager authenticationManager;
    UserService userService;
    JwtService jwtService;

    @Autowired
    public TaiKhoanController(TaiKhoanRepository taiKhoanRepository, UserService userService, AuthenticationManager authenticationManager, TaiKhoanService taiKhoanService, JwtService jwtService) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.taiKhoanService = taiKhoanService;
        this.jwtService = jwtService;
    }




    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody TaiKhoan taiKhoan){
        ResponseEntity<?> response = taiKhoanService.dangKyTaiKhoan(taiKhoan);
        return response;

    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhapNguoiDung(@RequestBody LoginRequest loginRequest){
        // xác thực người dùng bằng tên đăng nhập và mật khẩu
        try {
            // kiểm tra đăng nhập
            // đi đếnn hàm authenticationProvider để xác thực
            //authenticationManager là class chịu trách nhiệm xác thực người dùng trong Spring Security. Nó gọi UserDetailsService để tải thông tin người dùng và kiểm tra thông tin đăng nhập.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            // nếu đănng nhập được, xác thực thành công thì tạo token JWT
            if (authentication.isAuthenticated()){
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }
        // xác thực không thành công
        // AuthenticationException là đăng nhập k chính xác
        catch (AuthenticationException e){
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng");
        }

        return ResponseEntity.badRequest().body("Xác thực không thành công");
    }
}
