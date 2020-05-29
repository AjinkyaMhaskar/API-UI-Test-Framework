package main.java.dataProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private final String propertyFilePath = "configs//Configuration.properties";
	FileInputStream reader = null;
	static Properties properties = null;

	/*
	 * Constructor - TestInitialize 
	 * Use - Load property file
	 * */
	
	public ConfigFileReader() {
		try {
			reader = new FileInputStream(propertyFilePath);
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	/*
	 * Function - getBaseURL
	 * Return type - String(URL) 
	 * */
	
	public static String getBaseURL_GR() {
		String baseURL_GR = properties.getProperty("baseURL_GR");
		if (baseURL_GR != null)
			return baseURL_GR;
		else
			throw new RuntimeException("baseURL_GoodReads not is specified in the Configuration.properties file.");
	}
	
	/*
	 * Function - getQueryText
	 * Return type - String(URL)
	 * Use - The query text to match against book title, author, and ISBN fields. 
	 * 		 Supports boolean operators and phrase searching. 
	 * */
	
	public static String getQueryText() {
		String q = properties.getProperty("q");
		if (q != null)
			return q;
		else
			throw new RuntimeException("Query text not is specified in the Configuration.properties file.");
	}
	
	/*
	 * Function - getFormat
	 * Return type - String(URL)
	 * Use - Return format for API(XML) 
	 * */
	
	public static String getFormat() {
		String format = properties.getProperty("format");
		if (format != null)
			return format;
		else
			throw new RuntimeException("API return format is not specified in the Configuration.properties file.");
	}
	
	/*
	 * Function - getDevKey
	 * Return type - String(URL) 
	 * Use - Get Developer key required for search book API, Provided by GoodReads
	 * */
	
	public static String getDevKey() {
		String key = properties.getProperty("key");
		if (key != null)
			return key;
		else
			throw new RuntimeException("Dev key is not specified in the Configuration.properties file.");
	}
	
	/*
	 * Function - getSearchField
	 * Return type - String(URL) 
	 * */
	
	public static String getSearchField() {
		String search = properties.getProperty("search");
		if (search != null)
			return search;
		else
			throw new RuntimeException("Search field is not specified in the Configuration.properties file.");
	}
}
