package com.study.mongo.client;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
/*
 * 创建一个会话实现对mongoDB的原子操作
 *
 * @author:maybo
 *
 * @date:2016-2-1
 */
public abstract class AbstractMongoSession {
    private MongoDatabase db;
    private Class<?> clazz;
    private MongoCollection<Document> collection;

    public MongoCollection<Document> getCollection() {
        return this.collection;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoDatabase getDb() {
        return db;
    }

    protected MongoCollection<Document> Collection(Class<?> clazz) {
        this.clazz = clazz;
        Table table = (Table) clazz.getAnnotation(Table.class);
        String col = null;
        if (null != table && null != table.name()) {
            col = table.name();
        } else {
            col = clazz.getName();
        }
        this.collection = db.getCollection(col);
        return this.collection;
    }

    /*
     * 保存
     *
     * @param:实体
     *
     * @return:void
     */
    public abstract void save(Object obj);

    public abstract void saveMany(List<Object> obj);

    // 删除数据
    public abstract long delete(Object obj) throws Exception;

    public abstract long delete(Bson bson) throws Exception;

    // 删除数据
    public abstract long deleteMany(List<Object> objs);

    public abstract long deleteMany(Bson bson);

    // 修改数据
    public abstract long upate(Bson bson, Object obj);

    public abstract long update(Object obj);

    public abstract long upateMany(Bson bson, Object obj);

    public abstract long upateMany(Bson bson, List<Object> objs);

    public abstract long upateMany(List<Object> objs);

    // 查询数据
    public abstract Object find(Object obj);

    // 获取所有的数据
    public abstract List<Object> finds();

    // 条件查询数据
    public abstract List<Object> query(Bson bson);

    public abstract Object queryOne(Bson bson);

    public abstract List<Object> query(Bson bson, Bson sort);

    public abstract Object queryOne(Bson bson, Bson sort);

    public abstract List<Object> query(Bson bson, Bson sort, int limit);

    public abstract List<Object> query(Bson bson, Bson sort, int limit, int skip);

    public abstract List<Object> query(Bson bson, Bson sort, Bson filter);

    public abstract Object queryOne(Bson bson, Bson sort, Bson Filter);

    public abstract long count();

    public abstract long count(Bson bson);

}
