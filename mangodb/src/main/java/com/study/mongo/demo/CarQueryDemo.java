package com.study.mongo.demo;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
public class CarQueryDemo {

    private static MongoConnectionFactory factory = new MongoConnectionFactory("localhost",27017,"");
    private static MongoDatabase database;
    private static MongoCollection<Document> cars;

    public static void main(String[] args) {
        factory.init();
        database = factory.getDB("query");
        cars = database.getCollection("cars");
//        insertMany();
    }

    public static void createIndex(){
        cars.createIndex(new Document("location","2dsphere"));
    }

    public static void removeAll(){
        cars.drop();
    }

    public static void insertMany(){
        Gson gson=new Gson();
        List<CarQueryDTO> data = getData();
        List<Document> list = new ArrayList<Document>();
        for (CarQueryDTO dto : data){
            String json = gson.toJson(dto);
            Document document = Document.parse(json);
            list.add(document);
        }
        cars.insertMany(list);
    }

    public static List<CarQueryDTO> getData(){
        List<CarQueryDTO> list = new ArrayList<CarQueryDTO>();

        Double latitude = 108.8494751588;
        Double longitude = 34.2713220238;
        for (int i = 0; i < 100000; i++) {
            CarQueryDTO carQuery = new CarQueryDTO(latitude,longitude);
            list.add(carQuery);
        }
        return list;
    }
}
