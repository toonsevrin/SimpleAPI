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

package com.exorath.simpleapi.impl.redis;

import com.exorath.simpleapi.api.redis.RedisManager;
import com.exorath.simpleapi.api.redis.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * Created by Toon Sevrin on 5/17/2016.
 */
public class RedisManagerImpl implements RedisManager {
    RedisPool redisPool;

    public RedisManagerImpl() {
        redisPool = new RedisPoolImpl();
    }

    @Override
    public RedisPool getRedisPool() {
        return redisPool;
    }

    @Override
    public boolean isEnabled() {
        return redisPool.isEnabled();
    }

    @Override
    public void publish(String channel, String message) {
        if (isEnabled())
            new Thread((Runnable) () -> {
                try (Jedis jedis = redisPool.getResource()){
                    jedis.publish(channel, message);
                }
            }, "publisherThread").start();
    }
}
