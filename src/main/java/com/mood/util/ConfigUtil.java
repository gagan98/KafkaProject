package com.mood.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {

	private static Logger LOGGER = Logger.getLogger(ConfigUtil.class);
	private static ConfigUtil configUtil = null;
	private static Map<String, Properties> propertiesFiles = null;
	private static String DB_PROPERTIES = "db.properties";
	private static String EXTRACTOR_PROPERTIES = "extractor.properties";
	private static String KAFKA_PROPERTIES = "kafka.properties";
	private static Properties config = null;
	
	static {
		propertiesFiles = new HashMap<>();
		//propertiesFiles.put(DB_PROPERTIES, load("db.properties"));
		//propertiesFiles.put(EXTRACTOR_PROPERTIES, null);
		propertiesFiles.put(KAFKA_PROPERTIES, load("kafka.properties"));
	}

	private ConfigUtil() {
	}

	public static ConfigUtil getConfigUtil() {
		if (configUtil == null) {
			configUtil = new ConfigUtil();
		}

		return configUtil;
	}

	private static Properties load(String propertiesFileName) {
		Properties properties = new Properties();
		InputStream input = null;
		System.out.println(propertiesFileName);
		try {

			input = ConfigUtil.class.getClassLoader().getResourceAsStream(
					propertiesFileName);
			properties.load(input);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("[Loaded - " + propertiesFileName + "]");

		} catch (IOException e) {
			LOGGER.error(e.getMessage(),e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error(
							"Could not load Properties file:"
									+ propertiesFileName, e);
				}
			}
		}
		return properties;
	}

	@SuppressWarnings("unused")
	private static Properties load(File file) {
		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(file);

			// load a properties file
			properties.load(input);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("[Loaded - " + file.getName() + "]");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error(
							"Could not load Properties file:"
									+ file.getName(), e);
				}
			}
		}
		return properties;
	}
	
	public static String getDBProperty(String propertyName) {
		return getDBProperties().getProperty(propertyName);
	}

	public static String getExtractorProperty(String propertyName) {
		return getExtractorProperties().getProperty(propertyName);
	}
	
	public static String getKafkaProperty(String propertyName) {
		return getKafkaProperties().getProperty(propertyName);
	}
	
	public static Properties getDBProperties()
	{
		return propertiesFiles.get(DB_PROPERTIES);
	}
	public static Properties getExtractorProperties()
	{
		return propertiesFiles.get(EXTRACTOR_PROPERTIES);
	}
	public static String getSerializeLocation()
	{
		return config.getProperty("serializeLocation");
	}
	public static Properties getKafkaProperties()
	{
		return propertiesFiles.get(KAFKA_PROPERTIES);
	}
}
