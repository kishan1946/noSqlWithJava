package noSql;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dbConnection.DBConnection;
import org.bson.Document;
import org.testng.Reporter;

public class NoSqlCRUD {
    MongoClient client;
    MongoDatabase dataBase;
    MongoCollection collection;
    DBConnection dbConnection=new DBConnection();
    public NoSqlCRUD(MongoClient client,String dbName,String collectionName) {
        this.client=client;
        create(dbName,collectionName);
    }

    public MongoCollection create(String dbName,String collectionName) {
        dataBase=client.getDatabase(dbName);
        collection=dataBase.getCollection(collectionName);
        Reporter.log("Collection Created");
        return collection;
    }
    public void insert(Document document) {
        collection.insertOne(document);
        Reporter.log("Document inserted");
    }
    public void update(String updateParameter,String updateData,String updateByParameter,String updateByParameterValue) {
        collection.updateOne(Filters.eq(updateByParameter,updateByParameterValue), Updates.set(updateParameter,updateData));
        Reporter.log("Document updated");
    }
    public void delete(String deleteParameter,String deleteValue) {
        collection.deleteOne(Filters.eq(deleteParameter,deleteValue));
        Reporter.log("Document deleted");
    }
}
