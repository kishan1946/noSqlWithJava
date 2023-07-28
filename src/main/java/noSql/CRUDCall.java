package noSql;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import dataReader.DataReader;
import org.bson.Document;
import org.json.simple.JSONObject;
import java.sql.ResultSet;

public class CRUDCall extends NoSqlCRUD {
    JSONObject jsonObj;
    ResultSet resultSet=null;
    MongoClient client;

    public CRUDCall(MongoClient client, String dbName, String collectionName) {
        super(client, dbName, collectionName);
        this.client=client;
    }


    public FindIterable reader(String call, String jsonFile, String packageName, String dataObject, String userDeatils, String dbName, String collectionName) {
        DBOperation dbOperation=new DBOperation(collection);
        ReadOperations readOperations=new ReadOperations(client,collection);
        DataReader dataReader=new DataReader();
        FindIterable findIterable = null;
        Document document =dataReader.convertToDocument(jsonFile,packageName,dataObject);
        jsonObj=dataReader.readJson(jsonFile,packageName,userDeatils);

        switch(call){
            case "create" :
                create(dbName,collectionName);
                break;
            case "insert" :
                insert(document);
                break;
            case "update" :
                update((String) jsonObj.get("updateParameter"),(String) jsonObj.get("updateData"),(String) jsonObj.get("updateByParameter"),(String) jsonObj.get("updateByParameterValue"));
                break;
            case "delete" :
                delete((String) jsonObj.get("deleteParameter"),(String) jsonObj.get("deleteValue"));
                break;
            case "drop" :
                dbOperation.drop();
                break;
            case "read" :
                findIterable = readOperations.readCollectionData();
                return findIterable;
            default:
                return findIterable;
        }
        return findIterable;
    }
}
