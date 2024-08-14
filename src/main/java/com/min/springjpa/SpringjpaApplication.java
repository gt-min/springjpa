package com.min.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 데이터베이스 설정이 없어도 빌드가 가능하도록 설정
@ComponentScan(basePackages = "com.min.springjpa")
public class SpringjpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringjpaApplication.class, args);
  }

}
