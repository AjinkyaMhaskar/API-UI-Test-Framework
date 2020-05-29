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
	@Given("^I perform GET operation for \"([^\"]*)\" with query parameters$")
	public void i_perform_GET_operation_for_with_query_parameters(String url) throws Throwable {

		// query parameter
		HashMap<String, String> queryParam = new HashMap<>();

		queryParam.put("format", ConfigFileReader.getFormat());
		queryParam.put("key", ConfigFileReader.getDevKey());
		queryParam.put("q", ConfigFileReader.getQueryText());
		queryParam.put("search", ConfigFileReader.getSearchField());

		response = BDDStyleMethod.GetOpsWithQueryParams(url,queryParam);
	}
	
	/*
	 * Method - i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_verification
	 * Use - Print desired results 
	 * */
	@Then("^I see API response in XML format and use XML class path for result verification$")
	public void i_see_API_response_in_XML_format_and_use_XML_class_path_for_result_verification() throws Throwable {
		XmlPath xml = new XmlPath(response.getBody().asString());
		int i = 0, j = 1;
		System.out.println("Top 5 search results are as follows:");
		for (i = 0; i < 5; i++) {
			System.out.println("Search Result:" + j + " "
					+ xml.getString("GoodreadsResponse.search.results.work[" + i + "].best_book.title"));
			j++;
		}
	}

	/*
	 * Method - i_should_see_the_status_as
	 * Use - Assert get API response status code 
	 * */
	@SuppressWarnings("deprecation")
	@Then("^I should see the status as (\\d+)$")
	public void i_should_see_the_status_as(int statusCode) throws Throwable {
		assertThat(response.getStatusCode(), is(statusCode));
	}
}
