package com.hzht.mlxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = {"com.hzht.mlxc.dao"})
public class StartUp {

  public static void main(String[] args) {
    SpringApplication.run(StartUp.class, args);
  }
}
