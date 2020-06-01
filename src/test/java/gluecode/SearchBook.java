package test.java.gluecode;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.dataProviders.ConfigFileReader;

public class SearchBook {

	private static ResponseOptions<Response> response;
	
	/*
	 * Method - i_perform_GET_operation_for_with_query_parameters
	 * Argument - URL 
	 * Return type - null
	 * */
//	@Given("^I perform GET operation for \"([^\"]*)\" with query parameters$")
//	public void i_perform_GET_operation_for_with_query_parameters(String url) throws Throwable {
//
//		// query parameter
//		HashMap<String, String> queryParam = new HashMap<>();
//
//		queryParam.put("format", ConfigFileReader.getFormat());
//		queryParam.put("key", ConfigFileReader.getDevKey());
//		queryParam.put("q", ConfigFileReader.getQueryText());
//		queryParam.put("search", ConfigFileReader.getSearchField());
//
//		response = BDDStyleMethod.GetOpsWithQueryParams(url,queryParam);
//	}
	
	/*
	 * Method - i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_verification
	 * Use - Print desired results 
	 * */
//	@Then("^I see API response in XML format and use XML class path for result verification$")
//	public void i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_verification() throws Throwable {
//		//XmlPath xml = new XmlPath(response.getBody().asString());
//		xml = new XmlPath(response.getBody().asString());
//		int i = 0, j = 1;
//		System.out.println("Top 5 search results are as follows:");
//		for (i = 0; i < 5; i++) {
//			System.out.println("Search Result:" + j + " "
//					+ xml.getString("GoodreadsResponse.search.results.work[" + i + "].best_book.title"));
//			j++;
//		}
//	}

	/*
	 * Method - i_should_see_the_status_as
	 * Use - Assert get API response status code 
	 * */
	@SuppressWarnings("deprecation")
	@Then("^I should see the status as (\\d+)$")
	public void i_should_see_the_status_as(int statusCode) throws Throwable {
		assertThat(response.getStatusCode(), is(statusCode));
	}
	
	/*
	 * Method - i_perform_GET_operation_for_with_query_parameters_search_as
	 * Use - Search with following query parameter (Search[filed] - author, title, all)
	 * */
	@Given("^I perform GET operation for \"([^\"]*)\" with query parameters search as \"([^\"]*)\"$")
	public void i_perform_GET_operation_for_with_query_parameters_search_as(String url, String search_field) throws Throwable {

		// query parameter
		HashMap<String, String> queryParam = new HashMap<>();

		queryParam.put("format", ConfigFileReader.getFormat());
		queryParam.put("key", ConfigFileReader.getDevKey());
		queryParam.put("q", ConfigFileReader.getQueryText());
		
		if(search_field=="title")
			{queryParam.put("search", ConfigFileReader.getSearchField_title());}
		else if(search_field=="author")
			{queryParam.put("search", ConfigFileReader.getSearchField_author());}
		else
			{queryParam.put("search", ConfigFileReader.getSearchField_all());}
		
		response = BDDStyleMethod.GetOpsWithQueryParams(url,queryParam);
	}

	/*
	 * Method - i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_filteration_with_attribute_original_publication_year
	 * Use - Filter XML data with respect to attribute "original_publication_year" and "no_filter"
	 * */
	@Then("^I see API response in XML format and use XML class path for result verification with \"([^\"]*)\"$")
	public void i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_verification_with(String responseFilter) throws Throwable {

		XmlPath xml = new XmlPath(response.getBody().asString());		
		int i = 0, j = 1;
		
		// when user is un-aware about exact search parameter(Plot, Quoter, Trivia)
		if((responseFilter).equalsIgnoreCase("no_filter"))
		{
			System.out.println("Top 5 search results are as follows:");
			for (i = 0; i < 5; i++) {
				System.out.println("Search Result:" + j + " "
						+ xml.getString("GoodreadsResponse.search.results.work[" + i + "].best_book.title"));
				j++;
			}
		}
		
		//when user is aware for author name and book published year
		else if((responseFilter).equalsIgnoreCase("original_publication_year"))
		{
			for(i=0; i<xml.getInt("GoodreadsResponse.search.results-end");i++)
			{
				j = 1;
				try
					{
					if((xml.getInt("GoodreadsResponse.search.results.work[" + i + "].original_publication_year"))==(Integer.parseInt(ConfigFileReader.getPubYear())))
						{
							System.out.println("Search Result:" + j + " "
							+ xml.getString("GoodreadsResponse.search.results.work[" + i + "].best_book.title"));
							j++;
						}
					}
				catch(Exception ex) 
				  {
				    //do nothing, error handling for "" in XML response
				  }
			}	
		}
	}
}
