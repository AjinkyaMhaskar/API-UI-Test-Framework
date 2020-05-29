package test.java.gluecode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class BDDStyleMethod {

	public static ResponseOptions<Response> Request;

	/*
	 * Function - GetOpsWithQueryParams
	 * Argument - URl, QueryParameter
	 * Return type - ResponseOPtions(XML format)
	 * Use - Perform get operation with query parameters
	 * */
	@SuppressWarnings("deprecation")
	public static ResponseOptions<Response> GetOpsWithQueryParams(String url, HashMap<String, String> queryParam) {
		Request = given().contentType(ContentType.XML).queryParameters(queryParam).get(url);
		return Request;
	}
}
