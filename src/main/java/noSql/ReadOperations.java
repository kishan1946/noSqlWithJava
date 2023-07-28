package noSql;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class ReadOperations {
    MongoCursor cursor;
    MongoClient client;
    MongoCollection collection;

    public ReadOperations(MongoClient client, MongoCollection collection) {
        this.client=client;
        this.collection=collection;
    }

    public MongoIterable<String> readListOfDatabase() {
//        List<String> database=new ArrayList<>();
//        cursor=client.listDatabaseNames().iterator();
//        while (cursor.hasNext()){
//            database.add((String) cursor.next());
//        }
        MongoIterable<String> database=client.listDatabaseNames();
        return database;
    }
    public FindIterable<Document> readCollectionData(){
        FindIterable<Document> collectionData=collection.find();
        return collectionData;
    }

}
