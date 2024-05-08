package com.monitor.monitoringapp.config;
import com.monitor.monitoringapp.service.MonitorService;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class Schedule{

        MonitorService monitorService;
        Map<String , Boolean> mapStatus;

        public Schedule(MonitorService monitorService) {
                this.monitorService = monitorService;
                mapStatus = new HashMap<>();
        }
/*
* run -> Scheduler to schedule task to check the server status.
* */
        @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
        public void run() {
                this.mapStatus.clear();
                Map<String,String> map = this.monitorService.getURls();
                for (String key : map.keySet()) {
                        String serverUrl = map.get(key);
                        String serverName;
                        serverName = key;
                        try {
                                Socket socket = new Socket();
                                socket.connect(new InetSocketAddress(serverUrl, 443), 3000);
                                //System.out.println("Server " + serverName + " is UP");
                                socket.close();
                                mapStatus.put(serverName,true);
                        }catch(SocketTimeoutException s){
                                //System.out.println("Server " + serverName + " is down" + s.getMessage());
                                mapStatus.put(serverName,false);
                        } catch (IOException e) {
                                //System.out.println("Error checking server " + serverUrl + ": " + e.getMessage());
                                mapStatus.put(serverName,false);
                        }
                }
                /*for(String key : mapStatus.keySet()){
                       System.out.println(key + mapStatus.get(key));
                }*/
        }

}

