package com.LocHai.HotelManagement.user.Service;

import com.LocHai.HotelManagement.user.entity.Quyen;
import com.LocHai.HotelManagement.user.entity.TaiKhoan;
import com.LocHai.HotelManagement.user.repository.QuyenRepository;
import com.LocHai.HotelManagement.user.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private QuyenRepository quyenRepository;

    @Override
    public TaiKhoan findByUsername(String soDienThoai) {
        return taiKhoanRepository.findBySoDienThoai(soDienThoai);
    }

    // Hàm này dùng để Spring Boot hiểu User là ai, quyền hạn là gì.
    // tìm kiếm người dùng theo tên đăng nhập (username) và trả về đối tượng UserDetails.
    // Nếu không tìm thấy người dùng, nó sẽ ném ra ngoại lệ UsernameNotFoundException.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = findByUsername(username);
        if(taiKhoan == null){
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        // Lấy thông tin người dùng và role
        // Tạo một đối tượng User để Spring Boot quản lý,
        // trong đó bao gồm tên đăng nhập, mật khẩu và danh sách quyền được chuyển đổi thành authorities.
        User user = new User(taiKhoan.getSoDienThoai(), taiKhoan.getMatKhau(), rolesToAuthorities(taiKhoan.getDanhSachQuyen()));
        return user;
    }

    // GrantedAuthority đại diện cho quyền của người dùng (các vai trò như "ROLE_ADMIN", "ROLE_USER").
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens){
        return quyens.stream().map(quyen-> new SimpleGrantedAuthority(quyen.getTenQuyen())).collect(Collectors.toList());
    }
}
