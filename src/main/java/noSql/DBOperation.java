package noSql;

import com.mongodb.client.MongoCollection;

public class DBOperation {
    MongoCollection collection;

    public DBOperation(MongoCollection collection) {
        this.collection=collection;
    }
    public void drop() {
        collection.drop();
    }
}
