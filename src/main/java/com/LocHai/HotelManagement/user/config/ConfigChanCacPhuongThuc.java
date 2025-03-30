//package com.LocHai.HotelManagement.config;
//
//import com.LocHai.HotelManagement.user.entity.TaiKhoan;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.metamodel.Type;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
////Interface cho phép bạn tùy chỉnh cấu hình của Spring Data REST.
//@Component
//public class ConfigChanCacPhuongThuc implements RepositoryRestConfigurer {
//
//    private String url = "http://localhost:3000";
//
//
//
//    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)  {
//        // cho phép localhost:3000( react ) có thể gửi request đến backend, và chỉ cho phép 4 phương thức HTTP này
//        cors.addMapping("/**")
//                .allowedOrigins(url)
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//
//
//        // định nghĩa các phương thức muốn chặn
//        HttpMethod[] chanCacPhuongThuc = {
//                HttpMethod.POST,
//                HttpMethod.PUT,
//                HttpMethod.PATCH,
//                HttpMethod.DELETE
//        };
//        // chặn gọi tới class Theloai
//        //disableHttpMethods(TheLoai.class, config, chanCacPhuongThuc);
//
//
//        // định nghĩa muốn 1 1 phương thức
//        HttpMethod[] phuongThucDelete = {
//                HttpMethod.DELETE
//        };
//        // chặn gọi tới class TaiKhoan với phuongThucDelete
//        //disableHttpMethods(TaiKhoan.class, config, phuongThucDelete);
//    }
//
//
//    // Phương thức này dùng để vô hiệu hóa (disable) các HTTP method như POST, PUT, DELETE,... cho 1 entity cụ thể (class c).
//    private void disableHttpMethods(Class c, RepositoryRestConfiguration config, HttpMethod[] methods){
//        config.getExposureConfiguration()
//                .forDomainType(c)
//                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(methods)))
//                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable());
//    }
//}
