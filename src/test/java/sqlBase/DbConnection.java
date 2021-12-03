package sqlBase;

import com.mongodb.MongoClient;
import io.qameta.allure.Step;
import utilities.PropertyReader;

import java.io.IOException;
import java.util.Properties;

public class DbConnection {
    MongoClient client;
    @Step("Connect to DB")
    public MongoClient dbConnection() {
        try {
            String env = System.getProperty("env");
            Properties properties = new PropertyReader().getProperty(env);
            String uri=String.format(properties.getProperty("cloudAddress"),properties.getProperty("user"),properties.getProperty("password"),properties.getProperty("cluster"));
            return (new dbConnection.DBConnection().connectToDb(uri));
        }catch (IOException e){
            e.printStackTrace();
        }
        return client;
    }
}
