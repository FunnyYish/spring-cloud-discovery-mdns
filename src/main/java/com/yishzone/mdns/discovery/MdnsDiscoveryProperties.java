package com.yishzone.mdns.discovery;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtils.HostInfo;

@ConfigurationProperties(prefix = "spring.cloud.discovery.client.mdns")
public class MdnsDiscoveryProperties {

    private String instanceId;

    private String serviceId;

    private String host;

    private Integer port;

    private boolean secure;

    private Map<String, String> metadata = new LinkedHashMap<>();

    private InetUtils inetUtils;
    private HostInfo hostInfo;

    /**
     * @param inetUtils
     */
    public MdnsDiscoveryProperties(InetUtils inetUtils) {
        this.inetUtils = inetUtils;
        this.hostInfo = inetUtils.findFirstNonLoopbackHostInfo();
        this.host = this.hostInfo.getIpAddress();
    }

    /**
     * @return the instanceId
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId the instanceId to set
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
        this.hostInfo.override = true;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the secure
     */
    public boolean isSecure() {
        return secure;
    }

    /**
     * @param secure the secure to set
     */
    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    /**
     * @return the metadata
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}
