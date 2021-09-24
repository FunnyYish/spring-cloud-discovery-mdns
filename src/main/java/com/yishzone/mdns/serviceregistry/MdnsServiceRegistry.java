package com.yishzone.mdns.serviceregistry;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class MdnsServiceRegistry implements ServiceRegistry<MdnsRegistration> {
    // 监听spring启动时间时，从web server端口中给这玩意赋值
    private AtomicInteger port = new AtomicInteger(0);

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deregister(MdnsRegistration registration) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getStatus(MdnsRegistration registration) {
        // TODO Auto-generated method stub
        return "up";
    }

    @Override
    public void register(MdnsRegistration registration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setStatus(MdnsRegistration registration, String status) {
        // TODO Auto-generated method stub

    }

}
