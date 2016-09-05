package com.study.mongo.client;

import com.mongodb.client.MongoDatabase;

/**
 * Created by wangliang on 2016/9/4.
 */
public class MongoDB {
    private String db;
    private MongoObject client;

    public void setClient(MongoObject client) {
        this.client = client;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public MongoDB(MongoObject client, String db) {
        this.client = client;
        this.db = db;
    }

    public MongoDatabase excute() {
        return client.run().getDatabase(db);
    }
}
