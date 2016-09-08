package com.study.mongo.demo;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangliang on 2016/9/4.
 */
public class CarQueryDemo {

    private static MongoConnectionFactory factory = new MongoConnectionFactory("localhost",27017,"");
    private static LongLifeRemoteMongoCache mongoCache;

    public static void main(String[] args) {
        mongoCache = new LongLifeRemoteMongoCache(factory,"cars");
//        List<CarQueryDTO> data = getData();
//        for (CarQueryDTO car:  data){
//            Document carDoc = Document.parse(new Gson().toJson(car));
//            mongoCache.save("cars",carDoc);
//        }
//        mongoCache.createIndex("cars",new Document("location","2dsphere"));

//        mongoCache.clearCollection("cars");
//        Coords point = new Coords();
//        point.setLatitude(39.84484);
//        point.setLongitude(116.50413);
//        List<Document> near = mongoCache.geoNear("cars", "location", point, 100, 1000);
//        String json = near.get(0).toJson();
//        CarQueryDTO dto = new Gson().fromJson(json,CarQueryDTO.class);
////        System.out.println(dto.getCarId());
//
//        dto.setLongitude(116D);
//        dto.setLatitude(39D);
//        dto.setLocation(new Location("Point", new Double[]{116D,39D}));
//        BasicDBList coordinates = new BasicDBList();
//        coordinates.put(0, 116D);
//        coordinates.put(1, 39D);
//        Document update = new Document("$set",
//                new Document("longitude",116D)
//                        .append("latitude",39D)
//                        .append("location",new Document("type","Point").append("coordinates",coordinates)));
//        UpdateResult updateResult = mongoCache.update("cars", new Document("carId", dto.getCarId()), update, true, true);
//        System.out.println(updateResult.getModifiedCount());

        Coords point = new Coords();
        point.setLatitude(39.958320);
        point.setLongitude(116.342214);
        List<Document> near = mongoCache.geoNear("cars", "location", point, 100, 500);
        for (Document doc : near){
            CarQueryDTO dto = new Gson().fromJson(doc.toJson(),CarQueryDTO.class);
            System.out.println(dto.getLatitude()+","+dto.getLongitude());
        }
    }

    public static List<CarQueryDTO> getData(){
        List<CarQueryDTO> list = new ArrayList<CarQueryDTO>();
        int statas = 0;
        double latitude;
        double longitude;
        Random random = new Random();
        int carId = 10000;
        for (int i = 0; i < 10; i++){
            latitude = 39 + Double.valueOf(random.nextInt(107852-79505) + 79505) / 100000;
            longitude = 116 + Double.valueOf(random.nextInt(58870-24001) + 24001) / 100000;
            System.out.println(latitude+","+longitude);
            CarQueryDTO carQuery = new CarQueryDTO("car_"+ (carId++) ,latitude,longitude);
            carQuery.setStatus(statas+"");
            list.add(carQuery);
            if (statas == 0){
                statas = 1;
            }else {
                statas = 0;
            }
        }
        return list;
    }
}
