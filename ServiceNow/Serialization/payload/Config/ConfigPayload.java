package Serialization.payload.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigPayload {
	private static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/config.properties")));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Unable to find config file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getUserName() {
		return properties.getProperty("service.now.username");
	}
	
	public static String getPassword() {
		return properties.getProperty("service.now.password");
	}
	
	public static String getClientId() {
		return properties.getProperty("service.now.client.id");
	}
	
	public static String getClientSecret() {
		return properties.getProperty("service.now.client.secret");
	}
	
	public static String getGrantType() {
		return properties.getProperty("service.now.grant.type");
	}
}
