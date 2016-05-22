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

import com.exorath.simpleapi.api.database.DBData;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import com.mongodb.MongoNamespace;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class DBDataImpl implements DBData{
    private Document bson;
    private String id;

    private volatile MongoNamespace namespace;

    private volatile boolean loaded = false;

    public DBDataImpl(Plugin plugin, String collectionName, String id, boolean sync) {
        this.id = id;
        if (sync)
            SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(plugin, collectionName, c ->
                    setup(c.getNamespace(), c.find(new Document("_id", id)).first()));
        else
            Bukkit.getScheduler().runTaskAsynchronously(SimpleAPIImpl.getInstance(), () ->
                    SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(plugin, collectionName, c ->
                            setup(c.getNamespace(), c.find(new Document("_id", id)).first())));
    }

    public DBDataImpl(MongoNamespace namespace, Document bson) {
        setup(namespace, bson);
    }

    private void setup(MongoNamespace namespace, Document bson) {
        this.namespace = namespace;
        if (bson == null) {
            this.bson = new DBDataDocument(new Document("_id", id.toString()));
            SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(namespace.getDatabaseName(), namespace.getCollectionName(), c -> c.insertOne(new Document(this.bson)));
        } else
            this.bson = new DBDataDocument(bson);
        id = this.bson.getString("_id");
        loaded = true;
    }

    @Override
    public void put(String key, Object value) {
        Bukkit.getScheduler().runTaskAsynchronously(SimpleAPIImpl.getInstance(), () ->
                SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(namespace.getDatabaseName(), namespace.getCollectionName(), c -> c.findOneAndUpdate(new Document("_id", id), new Document("$set", new Document(key, value))))
        );
        bson.put(key, value);
    }

    @Override
    public <T> T as(String key, Class<T> clazz) {
        return (T) bson.get("key");
    }

    @Override
    public Object get(String key) {
        return bson.get(key);
    }

    @Override
    public Object get(String key, Object def) {
        Object obj = get(key);
        return obj == null ? def : obj;
    }

    @Override
    public String getString(String key, String def) {
        return (String) get(key, def);
    }

    @Override
    public String getString(String key) {
        return (String) get(key);
    }

    @Override
    public Integer getInt(String key, Integer def) {
        return (Integer) get(key, def);
    }

    @Override
    public Integer getInt(String key) {
        return (Integer) get(key);
    }

    @Override
    public Boolean getBoolean(String key, Boolean def) {
        return (Boolean) get(key, def);
    }

    @Override
    public Boolean getBoolean(String key) {
        return (Boolean) get(key);
    }

    @Override
    public Long getLong(String key, Long def) {
        return (Long) get(key, def);
    }

    @Override
    public Long getLong(String key) {
        return (Long) get(key);
    }

    @Override
    public Double getDouble(String key, Double def) {
        return (Double) get(key, def);
    }

    @Override
    public Double getDouble(String key) {
        return (Double) get(key);
    }

    @Override
    public Date getDate(String key, Date def) {
        return (Date) get(key, def);
    }

    @Override
    public Date getDate(String key) {
        return (Date) get(key);
    }

    @Override
    public void incrementInt(String key, int amount) {
        bson.put(key, getInt(key) + amount);
        Bukkit.getScheduler().runTaskAsynchronously(SimpleAPIImpl.getInstance(), () -> {
            SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(namespace.getDatabaseName(), namespace.getCollectionName(), c -> c.updateOne(new Document("_id", id), new Document("$inc", new Document(key, amount))));
        });
    }

    @Override
    public void incrementDouble(String key, double amount) {
        bson.put(key, getDouble(key) + amount);
        Bukkit.getScheduler().runTaskAsynchronously(SimpleAPIImpl.getInstance(), () -> {
            SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(namespace.getDatabaseName(), namespace.getCollectionName(), c -> c.updateOne(new Document("_id", id), new Document("$inc", new Document(key, amount))));
        });
    }

    @Override
    public void remove(String key) {
        bson.remove(key);
        Bukkit.getScheduler().runTaskAsynchronously(SimpleAPIImpl.getInstance(), () -> {
            SimpleAPIImpl.getDatabaseManager().withCollectionConsumer(namespace.getDatabaseName(), namespace.getCollectionName(), c -> c.updateOne(new Document("_id", id), new Document("$unset", new Document(key, 0))));
        });
    }

    @Override
    public boolean contains(String key) {
        return bson.containsKey(key);
    }

    public String getId() {
        return id;
    }

    public UUID getUuid() {
        return UUID.fromString(id.toString());
    }

    public static class DBDataDocument extends Document {
        public DBDataDocument() {}

        public DBDataDocument(Document document) {
            super(document);
        }

        @Override
        public Object get(Object key) {
            if (key instanceof String && ((String) key).contains("."))
                return getDoc(this, (String) key, false).get(((String) key).substring(((String) key).lastIndexOf(".") + 1));
            return super.get(key);
        }

        @Override
        public Object put(String key, Object value) {
            if (key instanceof String && ((String) key).contains("."))
                return getDoc(this, key, true).put(((String) key).substring(((String) key).lastIndexOf(".") + 1), value);
            return super.put(key, value);
        }

        //TODO: Simplify method
        @Override
        public boolean containsKey(Object key) {
            if (key instanceof String && ((String) key).contains(".")) {
                String remainingKeys = (String) key;
                Document previous = this;
                while (true) {
                    String[] split = remainingKeys.split("\\.", 2);
                    Object next = previous.get(split[0]);
                    if (next == null)
                        return false;
                    if (!(next instanceof Document))
                        return !remainingKeys.contains(".");
                    if (!remainingKeys.contains("."))
                        return previous.containsKey(remainingKeys);
                    remainingKeys = split[1];
                    previous = (Document) next;
                }
            }
            return super.containsKey(key);
        }

        private Document getDoc(Document previous, String remainingKeys, boolean createDoc) {
            if (!remainingKeys.contains("."))
                return previous;
            String[] split = remainingKeys.split("\\.", 2);
            Object next = previous.get(split[0]);
            if (createDoc && next == null) {
                next = new Document();
                previous.put(split[0], next);
            }
            return (next instanceof Document) ? getDoc((Document) next, split[1], createDoc) : previous;
        }
    }

}
