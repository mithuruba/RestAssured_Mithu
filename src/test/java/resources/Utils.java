package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification requestBuilder;
	public RequestSpecification requestSpecification() throws IOException {
		if(requestBuilder==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		requestBuilder = new RequestSpecBuilder().setBaseUri(getGlobalData("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))// to capture request logs
				.addFilter(ResponseLoggingFilter.logResponseTo(log))// to capture response logs
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		return requestBuilder;
		}
		return requestBuilder;
		
	}
	
	public static String getGlobalData(String key) throws IOException {
		
		Properties ps = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\Vijay Paul\\eclipse-workspace\\rest.assured.framework.rahul\\src\\test\\java\\resources\\data.properties");
		ps.load(fs);
		return ps.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		//System.out.println("hello all");
		//System.out.println(responseString);
		JsonPath jp = new JsonPath(responseString);
		return jp.get(key).toString();
		
	}

}
