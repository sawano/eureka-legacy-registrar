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

import java.util.List;

@ConfigurationProperties(prefix = "eureka.legacy.client")
public class LegacyClientProperties {

    private Boolean preferSameZone;
    private Boolean shouldUseDns;
    private List<String> serviceUrlDefault;
    private String decoderName = "JacksonJson";

    public Boolean isPreferSameZone() {
        return preferSameZone;
    }

    public void setPreferSameZone(final Boolean preferSameZone) {
        this.preferSameZone = preferSameZone;
    }

    public Boolean isShouldUseDns() {
        return shouldUseDns;
    }

    public void setShouldUseDns(final Boolean shouldUseDns) {
        this.shouldUseDns = shouldUseDns;
    }

    public List<String> getServiceUrlDefault() {
        return serviceUrlDefault;
    }

    public void setServiceUrlDefault(final List<String> serviceUrlDefault) {
        this.serviceUrlDefault = serviceUrlDefault;
    }

    public String getDecoderName() {
        return decoderName;
    }

    public void setDecoderName(final String decoderName) {
        this.decoderName = decoderName;
    }
}
