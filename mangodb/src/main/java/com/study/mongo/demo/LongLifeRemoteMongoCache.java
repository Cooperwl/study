/**
 * @Copyright (C) 2016 本内容属于商业秘密，易微行(北京)科技有限公司保留版权等所有权利.
 */
package com.study.mongo.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * MongoDB 数据操作
 * 
 * @author wangliang
 * @date: 2016-9-6 下午3:32:19
 */
public class LongLifeRemoteMongoCache {

    private MongoConnectionFactory factory;
    private MongoDatabase db;
    
    public LongLifeRemoteMongoCache(MongoConnectionFactory factory, String dbName) {
        this.factory = factory;
        this.factory.init();
        this.db = this.factory.getDB(dbName);
    }
    
    /**
     * 清空集合
     * @param collectionName    集合名称
     */
    public void clearCollection(String collectionName){
        db.getCollection(collectionName).drop();
    }
    
    /**
     * 创建数据索引
     * @param collectionName 集合名称
     * @param dbIndex 数据索引
     */
    public void createIndex(String collectionName, Document dbIndex){
        db.getCollection(collectionName).createIndex(dbIndex);
    }
    /**
     * 保存对象
     * @param collectionName 集合名称
     * @param dbObject 需要保存的对象
     */
    public synchronized void save(String collectionName, Document dbObject) {
        db.getCollection(collectionName).insertOne(dbObject);
    }
    
    /**
     * 更新数据
     * @param collection    集合名称
     * @param query     更新条件
     * @param update    新对象
     * @param upsert    没有到查询过滤器相匹配的结果，新文件是否被插入
     * @param multi 是否是多结果操作
     * @return
     */
    public synchronized UpdateResult update(String collection, Document query, Document update, boolean upsert, boolean multi) {
        UpdateOptions updateOptions = new UpdateOptions();
        updateOptions.upsert(upsert);
        if (multi) {
            return db.getCollection(collection).updateMany(query, update, updateOptions);
        }else {
            return db.getCollection(collection).updateOne(query, update, updateOptions);
        }
    }
    
    /**
     * 根据条件删除数据
     * @param collectionName    集合名称
     * @param filter    过滤条件
     * @return
     */
    public synchronized DeleteResult remove(String collectionName, Document filter){
        return db.getCollection(collectionName).deleteMany(filter);
    }
    /** 根据过滤条件统计数量
     * @param collection 集合名称
     * @param query 查询条件
     * @return
     */
    public synchronized Long count(String collection, Document query) {
        return db.getCollection(collection).count(query);
    }
    /**
     * 聚合查询，查询一个点附近的点，并返回每一个点到该中心点的距离，在坐标表分片的情况下$nearSphere不支持， 可以使用该方法进行查询
     * 
     * @param collectionName 集合名称
     * @param field 查询条件
     * @param point 中心点坐标
     * @param limit 返回记录数量限制
     * @param maxDistance 最大距离
     * @return 非NULL的list
     */
    public List<Document> geoNear(String collectionName, String field, Coords point, int limit, long maxDistance) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        BasicDBList coordinates = new BasicDBList();
        coordinates.put(0, point.getLongitude().doubleValue());
        coordinates.put(1, point.getLatitude().doubleValue());

        Document dbObject = new Document();
        Document searchObj = new Document("$near",
                new Document("$geometry",new Document("coordinates",coordinates).append("type", "Point"))
                .append("$maxDistance",maxDistance));
        dbObject.put(field,searchObj);
        final List<Document> result = new ArrayList<>();
        FindIterable<Document> documents = collection.find(dbObject);
        documents.limit(limit);
        documents.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                result.add(document);
            }
        });
        return result;
    }

    /**
     * 查询在圆形区域内的坐标点，需要指定中心点坐标和半径，半径单位是米
     * 
     * @param collection 集合名称
     * @param locationField 坐标字段
     * @param center 中心点坐标[经度，纬度]
     * @param radius 半径 单位:米
     * @param fields 查询字段
     * @param query 查询条件
     * @param limit 返回记录限制数量
     * @return 非NULL的list
     */
    public List<Document> withinCircle(String collection, String locationField, Coords center, long radius, Document fields, Document query, int limit) {
        return null;
    }

    /**
     * 指定一个点，返回该点附近的坐标点且是由近到远,$nearSphere 需要建立索引2dsphere 或者2d,并且支持GeoJSON和一般坐标对 注意:
     * $nearSphere在分片的集群中无效，使用geoNear
     * 
     * @param collection 集合名称
     * @param locationField 坐标字段
     * @param center 中心点坐标[经度，纬度]
     * @param minDistance 最近距离
     * @param maxDistance 最远距离
     * @param query 查询条件
     * @param fields 查询字段
     * @param limit 返回记录限制数量
     * @return 非NULL的list
     */
    public List<Document> nearSphere(String collection, String locationField, Coords center, long minDistance, long maxDistance, Document query,
            Document fields, int limit) {
        return null;
    }


    /**
     * 查询位于指定一个封闭多边形内的所有坐标点，给定的多边形坐标点必须首位相接形成封闭的多边形 如三角形 final LinkedList<double[]> polygon = new
     * LinkedList<>(); polygon.addLast(new double[] { 121.36, 31.18 }); polygon.addLast(new double[]
     * { 121.35, 31.36 }); polygon.addLast(new double[] { 121.39, 31.17 }); polygon.addLast(new
     * double[] { 121.36, 31.18 });
     * 
     * MongoDB将多边形的边界也作为查询形状的一部分
     * 
     * @param collection 集合名称
     * @param locationField 坐标字段
     * @param polygon 多边形坐标
     * @param fields 查询字段
     * @param query 查询条件
     * @param limit 返回记录限制数量
     * @return 非NULL的list
     */
    public List<Document> withinPolygon(String collection, String locationField, List<double[]> polygon, Document fields, Document query, int limit) {
        return null;
    }


    /**
     * 查询位于指定多个封闭多边形内的所有坐标点，给定的多边形坐标点必须首位相接形成封闭的多边形
     * 
     * @param collection 集合名称
     * @param locationField 坐标字段
     * @param polygons 多边形坐标 数组
     * @param fields 查询字段
     * @param query 查询条件
     * @param limit 返回记录限制数量
     * @return 非NULL的list
     */
    public List<Document> withinMultiPolygon(String collection, String locationField, List<List<double[]>> polygons, Document fields, Document query, int limit) {
        return null;
    }
}
