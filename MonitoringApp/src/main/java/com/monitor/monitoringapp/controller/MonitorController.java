package com.monitor.monitoringapp.controller;

import com.monitor.monitoringapp.config.Schedule;
import com.monitor.monitoringapp.entity.Monitor;
import com.monitor.monitoringapp.service.MonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/*
* @Author Malik Shameer Riaz
* Controller to control the monitoring of the Servers
* addServerData -> Endpoint to add a server which need to be monitored
* getStatus -> give the status if server is running or down.
*/

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    MonitorService monitorService;
    Schedule schedule;


    public MonitorController(MonitorService monitorService , Schedule schedule) {
        this.monitorService = monitorService;
        this.schedule=schedule;
    }

    @PostMapping
    public ResponseEntity<?> addServerData(@RequestBody Monitor m) {
        if (m.getServerName().isEmpty() || m.getServerURL().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        this.monitorService.addDataInMap(m);
        return ResponseEntity.ok(m);
    }


    @GetMapping("/getStatus")
    public Map<String, ?> getStatus() {
        Map<String, Boolean> m = schedule.getMapStatus();
        if(m.size()==this.monitorService.getMapSize()){
            return schedule.getMapStatus();
        }else{
            Map<String, String> temp = new HashMap<>();
            for(String key:this.monitorService.getURls().keySet() ){
                temp.put(key,"loading..");
            }
            return temp;
        }
    }

    @GetMapping
    public String index() {
        return "index";
    }


}
