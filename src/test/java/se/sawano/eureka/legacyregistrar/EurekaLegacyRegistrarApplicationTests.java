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
import se.sawano.java.commons.lang.validate.IllegalStateValidationException;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EurekaLegacyRegistrarApplicationTests {

    volatile boolean failed = false;

    @Test
    public void should_create_start_and_shutdown_application() throws Exception {
        final EurekaLegacyRegistrarApplication application = application();
        final Thread thread = new Thread(() -> {
            application.start();
            try {
                new CountDownLatch(1).await();
            } catch (InterruptedException e) {
                application.shutdown();
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
        thread.join();

        assertFalse(application.isStarted());
    }

    @Test
    @Ignore("Just here to serve as an example")
    public void should_start_application() throws Exception {
        EurekaLegacyRegistrarApplication.main(new String[0]);
    }

    @Test
    public void should_throw_exception_if_trying_to_start_an_already_started_application() {
        final EurekaLegacyRegistrarApplication application = application();
        application.start();

        try {
            application.start();
        } catch (IllegalStateValidationException e) {
            assertEquals("Application has already been started", e.getMessage());
        }
    }

    @Test
    public void should_throw_exception_if_trying_to_shutdown_an_application_that_is_not_started() {
        try {
            application().shutdown();
        } catch (IllegalStateValidationException e) {
            assertEquals("Application has not been started yet", e.getMessage());
        }
    }

    private EurekaLegacyRegistrarApplication application() {return new EurekaLegacyRegistrarApplication();}

}
