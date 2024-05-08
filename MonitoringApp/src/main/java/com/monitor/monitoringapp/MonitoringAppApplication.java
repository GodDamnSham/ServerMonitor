package com.monitor.monitoringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitoringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringAppApplication.class, args);
    }

}
