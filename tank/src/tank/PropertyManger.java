package tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyManger {
	
	static Properties properties = new Properties();
	
	static{
		try {
			properties.load(PropertyManger.class.getClassLoader().getResourceAsStream("tankConfig"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getInt(String key){
		return Integer.parseInt((String)properties.get(key));
	}

}
