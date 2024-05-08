package com.monitor.monitoringapp.service;


import com.monitor.monitoringapp.entity.Monitor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MonitorService {

     void addDataInMap(Monitor m);
     Map<String,String> getURls();
     int getMapSize();

}
