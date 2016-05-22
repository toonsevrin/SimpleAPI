/*
 *    Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.simpleapi.api.redis;

import com.exorath.simpleapi.api.manager.Manager;

/**
 * The redis manager is responsible for making connections with the redis pool and publishing messages on redis.
 * Created by Toon Sevrin on 5/17/2016.
 */
public interface RedisManager extends Manager{
      /**
      * Gets the redis pool. The redis pool is used to get Jedis resources.
     * @return the redis pool
     */
    RedisPool getRedisPool();

    /**
     * True if the jedis pool is enabled (false if redis is not enabled or could not be found)
     * @return true if the jedis pool is enabled
     */
    boolean isEnabled();

    /**
     * Publishes a message to a specific redis channel.
     * @param channel channel to publish message to
     * @param message message to publish on channel
     */
    void publish(String channel, String message);
}
