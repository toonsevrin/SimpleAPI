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

import com.exorath.simpleapi.api.SimpleAPI;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Toon Sevrin on 5/17/2016.
 */
public abstract class RedisSubscriber {
    private String channel[];

    public RedisSubscriber(String... channel) {
        this.channel = channel;
        setupPubSub();
    }

    public abstract void onMessage(String channel, String message);

    private JedisPubSub setupPubSub() {
        final JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                RedisSubscriber.this.onMessage(channel, message);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Jedis jedis = SimpleAPI.getInstance().getManager(RedisManager.class).getRedisPool().getResource();
                    jedis.subscribe(jedisPubSub, channel);
                    jedis.quit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "subscriberThread").start();
        SimpleAPI.getInstance().addOnDisable(() -> jedisPubSub.unsubscribe());
        return jedisPubSub;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
