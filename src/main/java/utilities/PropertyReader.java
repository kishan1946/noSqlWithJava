package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public Properties getProperty(String env) throws IOException {
        FileReader fileReader = new FileReader(System.getProperty("user.dir")+"/src/main/resources/environments/"+
                String.format("%s",env)+
                "env.properties");
//        FileReader fileReader=new FileReader("/Users/k.k.k/IdeaProjects/beforeClientAPIPractice/src/main/resources/Environments/devenv.properties");

        Properties properties = new Properties();
        properties.load(fileReader);
        return properties;
    }
//    /Users/k.k.k/IdeaProjects/beforeClientAPIPractice/src/main/resources/Environments/devenv.properties
}
