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

import se.sawano.eureka.legacyregistrar.LegacyClient;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static se.sawano.java.commons.lang.validate.Validate.noNullElements;

public class LegacyClients {

    private final List<LegacyClient> clients;

    public LegacyClients(final List<LegacyClient> clients) {
        noNullElements(clients);
        this.clients = unmodifiableList(new ArrayList<>(clients));
    }

    public List<LegacyClient> clients() {
        return clients;
    }
}
