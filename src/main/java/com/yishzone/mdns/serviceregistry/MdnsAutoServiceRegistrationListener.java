package com.yishzone.mdns.serviceregistry;

import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

public class MdnsAutoServiceRegistrationListener implements SmartApplicationListener {
    private final MdnsAutoServiceRegistration mdnsAutoServiceRegistration;

    /**
     * @param mdnsRegistration
     */
    public MdnsAutoServiceRegistrationListener(MdnsAutoServiceRegistration mdnsRegistration) {
        this.mdnsAutoServiceRegistration = mdnsRegistration;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof WebServerInitializedEvent) {
            WebServerInitializedEvent event = (WebServerInitializedEvent) applicationEvent;

            ApplicationContext context = event.getApplicationContext();
            if (context instanceof ConfigurableWebServerApplicationContext) {
                if ("management".equals(((ConfigurableWebServerApplicationContext) context).getServerNamespace())) {
                    return;
                }
            }
            this.mdnsAutoServiceRegistration.setPortIfNeeded(event.getWebServer().getPort());
            this.mdnsAutoServiceRegistration.start();
        }
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return WebServerInitializedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

}
