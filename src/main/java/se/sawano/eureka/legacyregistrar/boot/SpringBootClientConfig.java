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

import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.transport.EurekaTransportConfig;

import java.util.List;

import static java.util.Optional.ofNullable;
import static se.sawano.java.commons.lang.validate.Validate.notNull;

public class SpringBootClientConfig implements EurekaClientConfig {

    private final DefaultEurekaClientConfig defaultEurekaClientConfig = new DefaultEurekaClientConfig();
    private final LegacyClientProperties properties;

    public SpringBootClientConfig(final LegacyClientProperties properties) {
        this.properties = notNull(properties);
    }

    @Override
    public int getRegistryFetchIntervalSeconds() {
        return defaultEurekaClientConfig.getRegistryFetchIntervalSeconds();
    }

    @Override
    public int getInstanceInfoReplicationIntervalSeconds() {
        return defaultEurekaClientConfig.getInstanceInfoReplicationIntervalSeconds();
    }

    @Override
    public int getInitialInstanceInfoReplicationIntervalSeconds() {
        return defaultEurekaClientConfig.getInitialInstanceInfoReplicationIntervalSeconds();
    }

    @Override
    public int getEurekaServiceUrlPollIntervalSeconds() {
        return defaultEurekaClientConfig.getEurekaServiceUrlPollIntervalSeconds();
    }

    @Override
    public String getProxyHost() {
        return defaultEurekaClientConfig.getProxyHost();
    }

    @Override
    public String getProxyPort() {
        return defaultEurekaClientConfig.getProxyPort();
    }

    @Override
    public String getProxyUserName() {
        return defaultEurekaClientConfig.getProxyUserName();
    }

    @Override
    public String getProxyPassword() {
        return defaultEurekaClientConfig.getProxyPassword();
    }

    @Override
    public boolean shouldGZipContent() {
        return defaultEurekaClientConfig.shouldGZipContent();
    }

    @Override
    public int getEurekaServerReadTimeoutSeconds() {
        return defaultEurekaClientConfig.getEurekaServerReadTimeoutSeconds();
    }

    @Override
    public int getEurekaServerConnectTimeoutSeconds() {
        return defaultEurekaClientConfig.getEurekaServerConnectTimeoutSeconds();
    }

    @Override
    public String getBackupRegistryImpl() {
        return defaultEurekaClientConfig.getBackupRegistryImpl();
    }

    @Override
    public int getEurekaServerTotalConnections() {
        return defaultEurekaClientConfig.getEurekaServerTotalConnections();
    }

    @Override
    public int getEurekaServerTotalConnectionsPerHost() {
        return defaultEurekaClientConfig.getEurekaServerTotalConnectionsPerHost();
    }

    @Override
    public String getEurekaServerURLContext() {
        return defaultEurekaClientConfig.getEurekaServerURLContext();
    }

    @Override
    public String getEurekaServerPort() {
        return defaultEurekaClientConfig.getEurekaServerPort();
    }

    @Override
    public String getEurekaServerDNSName() {
        return defaultEurekaClientConfig.getEurekaServerDNSName();
    }

    @Override
    public boolean shouldUseDnsForFetchingServiceUrls() {
        return ofNullable(properties.isShouldUseDns())
                .orElseGet(defaultEurekaClientConfig::shouldUseDnsForFetchingServiceUrls);
    }

    @Override
    public boolean shouldRegisterWithEureka() {
        return defaultEurekaClientConfig.shouldRegisterWithEureka();
    }

    @Override
    public boolean shouldPreferSameZoneEureka() {
        return ofNullable(properties.isPreferSameZone())
                .orElseGet(defaultEurekaClientConfig::shouldPreferSameZoneEureka);
    }

    @Override
    public boolean allowRedirects() {
        return defaultEurekaClientConfig.allowRedirects();
    }

    @Override
    public boolean shouldLogDeltaDiff() {
        return defaultEurekaClientConfig.shouldLogDeltaDiff();
    }

    @Override
    public boolean shouldDisableDelta() {
        return defaultEurekaClientConfig.shouldDisableDelta();
    }

    @Override
    public String fetchRegistryForRemoteRegions() {
        return defaultEurekaClientConfig.fetchRegistryForRemoteRegions();
    }

    @Override
    public String getRegion() {
        return defaultEurekaClientConfig.getRegion();
    }

    @Override
    public String[] getAvailabilityZones(String region) {
        return defaultEurekaClientConfig.getAvailabilityZones(region);
    }

    @Override
    public List<String> getEurekaServerServiceUrls(String myZone) {
        return ofNullable(properties.getServiceUrlDefault())
                .orElseGet(() -> defaultEurekaClientConfig.getEurekaServerServiceUrls(myZone));
    }

    @Override
    public boolean shouldFilterOnlyUpInstances() {
        return defaultEurekaClientConfig.shouldFilterOnlyUpInstances();
    }

    @Override
    public int getEurekaConnectionIdleTimeoutSeconds() {
        return defaultEurekaClientConfig.getEurekaConnectionIdleTimeoutSeconds();
    }

    @Override
    public boolean shouldFetchRegistry() {
        return defaultEurekaClientConfig.shouldFetchRegistry();
    }

    @Override
    public String getRegistryRefreshSingleVipAddress() {
        return defaultEurekaClientConfig.getRegistryRefreshSingleVipAddress();
    }

    @Override
    public int getHeartbeatExecutorThreadPoolSize() {
        return defaultEurekaClientConfig.getHeartbeatExecutorThreadPoolSize();
    }

    @Override
    public int getHeartbeatExecutorExponentialBackOffBound() {
        return defaultEurekaClientConfig.getHeartbeatExecutorExponentialBackOffBound();
    }

    @Override
    public int getCacheRefreshExecutorThreadPoolSize() {
        return defaultEurekaClientConfig.getCacheRefreshExecutorThreadPoolSize();
    }

    @Override
    public int getCacheRefreshExecutorExponentialBackOffBound() {
        return defaultEurekaClientConfig.getCacheRefreshExecutorExponentialBackOffBound();
    }

    @Override
    public String getDollarReplacement() {
        return defaultEurekaClientConfig.getDollarReplacement();
    }

    @Override
    public String getEscapeCharReplacement() {
        return defaultEurekaClientConfig.getEscapeCharReplacement();
    }

    @Override
    public boolean shouldOnDemandUpdateStatusChange() {
        return defaultEurekaClientConfig.shouldOnDemandUpdateStatusChange();
    }

    @Override
    public String getEncoderName() {
        return defaultEurekaClientConfig.getEncoderName();
    }

    @Override
    public String getDecoderName() {
        return ofNullable(properties.getDecoderName())
                .orElseGet(defaultEurekaClientConfig::getDecoderName);
    }

    @Override
    public String getClientDataAccept() {
        return defaultEurekaClientConfig.getClientDataAccept();
    }

    @Override
    public String getExperimental(String name) {
        return defaultEurekaClientConfig.getExperimental(name);
    }

    @Override
    public EurekaTransportConfig getTransportConfig() {
        return defaultEurekaClientConfig.getTransportConfig();
    }
}
