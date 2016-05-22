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

package com.exorath.simpleapi.impl.database;

import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.manager.Manager;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class DBManager implements Manager {
    public static final String GAME_API_DATABASE_NAME = "GameAPI";
    public static final String DATABASE_PREFIX = "gameapi_";
    private String host;

    public DBManager(String host){
        this.host = host;
        muteLogger();
    }
    private void muteLogger(){
        Logger.getLogger("org.mongodb.driver.connection").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver.management").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver.cluster").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver.protocol.insert").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver.protocol.query").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver.protocol.update").setLevel(Level.SEVERE);
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        Logger.getLogger("com.mongodb").setLevel(Level.SEVERE);
    }


    public  void withCollectionConsumer(Plugin plugin, String collectionName, MongoCollectionConsumer consumer){
        withCollectionConsumer(DATABASE_PREFIX + plugin.getName().replace(" ", "_"), collectionName, consumer);
    }

    public  void withCollectionConsumer(String collectionName, MongoCollectionConsumer consumer){
        withCollectionConsumer(DATABASE_PREFIX + GAME_API_DATABASE_NAME, collectionName, consumer);
    }

    public  void withCollectionConsumer(String dbName, String collectionName, MongoCollectionConsumer consumer){
        try (MongoClient client = new MongoClient(host)){
            consumer.consume(client.getDatabase(dbName).getCollection(collectionName));
        }
    }

    public void withDatabaseConsumer(String dbName, MongoDBConsumer consumer){
        try(MongoClient client = new MongoClient(host)) {
            consumer.consume(client.getDatabase(dbName));
        }
    }

    public void withClientConsumer(MongoClientConsumer consumer){
        try(MongoClient client = new MongoClient(host)){
            consumer.consume(client);
        }
    }
}
