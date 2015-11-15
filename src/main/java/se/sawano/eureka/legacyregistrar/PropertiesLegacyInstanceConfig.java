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

package se.sawano.eureka.legacyregistrar;

import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.config.DynamicPropertyFactory;

import java.util.Map;

import static se.sawano.java.commons.lang.validate.Validate.notEmpty;
import static se.sawano.java.commons.lang.validate.Validate.notNull;

/**
 * Instance config based on the standard Eureka properties configuration.
 *
 * @see com.netflix.appinfo.PropertiesInstanceConfig
 */
public class PropertiesLegacyInstanceConfig implements LegacyInstanceConfig {
    private static final String PROPERTY_HOSTNAME = "hostName";
    private static final String PROPERTY_IP_ADDRESS = "ipAddress";

    private final MyDataCenterInstanceConfig config;

    public PropertiesLegacyInstanceConfig(final String namespace) {
        notEmpty(namespace);
        config = new MyDataCenterInstanceConfig(normalize(namespace));
    }

    public PropertiesLegacyInstanceConfig(final String namespace, final DataCenterInfo dataCenterInfo) {
        notEmpty(namespace);
        notNull(dataCenterInfo);
        config = new MyDataCenterInstanceConfig(normalize(namespace), dataCenterInfo);
    }

    private static String normalize(final String namespace) {
        final String trimmed = namespace.trim();
        if (trimmed.endsWith(".")) {
            return trimmed;
        }
        return trimmed + ".";
    }

    @Override
    public String getHostName(boolean refresh) {
        return nonNullProperty(PROPERTY_HOSTNAME);
    }

    @Override
    public String getIpAddress() {
        return property(PROPERTY_IP_ADDRESS);
    }

    @Override
    public DataCenterInfo getDataCenterInfo() {
        return config.getDataCenterInfo();
    }

    @Override
    public boolean isInstanceEnabledOnit() {
        return config.isInstanceEnabledOnit();
    }

    @Override
    public int getNonSecurePort() {
        return config.getNonSecurePort();
    }

    @Override
    public int getSecurePort() {
        return config.getSecurePort();
    }

    @Override
    public boolean isNonSecurePortEnabled() {
        return config.isNonSecurePortEnabled();
    }

    @Override
    public boolean getSecurePortEnabled() {
        return config.getSecurePortEnabled();
    }

    @Override
    public int getLeaseRenewalIntervalInSeconds() {
        return config.getLeaseRenewalIntervalInSeconds();
    }

    @Override
    public int getLeaseExpirationDurationInSeconds() {
        return config.getLeaseExpirationDurationInSeconds();
    }

    @Override
    public String getVirtualHostName() {
        return config.getVirtualHostName();
    }

    @Override
    public String getSecureVirtualHostName() {
        return config.getSecureVirtualHostName();
    }

    @Override
    public String getASGName() {
        return config.getASGName();
    }

    @Override
    public Map<String, String> getMetadataMap() {
        return config.getMetadataMap();
    }

    @Override
    public String getInstanceId() {
        return config.getInstanceId();
    }

    @Override
    public String getAppname() {
        return config.getAppname();
    }

    @Override
    public String getAppGroupName() {
        return config.getAppGroupName();
    }

    @Override
    public String getStatusPageUrlPath() {
        return config.getStatusPageUrlPath();
    }

    @Override
    public String getStatusPageUrl() {
        return config.getStatusPageUrl();
    }

    @Override
    public String getHomePageUrlPath() {
        return config.getHomePageUrlPath();
    }

    @Override
    public String getHomePageUrl() {
        return config.getHomePageUrl();
    }

    @Override
    public String getHealthCheckUrlPath() {
        return config.getHealthCheckUrlPath();
    }

    @Override
    public String getHealthCheckUrl() {
        return config.getHealthCheckUrl();
    }

    @Override
    public String getSecureHealthCheckUrl() {
        return config.getSecureHealthCheckUrl();
    }

    @Override
    public String getNamespace() {
        return config.getNamespace();
    }

    private String nonNullProperty(final String property) {
        return notNull(property(property), "Property %s%s cannot be null", getNamespace(), property);
    }

    private String property(final String property) {
        return DynamicPropertyFactory.getInstance().getStringProperty(getNamespace() + property, null).get();
    }

}
