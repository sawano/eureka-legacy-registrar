/*
 * Copyright 2015 Daniel Sawano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.sawano.eureka.legacyregistrar.boot;

import com.netflix.appinfo.DataCenterInfo;
import se.sawano.eureka.legacyregistrar.LegacyInstanceConfig;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class SpringBootInstanceConfig implements LegacyInstanceConfig {

    private static final int LEASE_EXPIRATION_DURATION_SECONDS = 90;
    private static final int LEASE_RENEWAL_INTERVAL_SECONDS = 30;
    private static final boolean SECURE_PORT_ENABLED = false;
    private static final boolean NON_SECURE_PORT_ENABLED = true;
    private static final int NON_SECURE_PORT = 80;
    private static final int SECURE_PORT = 443;
    private static final boolean INSTANCE_ENABLED_ON_INIT = true;

    @NotNull
    private String instanceId;
    @NotNull
    private String appName;
    @NotNull
    private String virtualHostName;
    @NotNull
    private String hostName;

    private boolean instanceEnabledOnit = INSTANCE_ENABLED_ON_INIT;
    private int nonSecurePort = NON_SECURE_PORT;
    private int securePort = SECURE_PORT;
    private boolean nonSecurePortEnabled = NON_SECURE_PORT_ENABLED;
    private boolean securePortEnabled = SECURE_PORT_ENABLED;
    private int leaseRenewalIntervalInSeconds = LEASE_RENEWAL_INTERVAL_SECONDS;
    private int leaseExpirationDurationInSeconds = LEASE_EXPIRATION_DURATION_SECONDS;

    private String appGroupName;
    private String secureVirtualHostName;
    private String ASGName;
    private Map<String, String> metaDataMap = new HashMap<>();
    private final DataCenterInfo dataCenterInfo = () -> DataCenterInfo.Name.MyOwn;
    private String ipAddress;
    private String statusPageUrlPath;
    private String statusPageUrl;
    private String homePageUrlPath;
    private String homePageUrl;
    private String healthCheckUrlPath;
    private String healthCheckUrl;
    private String secureHealthCheckUrl;
    private String[] defaultAddressResolutionOrder;
    private String namespace;

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public String getAppname() {
        return appName;
    }

    @Override
    public String getAppGroupName() {
        return appGroupName;
    }

    @Override
    public boolean isInstanceEnabledOnit() {
        return instanceEnabledOnit;
    }

    @Override
    public int getNonSecurePort() {
        return nonSecurePort;
    }

    @Override
    public int getSecurePort() {
        return securePort;
    }

    @Override
    public boolean isNonSecurePortEnabled() {
        return nonSecurePortEnabled;
    }

    @Override
    public boolean getSecurePortEnabled() {
        return securePortEnabled;
    }

    @Override
    public int getLeaseRenewalIntervalInSeconds() {
        return leaseRenewalIntervalInSeconds;
    }

    @Override
    public int getLeaseExpirationDurationInSeconds() {
        return leaseExpirationDurationInSeconds;
    }

    @Override
    public String getVirtualHostName() {
        return virtualHostName;
    }

    @Override
    public String getSecureVirtualHostName() {
        return secureVirtualHostName;
    }

    @Override
    public String getASGName() {
        return ASGName;
    }

    @Override
    public String getHostName(final boolean refresh) {
        return hostName;
    }

    @Override
    public Map<String, String> getMetadataMap() {
        return metaDataMap;
    }

    @Override
    public DataCenterInfo getDataCenterInfo() {
        return dataCenterInfo;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String getStatusPageUrlPath() {
        return statusPageUrlPath;
    }

    @Override
    public String getStatusPageUrl() {
        return statusPageUrl;
    }

    @Override
    public String getHomePageUrlPath() {
        return homePageUrlPath;
    }

    @Override
    public String getHomePageUrl() {
        return homePageUrl;
    }

    @Override
    public String getHealthCheckUrlPath() {
        return healthCheckUrlPath;
    }

    @Override
    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    @Override
    public String getSecureHealthCheckUrl() {
        return secureHealthCheckUrl;
    }

    @Override
    public String[] getDefaultAddressResolutionOrder() {
        return defaultAddressResolutionOrder;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setInstanceId(final String instanceId) {
        this.instanceId = instanceId;
    }

    public void setAppName(final String appName) {
        this.appName = appName;
    }

    public void setAppGroupName(final String appGroupName) {
        this.appGroupName = appGroupName;
    }

    public void setInstanceEnabledOnit(final boolean instanceEnabledOnit) {
        this.instanceEnabledOnit = instanceEnabledOnit;
    }

    public void setNonSecurePort(final int nonSecurePort) {
        this.nonSecurePort = nonSecurePort;
    }

    public void setSecurePort(final int securePort) {
        this.securePort = securePort;
    }

    public void setNonSecurePortEnabled(final boolean nonSecurePortEnabled) {
        this.nonSecurePortEnabled = nonSecurePortEnabled;
    }

    public void setSecurePortEnabled(final boolean securePortEnabled) {
        this.securePortEnabled = securePortEnabled;
    }

    public void setLeaseRenewalIntervalInSeconds(final int leaseRenewalIntervalInSeconds) {
        this.leaseRenewalIntervalInSeconds = leaseRenewalIntervalInSeconds;
    }

    public void setLeaseExpirationDurationInSeconds(final int leaseExpirationDurationInSeconds) {
        this.leaseExpirationDurationInSeconds = leaseExpirationDurationInSeconds;
    }

    public void setVirtualHostName(final String virtualHostName) {
        this.virtualHostName = virtualHostName;
    }

    public void setSecureVirtualHostName(final String secureVirtualHostName) {
        this.secureVirtualHostName = secureVirtualHostName;
    }

    public void setASGName(final String ASGName) {
        this.ASGName = ASGName;
    }

    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    public void setMetaDataMap(final Map<String, String> metaDataMap) {
        this.metaDataMap = metaDataMap;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setStatusPageUrlPath(final String statusPageUrlPath) {
        this.statusPageUrlPath = statusPageUrlPath;
    }

    public void setStatusPageUrl(final String statusPageUrl) {
        this.statusPageUrl = statusPageUrl;
    }

    public void setHomePageUrlPath(final String homePageUrlPath) {
        this.homePageUrlPath = homePageUrlPath;
    }

    public void setHomePageUrl(final String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public void setHealthCheckUrlPath(final String healthCheckUrlPath) {
        this.healthCheckUrlPath = healthCheckUrlPath;
    }

    public void setHealthCheckUrl(final String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    public void setSecureHealthCheckUrl(final String secureHealthCheckUrl) {
        this.secureHealthCheckUrl = secureHealthCheckUrl;
    }

    public void setDefaultAddressResolutionOrder(final String[] defaultAddressResolutionOrder) {
        this.defaultAddressResolutionOrder = defaultAddressResolutionOrder;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }
}
