package com.project.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TourApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TourApplication.class, args);
    }
}
