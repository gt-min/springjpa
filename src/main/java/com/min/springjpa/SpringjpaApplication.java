package com.min.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)      // 데이터베이스 설정이 없어도 빌드가 가능하도록 설정
@EnableJpaRepositories(basePackages = {"com.min.springjpa.repository"})  // JpaRepository 사용 허용
@ComponentScan(basePackages = "com.min.springjpa")
public class SpringjpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringjpaApplication.class, args);
  }

}
