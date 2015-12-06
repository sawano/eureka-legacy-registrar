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

import com.netflix.discovery.EurekaClientConfig;

import java.util.List;

public interface LegacyClientConfig extends EurekaClientConfig {

    /**
     * Same as the {@link EurekaClientConfig#shouldUseDnsForFetchingServiceUrls()} except that this value can be overridden by the value specified in {@link
     * LegacyClientProperties#isShouldUseDns()})}.
     *
     * @see EurekaClientConfig#shouldUseDnsForFetchingServiceUrls()
     */
    @Override
    boolean shouldUseDnsForFetchingServiceUrls();

    /**
     * Same as the {@link EurekaClientConfig#shouldPreferSameZoneEureka()} except that this value can be overridden by the value specified in {@link LegacyClientProperties#isPreferSameZone()})}.
     *
     * @see EurekaClientConfig#shouldPreferSameZoneEureka()
     */
    @Override
    boolean shouldPreferSameZoneEureka();

    /**
     * Same as the {@link EurekaClientConfig#getEurekaServerServiceUrls(String)}  except that this value can be overridden by the value specified in {@link
     * LegacyClientProperties#getServiceUrlDefault()}.
     *
     * @see EurekaClientConfig#getEurekaServerServiceUrls(String)
     */
    @Override
    List<String> getEurekaServerServiceUrls(String myZone);

    /**
     * Same as the {@link EurekaClientConfig#getDecoderName()} except that this value can be overridden by the value specified in {@link LegacyClientProperties#getDecoderName()}.
     *
     * @see EurekaClientConfig#getDecoderName()
     */
    @Override
    String getDecoderName();

}
