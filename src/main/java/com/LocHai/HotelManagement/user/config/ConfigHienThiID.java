package com.LocHai.HotelManagement.user.config;

import jakarta.persistence.metamodel.Type;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//Interface cho phép bạn tùy chỉnh cấu hình của Spring Data REST.
@Component
public class ConfigHienThiID implements RepositoryRestConfigurer {

    @Autowired
    EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)  {
        // hiển thị nhiều id, (cho phép id xuất hiện bên trong json)
        entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new); //là thông tin của các entity để hiển thị phản hồi ở json
        // Cho phép hiển thị ID chỉ cho tất cả entity
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));


        //expose id, hiển thị 1 id TheLoai
        //Cho phép hiển thị ID chỉ cho thực thể TheLoai
        //config.exposeIdsFor(TheLoai.class);
    }
}

