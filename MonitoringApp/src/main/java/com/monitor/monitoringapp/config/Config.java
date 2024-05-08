package com.monitor.monitoringapp.config;

import com.monitor.monitoringapp.entity.Monitor;
import com.monitor.monitoringapp.repository.MonitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    MonitorRepo monitorRepo;

    public Config(MonitorRepo monitorRepo) {
        this.monitorRepo = monitorRepo;
    }

    @Bean
    public CommandLineRunner startup() {

        return args -> {
            monitorRepo.addDataInMap(new Monitor("google", "google.com"));
            monitorRepo.addDataInMap(new Monitor("netflix", "netflix.com"));
            monitorRepo.addDataInMap(new Monitor("instagram", "instagram.com"));
            monitorRepo.addDataInMap(new Monitor("juniqIT", "juniq-it.de"));
            monitorRepo.addDataInMap(new Monitor("demo", "demo.de"));
            monitorRepo.addDataInMap(new Monitor("Comcast", "comcast.com"));
        };
    }
}