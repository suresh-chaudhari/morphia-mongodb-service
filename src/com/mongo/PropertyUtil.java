package com.mongo;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	private static Properties mongoProps;
	private static final String filePath="conf/mongo.properties";

	/**
	 * Load property.
	 *
	 * @param propertyFile the property file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getProperty(String prop) {
		if(mongoProps == null)
			mongoProps = new Properties();
		try {
			mongoProps.load(PropertyUtil.class.getClassLoader().getResourceAsStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) mongoProps.get(prop);
	}

	public static void main(String[] args) {
		System.out.println(PropertyUtil.getProperty("mongo.host"));
	}
}
