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

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.redis.RedisPool;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Toon Sevrin on 5/17/2016.
 */
public class RedisPoolImpl implements RedisPool {
    private JedisPool pool;

    private String ip;
    private int port;

    private boolean enabled = false;

    public RedisPoolImpl(){
        File file = new File(SimpleAPI.getInstance().getDataFolder(), "redis.yml");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        this.ip = config.contains("ip") ? config.getString("ip") : "127.0.0.1";
        this.port = config.contains("port") ? config.getInt("port") : 6379;

        try {
            pool = new JedisPool(new JedisPoolConfig(), ip, port);
            pool.getResource().close();
            enabled = true;
            SimpleAPI.getInstance().log("Redis has been succesfully enabled by the GameAPI.");
        } catch (JedisConnectionException e) {
            SimpleAPI.getInstance().log("Could not connect to redis server. Make sure it is installed correctly if it is used.");
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Jedis getResource() {
        return (pool == null || !enabled) ?
             null : pool.getResource();
    }
}
