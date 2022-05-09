package libraries;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigDemo {

    @Test
    public void readConfig() throws IOException {

       System.out.println(getConfigValue("Browser"));
        System.out.println(getConfigValue("URL_"+getConfigValue("ENV")));

    }

    public String getConfigValue(String key) throws IOException {
        Properties prop =new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config\\config.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
