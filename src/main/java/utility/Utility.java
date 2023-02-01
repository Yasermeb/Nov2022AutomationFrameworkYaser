package utility;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class Utility {
    public static String path=System.getProperty("user.dir");
    public static Properties getProperties() {
        Properties prop = new Properties();   //this var is reference var (non-primitive), of type properties)
        try {
            InputStream inputStream = new FileInputStream("D:\\IdealProjects\\Nov2022AutomationFrameworkYaser\\config.properties");
            prop.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop; // prop is of type properties
    }
    public static String decode(String key){
        byte[]decodeBytes=Base64.getDecoder().decode(key);
        return new String(decodeBytes); //this will take the array of bytes and transform it into string

    }
    public static void main(String[] args) {
//        String toEncode="ti7HuQcrF8hBcJhSdQtJ";
//        String encoded=Base64.getEncoder().encodeToString(toEncode.getBytes());
//        System.out.println(encoded);
//
//        System.out.println(decode("bWViYXJraXlhc2VyX205TnczSA=="));

        String sep = File.separator;
        String path = System.getProperty("user.dir");
        System.out.println(sep);
    }
}