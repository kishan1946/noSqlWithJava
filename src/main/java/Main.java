//import com.mongodb.Block;
//import com.mongodb.MongoClient;
//import com.mongodb.client.*;
//import dataReader.DataReader;
//import dbConnection.DBConnection;
//import org.bson.Document;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class Main {
//    public static JSONObject jsonObj;
//
//    public static JSONObject readJson(String fileName, String packageName,String objectName) {
//        try {
//            JSONParser jsonParser = new JSONParser();
//            String fileLocation = String.format("./src/main/resources/%s/%s.json", packageName, fileName);
//            FileReader reader = new FileReader(fileLocation);
//            Object obj = jsonParser.parse(reader);
//            jsonObj = (JSONObject) obj;
//            if (objectName != null) {
//                jsonObj = (JSONObject) jsonObj.get(objectName);
//            }
//            return jsonObj;
//        }catch (IOException e){
//            e.printStackTrace();
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        return jsonObj;
//    }
//    public static void main(String[] args) {
//        MongoClient mongoClient;
//        MongoDatabase dataBase;
//        MongoCollection collection;
//        DBConnection dbConnection=new DBConnection();
//        mongoClient=dbConnection.connectToDb("mongodb+srv://kishan:kishan1234@mongodb.j8wlm.mongodb.net/test");
//        dataBase=mongoClient.getDatabase("practice");
//        collection=dataBase.getCollection("product");
////        Document document=new Document("name","kishan")
////                .append("id","1")
////                        .append("age","22");
////        jsonObj=readJson("product","dbTableData",null);
////
//        DataReader dataReader=new DataReader();
//        Document document=dataReader.convertToDocument("product","dbTableData","data");
////        collection.insertOne(document);
////        collection.deleteOne(Filters.eq("name","Aashirvaad multi grained Atta"));
////        MongoCursor cursor=mongoClient.listDatabaseNames().iterator();
////        while(cursor.hasNext()){
////            System.out.println(cursor.next());
////        }
////        MongoIterable<String> database= mongoClient.listDatabaseNames();
////        database.forEach((Block<? super String>) name -> System.out.println(name));
////        System.out.println(collection.countDocuments());
////        MongoCursor cursor= collection.find().iterator();
////        while (cursor.hasNext()){
////            System.out.println(cursor.next());
////        }
//
//        FindIterable<Document> collectionData=collection.find();
//        AtomicReference valueFromServer = new AtomicReference<>();
//        collectionData.forEach((Block<? super Document>) documents -> valueFromServer.set(documents.get("name")));
//        System.out.println(valueFromServer);
//        dbConnection.disconnect(mongoClient);
//    }
//}
