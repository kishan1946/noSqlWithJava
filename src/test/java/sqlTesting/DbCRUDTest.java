package sqlTesting;

import com.mongodb.MongoClient;
import dbConnection.DBConnection;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import noSql.CRUDCall;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sqlBase.DbConnection;

public class DbCRUDTest {
    MongoClient client;

    @DataProvider(name = "dbCrudCall")
    public Object[][] dbCrudCall() {
        return  new Object[][] {{"drop","dbTableData","historySale","data","userParameter"}};
    }

    @BeforeTest
    @Description("connect to database")
    @Step("connecting to database server and getting the MongoClient")
    @Severity(SeverityLevel.BLOCKER)
    public void dbConnect() {
        client=new DbConnection().dbConnection();
    }

    @Test(dataProvider = "dbCrudCall")
    @Description("Test for CRUD operation manually from MongoDB Compass")
    @Step("test all CRUD operation manually")
    @Severity(SeverityLevel.NORMAL)
    public void crudTest(String call,String packageName,String jsonFile,String dataObject,String userObject) {
        CRUDCall crudCall=new CRUDCall(client,"demo",jsonFile);
        crudCall.reader(call,jsonFile,packageName,dataObject,userObject,"demo",jsonFile);
    }
    @AfterTest
    @Step("Disconnect to the server")
    public void disconnect(){
        DBConnection dbConnection=new DBConnection();
        dbConnection.disconnect(client);
    }
}
