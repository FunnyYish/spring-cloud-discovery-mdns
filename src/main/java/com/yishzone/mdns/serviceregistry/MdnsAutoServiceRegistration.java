package com.yishzone.mdns.serviceregistry;

import com.yishzone.mdns.discovery.MdnsDiscoveryProperties;

import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.util.Assert;

public class MdnsAutoServiceRegistration extends AbstractAutoServiceRegistration<MdnsRegistration> {

    /**
     * @param serviceRegistry
     * @param properties
     * @param properties2
     * @param registration
     */
    public MdnsAutoServiceRegistration(ServiceRegistry<MdnsRegistration> serviceRegistry,
            AutoServiceRegistrationProperties autoServiceRegistrationProperties, MdnsDiscoveryProperties properties,
            MdnsRegistration registration) {
        super(serviceRegistry, autoServiceRegistrationProperties);
        this.properties = properties;
        this.registration = registration;
    }

    private MdnsDiscoveryProperties properties;

    private MdnsRegistration registration;

    @Override
    protected Object getConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected MdnsRegistration getRegistration() {
        if (this.registration.getPort() == 0 && this.getPort().get() > 0) {
            this.registration.initializePort(this.getPort().get());
        }
        Assert.isTrue(this.registration.getPort() > 0, "service.port has not been set");
        return this.registration;
    }

    @Override
    protected MdnsRegistration getManagementRegistration() {
        // TODO Auto-generated method stub
        return null;
    }

    void setPortIfNeeded(int port) {
        getPort().compareAndSet(0, port);
    }
}
