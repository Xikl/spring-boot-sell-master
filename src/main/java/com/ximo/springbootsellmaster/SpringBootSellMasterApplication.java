package com.ximo.springbootsellmaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.ximo.springbootsellmaster.domain.mapper")
@EnableCaching
public class SpringBootSellMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSellMasterApplication.class, args);
	}
}
