package sqlTesting;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import dataReader.DataReader;
import dbConnection.DBConnection;
import io.qameta.allure.*;
import noSql.CRUDCall;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sqlBase.DbConnection;

import java.util.concurrent.atomic.AtomicReference;

public class E2ETest {
    MongoClient client;
    FindIterable<Document> findIterable;
    JSONObject jsonObj;
    DataReader dataReader=new DataReader();

    @DataProvider(name = "dbCrudCall")
    public Object[][] dbCrudCall() {
        return  new Object[][] {{"dbTableData","product","data","userParameter"},{"dbTableData","userDetails","data","userParameter"},
                {"dbTableData","historySale","data","userParameter"},{"dbTableData","purchase","data","userParameter"}};
    }

    @Step("calling CRUDCall.class and fetching iterable data")
    public FindIterable getCall(String call, String jsonFile, String packageName, String dataObject, String userObject){
        CRUDCall crudCall=new CRUDCall(client,"demo",jsonFile);
        findIterable=crudCall.reader(call,jsonFile,packageName,dataObject,userObject,"demo",jsonFile);
        return findIterable;
    }
    @Step("done all the testing operation as well as assertion \"{0}\"")
    public void operation(String call, String jsonFile, String packageName, String dataObject, String userObject,String valueToServer,String valueToJson){
        getCall(call,jsonFile,packageName,dataObject,userObject);
        findIterable=getCall("read",jsonFile,packageName,dataObject,userObject);
        AtomicReference<String> valueFromServer =  new AtomicReference<>();;
        findIterable.forEach((Block<? super Document>) document -> valueFromServer.set((String) document.get(valueToServer)));
        jsonObj=dataReader.readJson(jsonFile,packageName,userObject);
        String valueFromJson= (String) jsonObj.get(valueToJson);
        Reporter.log(valueFromServer.toString()+"\n"+valueFromJson);
        Assert.assertEquals(valueFromServer.toString(),valueFromJson);
    }
    @BeforeTest
    @Description("connect to database")
    @Step("connecting to database server and getting the MongoClient")
    @Severity(SeverityLevel.BLOCKER)
    public void dbConnect() {
        client=new DbConnection().dbConnection();
    }

    @Test(priority = 1,dataProvider = "dbCrudCall",description = "create table test")
    @Description("Test for creating a table in the database server")
    @Step("create a table and test for whether table created")
    @Severity(SeverityLevel.BLOCKER)
    public void createTableTest(String packageName,String jsonFile,String dataObject,String userObject) {
        getCall("create",jsonFile,packageName,dataObject,userObject);

    }
    @Test(priority = 2,dataProvider = "dbCrudCall",description = "checking whether data inserted")
    @Description("Test for inserting a collection in a particular table")
    @Step("insert a document and test for whether document inserted in a particular table")
    @Severity(SeverityLevel.NORMAL)
    public void insertDataTest(String packageName,String jsonFile,String dataObject,String userObject){
        operation("insert",jsonFile,packageName,dataObject,userObject,"name","insertName");
    }
    @Test(priority = 3,dataProvider = "dbCrudCall",description = "checking whether data updated")
    @Description("Test for updating a collection data in a particular table")
    @Step("update a document and test for whether document updated in a particular table")
    @Severity(SeverityLevel.NORMAL)
    public void updateDataTest(String packageName,String jsonFile,String dataObject,String userObject){
        operation("update",jsonFile,packageName,dataObject,userObject,"name","updateData");
    }
    @Test(priority = 4,dataProvider = "dbCrudCall",description = "checking whether data deleted")
    @Description("Test for deleting a collection data in a particular table")
    @Step("delete a document and test for whether document deleted in a particular table")
    @Severity(SeverityLevel.NORMAL)
    public void deleteDataTest(String packageName,String jsonFile,String dataObject,String userObject){
        operation("delete",jsonFile,packageName,dataObject,userObject,"barcode","deleteValue");
    }
    @AfterTest
    @Step("Disconnect to the server")
    public void disconnect(){
        DBConnection dbConnection=new DBConnection();
        dbConnection.disconnect(client);
    }
}
