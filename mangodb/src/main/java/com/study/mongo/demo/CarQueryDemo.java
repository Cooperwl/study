package com.study.mongo.demo;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.Document;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
public class CarQueryDemo {

    private static MongoConnectionFactory factory = new MongoConnectionFactory("localhost",27017,"");
    private static MongoDatabase database;
    private static MongoCollection<Document> cars;
    static double latitude = 34.271;
    static double longitude = 108.849;

    static DecimalFormat myformat=new DecimalFormat("0.0000");
    public static void main(String[] args) {
        factory.init();
        database = factory.getDB("query");
        cars = database.getCollection("cars");
//        insertMany();
//        createIndex();
        List<CarQueryDTO> carResult = near("loc", 108.86, 34.29, 5000);
        System.out.println(carResult.size());
    }


    public static List<CarQueryDTO> near(String field, double lng, double lat, int distance) {
        final List<CarQueryDTO> result = new ArrayList<CarQueryDTO>();
//        db.cars.find( { loc : { $near : { $geometry : { type : "Point" , coordinates : [108.86, 34.29] } , $maxDistance : 50000} } } );
        Document typeAndCoordiante = new Document("type", "Point").append("coordinates", new double[]{lng, lat});
        Document geometryDocument = new Document("geometry", typeAndCoordiante).append("maxDistance", distance);

        Document nearDocument = new Document("near", geometryDocument);
        FindIterable<Document> documents = cars.find();//new Document(field,nearDocument)

        documents.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String json = document.toJson();
                Gson gson = new Gson();
                CarQueryDTO carQueryDTO = gson.fromJson(json, CarQueryDTO.class);
                result.add(carQueryDTO);
            }
        });
        return result;
    }

    public static void createIndex(){
        cars.createIndex(new Document("loc","2dsphere"));
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

        BigDecimal latitude = new BigDecimal(34.271);
        BigDecimal longitude = new BigDecimal(108.849);
        for (int i = 0; i < 1000; i++) {
            latitude = latitude.add(BigDecimal.valueOf(0.01));
            longitude = longitude.add(BigDecimal.valueOf(0.01));
            System.out.println("lng:"+longitude+", lat:"+latitude);
            CarQueryDTO carQuery = new CarQueryDTO(Double.valueOf(myformat.format(latitude)),Double.valueOf(myformat.format(longitude)));
            list.add(carQuery);
        }
        return list;
    }
}
