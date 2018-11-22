package com.project.tour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.project.tour")
public class TourApplication
{
	@Value("${property.hey}") private String propertyHey;

	public static void main(String[] args)
	{
		SpringApplication.run(TourApplication.class, args);
	}
}
