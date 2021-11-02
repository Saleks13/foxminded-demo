package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    static Properties prop = new Properties();

    // this method reads config.properties and returns value of the key, depending on the specified key name
    private static String getProperty(String propertyName) {
        String result = null;

        try {

            InputStream input = new FileInputStream("src/main/java/testData/config.properties");
            prop.load(input);
            result = prop.getProperty(propertyName);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();

        }
        return result;
    }

    //this get method returns value of the baseUrl from properties file
    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    //this get method returns value of the username from properties file
    public static String getUsername() {
        return getProperty("username");
    }

    //this get method returns value of the password from properties file
    public static String getPassword() {
        return getProperty("password");
    }

    //this get method returns value of the newDepartmentName from properties file
    public static String getDepartmentName() {
        return getProperty("departmentName");
    }

    //this method returns Database host to connect to DB
    public static String getDatabaseHost(){
        return getProperty("dbhost");
    }

    //this method returns Database port to connect to DB
    public static String getDatabasePort(){
        return getProperty("dbport");
    }

    //this method returns Database name to connect to DB
    public static String getDatabaseName(){
        return getProperty("dbname");
    }

    //this method returns Database login to connect to DB
    public static String getDatabaseLogin(){
        return getProperty("dblogin");
    }

    //this method returns Database password to connect to DB
    public static String getDatabasePassword(){
        return getProperty("dbpassword");
    }

}

