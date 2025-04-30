package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties properties;
    private static final String CONFIG_FILE_PATH = "./src/test/resourses/config.properties";
    File file;
    FileInputStream fileInputStream;

    public ConfigReader() {

        properties = new Properties();

        file = new File(CONFIG_FILE_PATH);

        try {
            fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);

        } catch (IOException e) {

            throw new RuntimeException("Error while reading config.properties file");
        }

    }

    public String getProperty(String key) {

        return properties.getProperty(key);

    }

    public int getIntProperty(String key) {

        return Integer.parseInt(properties.getProperty(key));
    }

}
