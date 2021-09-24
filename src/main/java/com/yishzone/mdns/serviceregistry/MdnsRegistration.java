package com.yishzone.mdns.serviceregistry;

import java.net.URI;
import java.util.Map;

import com.yishzone.mdns.discovery.MdnsDiscoveryProperties;

import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

public class MdnsRegistration implements Registration {
    private String serviceId;
    private int port;
    private String host;
    private MdnsDiscoveryProperties properties;
    private final ApplicationContext context;
    /**
     * Instance ID separator.
     */
    public static final char SEPARATOR = '-';

    /**
     * @param service
     * @param properties
     */
    public MdnsRegistration(MdnsDiscoveryProperties properties, ApplicationContext context) {

        this.properties = properties;
        this.context = context;
        serviceId = normalizeForDns(getAppName(properties, context.getEnvironment()));
        port = properties.getPort();
        host = properties.getHost();

    }

    public static String normalizeForDns(String s) {
        if (s == null || !Character.isLetter(s.charAt(0)) || !Character.isLetterOrDigit(s.charAt(s.length() - 1))) {
            throw new IllegalArgumentException(
                    "mdns service ids must not be empty, must start " + "with a letter, end with a letter or digit, "
                            + "and have as interior characters only letters, " + "digits, and hyphen: " + s);
        }

        StringBuilder normalized = new StringBuilder();
        Character prev = null;
        for (char curr : s.toCharArray()) {
            Character toAppend = null;
            if (Character.isLetterOrDigit(curr)) {
                toAppend = curr;
            } else if (prev == null || !(prev == SEPARATOR)) {
                toAppend = SEPARATOR;
            }
            if (toAppend != null) {
                normalized.append(toAppend);
                prev = toAppend;
            }
        }

        return normalized.toString();
    }

    public static String getAppName(MdnsDiscoveryProperties properties, Environment env) {
        final String appName = properties.getServiceId();
        if (StringUtils.hasText(appName)) {
            return appName;
        }
        return env.getProperty("spring.application.name", "application");
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return serviceId;
    }

    @Override
    public String getHost() {
        // TODO Auto-generated method stub
        return host;
    }

    @Override
    public int getPort() {
        // TODO Auto-generated method stub
        return port;
    }

    @Override
    public boolean isSecure() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public URI getUri() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        // TODO Auto-generated method stub
        return null;
    }

    public void initializePort(int knownPort) {
        if (getPort() == 0) {
            // not set by properties
            setPort(knownPort);
        }
    }

    private void setPort(int knownPort) {
        this.port = knownPort;
    }
}
