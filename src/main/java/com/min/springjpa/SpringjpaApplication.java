package com.min.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.min.springjpa")
public class SpringjpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringjpaApplication.class, args);
  }

}
