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

import com.netflix.discovery.DefaultEurekaClientConfig;
import org.junit.Test;
import se.sawano.java.commons.lang.validate.IllegalStateValidationException;

import static org.junit.Assert.assertEquals;

public class LegacyClientTest {

    @Test
    public void should_start() {
        createClient().init();
    }

    @Test
    public void should_throw_exception_when_initializing_twice() {
        try {
            final LegacyClient client = createClient();
            client.init();
            client.init();
        } catch (IllegalStateValidationException e) {
            assertEquals("Client has already been initialized", e.getMessage());
        }
    }

    @Test
    public void should_throw_exception_when_trying_to_shutdown_uninitialized_client() {
        try {
            createClient().shutdown();
        } catch (IllegalStateValidationException e) {
            assertEquals("Client has not been initialized", e.getMessage());
        }
    }

    private LegacyClient createClient() {return new LegacyClient(new PropertiesLegacyInstanceConfig("eureka.legacy.app1"), new DefaultEurekaClientConfig());}
}
