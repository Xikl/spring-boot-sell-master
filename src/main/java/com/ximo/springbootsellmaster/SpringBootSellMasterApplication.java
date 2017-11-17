package com.ximo.springbootsellmaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ximo.springbootsellmaster.domain.mapper")
public class SpringBootSellMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSellMasterApplication.class, args);
	}
}
