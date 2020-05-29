package test.java.gluecode;

import cucumber.api.java.Before;
import main.java.dataProviders.ConfigFileReader;

public class TestInitialize {

	/*
	 * Function - TestSetup
	 * Return type - 
	 * Use - Constructor to initialize property file for data 
	 * */
	
	@Before
	public void TestSetup() {
		ConfigFileReader ConfigFileReader = new ConfigFileReader();
		}
}
