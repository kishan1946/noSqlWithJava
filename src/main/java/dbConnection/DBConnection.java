package dbConnection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.testng.Reporter;

public class DBConnection {
    public MongoClient mongoClient;
    public DB dataBase;
    public DBCollection dbCollection;
    public MongoClient connectToDb(String uri){
        mongoClient=new MongoClient(new MongoClientURI(uri));
        Reporter.log("Connection Established",true);
        return mongoClient;
    }
    public void disconnect(MongoClient mongoClient){
        mongoClient.close();
    }
}
