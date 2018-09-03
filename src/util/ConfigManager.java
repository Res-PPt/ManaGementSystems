package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	private static Properties properties;
	private static ConfigManager configManager;
	private ConfigManager(){
		properties=new Properties();
		String configFile="database.properties";
		InputStream is=ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try{
			properties.load(is);
			is.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
