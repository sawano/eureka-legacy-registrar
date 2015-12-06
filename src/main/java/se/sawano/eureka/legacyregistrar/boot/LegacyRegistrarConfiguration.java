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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import se.sawano.eureka.legacyregistrar.LegacyClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static se.sawano.java.commons.lang.validate.Validate.isFalse;
import static se.sawano.java.commons.lang.validate.Validate.noNullElements;

@Configuration
@EnableConfigurationProperties({LegacyInstances.class, LegacyClientProperties.class})
public class LegacyRegistrarConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LegacyInstances instances;
    @Autowired
    private LegacyClientProperties properties;
    private List<LegacyClient> clients;

    @PostConstruct
    public void init() {
        isFalse(instances.getInstances().isEmpty(), "No applications configured. Make sure your application.yml is present and configured correctly");
        noNullElements(instances.getInstances());

        clients = instances.getInstances().stream()
                           .map(this::createInstance)
                           .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    @PreDestroy
    public void shutdown() {
        clients.forEach(this::shutdown);
    }

    private LegacyClient createInstance(final SpringBootInstanceConfig instanceConfig) {
        logger.debug("Creating legacy instance for app instance: '{}'", instanceConfig.getInstanceId());

        final LegacyClient client = new LegacyClient(instanceConfig, new SpringBootClientConfig(properties));
        client.init();
        return client;
    }

    private void shutdown(final LegacyClient client) {
        try {
            client.shutdown();
        } catch (Exception e) {
            // TODO cleanup/fix. Possibly same as: https://github.com/Netflix/eureka/issues/704
            e.printStackTrace();
        }
    }

}
