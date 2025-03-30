package com.LocHai.HotelManagement.user.security;


import com.LocHai.HotelManagement.user.Service.UserService;
import com.LocHai.HotelManagement.user.filter.JwtFilter;
import com.LocHai.HotelManagement.user.security.Endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userService); // tìm username và password mình cung cấp cho security
        dap.setPasswordEncoder(passwordEncoder());   // mật khẩu người dùng nhập vào
        return dap;


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer->configurer
//                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.DELETE, Endpoints.PUBLIC_DELETE_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.PUT, Endpoints.PUBLIC_PUT_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST_ENDPOINTS).permitAll()
                        //.requestMatchers("/admin/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, Endpoints.ADMIN_GET_ENDPOINTS).hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.POST, Endpoints.ADMIN_POST_ENDPOINTS).hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, Endpoints.ADMIN_DELETE_ENDPOINTS).hasAuthority("ADMIN")
                        .anyRequest().permitAll()

//                        .anyRequest().authenticated()
        );
       //  CORS ngăn chặn yêu cầu từ các trang web khác domain với back-end.
        http.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Arrays.asList(Endpoints.front_end_host)); // Kiểm tra lại URL này
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                corsConfiguration.setAllowCredentials(true); // Quan trọng nếu dùng JWT
                return corsConfiguration;
            });
        });
        // lọc filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //  là chính sách giúp ứng dụng hoạt động ở chế độ không trạng thái, không lưu trữ session của người dùng trên server.
        //Điều này có nghĩa là mỗi yêu cầu từ client đều phải kèm theo thông tin xác thực (thường là JWT) để server xác thực mà không cần nhớ trạng thái của người dùng giữa các yêu cầu.
        // giúp tăng bảo mật
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 2 http này cần có
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // AuthenticationManager là spring boot cung cấp. được sử dụng trong Spring Security để thực hiện quá trình xác thực người dùng.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
