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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.sawano.java.commons.lang.validate.IllegalArgumentValidationException;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LegacyInstancesTest {

    @Rule
    public final ExpectedException expectation = ExpectedException.none();

    @Test
    public void should_throw_exception_on_duplicate_instance_ids() {
        expectation.expect(IllegalArgumentValidationException.class);
        expectation.expectMessage(is("Instance id must be unique. Found 2 with id 'id-1'"));

        instances(instance("app-1", "id-1"), instance("app-1", "id-1"))
                .withInstanceId("id-1");
    }

    @Test
    public void should_return_instance_for_given_id() {
        final Optional<SpringBootInstanceConfig> config = instances(instance("app-1", "id-1"),
                                                                    instance("app-1", "id-2"),
                                                                    instance("app-2", "id-3")).withInstanceId("id-2");

        assertTrue(config.isPresent());
        assertEquals("id-2", config.get().getInstanceId());
    }

    @Test
    public void should_return_instance_for_given_name() {
        final List<SpringBootInstanceConfig> configs = instances(instance("app-1", "id-1"),
                                                                 instance("app-2", "id-2")).withName("app-2");

        assertEquals(1, configs.size());
        assertEquals("app-2", configs.get(0).getAppname());
    }

    @Test
    public void should_return_empty_if_no_insatnce_exists_for_given_id() {
        final Optional<SpringBootInstanceConfig> config = instances(instance("app-1", "id-1"),
                                                                    instance("app-1", "id-2"),
                                                                    instance("app-2", "id-3")).withInstanceId("id-4");

        assertFalse(config.isPresent());
    }

    private LegacyInstances instances(final SpringBootInstanceConfig... configs) {
        return new LegacyInstances(asList(configs));
    }

    private SpringBootInstanceConfig instance(final String appName, final String instanceId) {
        final SpringBootInstanceConfig config = new SpringBootInstanceConfig();
        config.setAppName(appName);
        config.setInstanceId(instanceId);
        return config;
    }
}
