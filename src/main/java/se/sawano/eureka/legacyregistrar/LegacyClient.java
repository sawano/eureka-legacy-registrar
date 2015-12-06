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

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClientConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static se.sawano.java.commons.lang.validate.Validate.notNull;
import static se.sawano.java.commons.lang.validate.Validate.validState;

public class LegacyClient {

    private DiscoveryClient client;
    private final LegacyInstanceConfig instanceConfig;
    private final EurekaClientConfig clientConfig;

    public LegacyClient(final LegacyInstanceConfig instanceConfig, final EurekaClientConfig clientConfig) {
        this.instanceConfig = notNull(instanceConfig);
        this.clientConfig = notNull(clientConfig);
    }

    public LegacyInstanceConfig instanceConfig() {
        return instanceConfig;
    }

    public EurekaClientConfig clientConfig() {
        return clientConfig;
    }

    public DiscoveryClient client() {
        return client;
    }

    @PostConstruct
    public void init() {
        validState(client == null, "Client has already been initialized");
        final ApplicationInfoManager appInfoManager = new ApplicationInfoManager(instanceConfig);
        client = new DiscoveryClient(appInfoManager, clientConfig);
    }

    @PreDestroy
    public void shutdown() {
        validState(client != null, "Client has not been initialized");
        client.shutdown();
    }
}
