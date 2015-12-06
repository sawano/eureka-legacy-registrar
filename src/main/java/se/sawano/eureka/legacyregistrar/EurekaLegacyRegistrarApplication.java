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

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.discovery.DefaultEurekaClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.sawano.eureka.legacyregistrar.boot.EnableEurekaLegacyRegistrar;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Collections.unmodifiableList;
import static se.sawano.java.commons.lang.validate.Validate.isFalse;
import static se.sawano.java.commons.lang.validate.Validate.validState;

/**
 * Use this class if you are running a pure Eureka solution. If you are using Spring Boot please see {@link EnableEurekaLegacyRegistrar}.
 */
public class EurekaLegacyRegistrarApplication {

    public static void main(String[] args) {
        final EurekaLegacyRegistrarApplication application = new EurekaLegacyRegistrarApplication();
        application.start();
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            application.shutdown();
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AtomicBoolean started = new AtomicBoolean(false);
    private final List<LegacyClient> legacyClients = new ArrayList<>();

    public void start() {
        validState(!isStarted(), "Application has already been started");

        logger.debug("Starting legacy registrar...");
        init();
        started.getAndSet(true);
        logger.debug("Started successfully");
    }

    public boolean isStarted() {
        return started.get();
    }

    public void shutdown() {
        validState(isStarted(), "Application has not been started yet");

        logger.debug("Shutting down legacy registrar...");
        legacyClients.forEach(this::shutdown);
        started.getAndSet(false);
        logger.debug("Shut down successfully");
    }

    private void init() {
        final List<String> namespaces = getNamespaces(dynamicPropertyFactory());
        isFalse(namespaces.isEmpty(), "No applications configured");

        namespaces
                .stream()
                .map(this::createLegacyClient)
                .forEach(legacyClients::add);
    }

    private DynamicPropertyFactory dynamicPropertyFactory() {
        final DynamicStringProperty eurekaPropsFile = DynamicPropertyFactory
                .getInstance().getStringProperty("eureka.client.props", "eureka-client");
        try {
            ConfigurationManager.loadCascadedPropertiesFromResources(eurekaPropsFile.get());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return DynamicPropertyFactory.getInstance();
    }

    private List<String> getNamespaces(final DynamicPropertyFactory instance) {
        final List<String> namespaces = new ArrayList<>();
        final String prefix = "eureka.legacy.app";
        for (int i = 0; ; i++) {
            final String namespace = prefix + i;
            final DynamicStringProperty property = instance.getStringProperty(namespace + ".name", null);
            if (property.getValue() != null) {
                namespaces.add(namespace);
            }
            else {
                break;
            }
        }
        return unmodifiableList(namespaces);
    }

    private LegacyClient createLegacyClient(final String namespace) {
        logger.debug("Creating legacy instance for namespace: '{}'", namespace);

        final LegacyClient client = new LegacyClient(new PropertiesLegacyInstanceConfig(namespace), new DefaultEurekaClientConfig());
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
