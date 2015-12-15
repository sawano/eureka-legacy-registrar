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

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static se.sawano.java.commons.lang.validate.Validate.*;

@ConfigurationProperties(prefix = "eureka.legacy")
public class LegacyInstances {

    @Valid
    private List<SpringBootInstanceConfig> instances = new ArrayList<>();

    public LegacyInstances() {}

    LegacyInstances(final List<SpringBootInstanceConfig> instances) {
        noNullElements(instances);
        this.instances.addAll(instances);
    }

    public List<SpringBootInstanceConfig> getInstances() {
        return instances;
    }

    public List<SpringBootInstanceConfig> withName(final String appName) {
        notNull(appName);

        return instances.stream()
                        .filter(conf -> appName.equals(conf.getAppname()))
                        .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public Optional<SpringBootInstanceConfig> withInstanceId(final String instanceId) {
        notNull(instanceId);

        final List<SpringBootInstanceConfig> found = instances.stream()
                                                              .filter(conf -> instanceId.equals(conf.getInstanceId()))
                                                              .collect(toList());
        if (found.isEmpty()) {
            return Optional.empty();
        }

        isTrue(1 == found.size(), "Instance id must be unique. Found %d with id '%s'", found.size(), instanceId);

        return Optional.of(found.get(0));
    }
}
