package com.monitor.monitoringapp.repository;

import com.monitor.monitoringapp.entity.Monitor;
import com.monitor.monitoringapp.service.MonitorService;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MonitorRepo implements MonitorService {
    Map<String,String> mapUrls;

    public MonitorRepo(){
        mapUrls = new HashMap<>();
    }

    @Override
    public void addDataInMap(Monitor m){
        mapUrls.put(m.getServerName(),m.getServerURL());

    }

    @Override
    public Map<String, String> getURls() {
        return mapUrls;
    }

    @Override
    public int getMapSize() {
        return mapUrls.size();
    }

}
