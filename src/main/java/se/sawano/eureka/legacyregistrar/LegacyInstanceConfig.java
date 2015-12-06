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

import com.netflix.appinfo.EurekaInstanceConfig;

public interface LegacyInstanceConfig extends EurekaInstanceConfig {

    /**
     * Gets the hostname associated with this legacy instance via configuration. This is different from a typical instance config which would return the name from the current host.
     *
     * @param refresh
     *         this parameter will have no effect
     *
     * @return the hostname
     *
     * @see EurekaInstanceConfig#getHostName(boolean)
     */
    @Override
    String getHostName(boolean refresh);

    /**
     * Gets the IP address associated with this legacy instance via configuration. This is different from a typical instance config which would return the address from the current host.
     *
     * @return th IP address
     *
     * @see EurekaInstanceConfig#getIpAddress()
     */
    @Override
    String getIpAddress();

}
