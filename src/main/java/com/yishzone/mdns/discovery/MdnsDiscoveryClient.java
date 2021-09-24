package com.yishzone.mdns.discovery;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MdnsDiscoveryClient implements DiscoveryClient {
    Map<String, Set<ServiceInstance>> instanceCache = new ConcurrentHashMap<>();

    /**
     * @param instanceCache
     */
    public MdnsDiscoveryClient(Map<String, Set<ServiceInstance>> instanceCache) {
        this.instanceCache = instanceCache;
    }

    @Override
    public String description() {

        return "Spring Cloud mdns Discovery Client";
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {

        return instanceCache.get(serviceId).stream().toList();
    }

    @Override
    public List<String> getServices() {

        log.warn("We haven't implemented that yet");
        return instanceCache.keySet().stream().toList();
    }

}
