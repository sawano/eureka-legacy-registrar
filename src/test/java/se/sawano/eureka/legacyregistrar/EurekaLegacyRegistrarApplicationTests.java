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

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;

public class EurekaLegacyRegistrarApplicationTests {

    volatile boolean failed = false;

    @Test
    public void should_create_and_start_application() throws Exception {
        final EurekaLegacyRegistrarApplication application = new EurekaLegacyRegistrarApplication();
        final Thread thread = new Thread(() -> {
            application.start();
            try {
                new CountDownLatch(1).await();
            } catch (InterruptedException e) {
            }
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            failed = true;
        });
        thread.start();

        while (!application.isStarted() && !failed) {
            Thread.sleep(100);
        }
        assertFalse("Application failed to start", failed);
        thread.interrupt();
    }

    @Test
    @Ignore("Just here to serve as an example")
    public void should_start_application() throws Exception {
        EurekaLegacyRegistrarApplication.main(new String[0]);
    }
}
