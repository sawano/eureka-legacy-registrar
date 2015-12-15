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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootConfigurationTest.Conf.class)
public class BootConfigurationTest {

    @Autowired
    private LegacyInstances instances;
    @Autowired
    private LegacyClientProperties properties;

    @Test
    public void should_load_expected_properties() {
        final List<SpringBootInstanceConfig> configs = instances.withName("legacy-app");
        assertEquals(2, configs.size());

        assertEquals(8083, instances.withInstanceId("10.0.1.15:legacy-app:1").get().getNonSecurePort());
        System.out.println(instances);

    }

    @Configuration
    @EnableConfigurationProperties({LegacyInstances.class, LegacyClientProperties.class})
    static class Conf {
    }

}
