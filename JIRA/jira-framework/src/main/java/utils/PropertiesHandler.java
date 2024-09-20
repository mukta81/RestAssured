package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class PropertiesHandler {
	
	private static Properties properties;
	
	public static String config(String key) {
		properties = new Properties();
		String value = null;
		
		try {
			properties.load(new FileInputStream(new File("src/test/resources/config.properties")));
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	public static String secret(String key) {
		properties = new Properties();
		String value = null;
		try {
			properties.load(new FileInputStream(new File("src/test/resources/secret.properties")));
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					"Unable to found \"src/test/resources/secret.properties\" file in the mentioned location.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	public static Map<String,String> queryParamRead(String fileName, String queryKey, HashMap String) {
		properties = new Properties();
		Map<String, String> queryParams = new HashMap<String, String>();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/query-params/" + fileName + ".properties")));
			for (String key : properties.stringPropertyNames()) {
				if(key.equals(queryKey)) {
					queryParams.put(key, properties.getProperty(key));
					break;
				}	
			}
		} catch(FileNotFoundException e) { 
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
System.out.println("queryParams"+queryParams);
		return queryParams;
	}
	
	
	
	public static Map<String,String> queryParamRead(String fileName, String[] queryKey) {
		properties = new Properties();
		Map<String, String> queryParams = new HashMap<String, String>();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/query-params/" + fileName + ".properties")));
			for (String keyFromProperties : properties.stringPropertyNames()) {
				for (String keyFromTest : queryKey) {
					if(keyFromProperties.equals(keyFromTest)) {
						queryParams.put(keyFromProperties, properties.getProperty(keyFromProperties));						
					}
				}		
			}
		} catch(FileNotFoundException e) { 
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("queryParams"+queryParams);
		return queryParams;
	}
}
