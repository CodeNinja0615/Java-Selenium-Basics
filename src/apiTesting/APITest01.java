package apiTesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class APITest01 {

	public static void main(String[] args) {
		// --validate if add place api is working as expected
		// given - all input details
		// when - submit the api - resource, http method
		// then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Sameer Akhtar\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"Ed park\",\r\n" + "    \"Education\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n" + "  \"language\": \"French-IN\"\r\n"
						+ "}\r\n" + "")
				.when().post("/maps/api/place/add/json?key=qaclick123").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);

	}

}
