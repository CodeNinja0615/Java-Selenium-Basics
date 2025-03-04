package apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import apiTesting.files.ReusableMethods;
import apiTesting.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APITest01 {

	public static void main(String[] args) {
		// --validate if add place api is working as expected
		// given - all input details
		// when - submit the api - resource, http method
		// then - validate the response

		// add place -> update place with new address -> get place to validate if new
		// address is present in response

		// Add place //POST
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response()
				.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing json

		String placeID = js.get("place_id"); // get place_id from response
		System.out.println(placeID);

		String newAddress = "70 Summer walk, USA";
		// update place //PUT
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).log().all();

		// get place //GET
		String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeID).when()
				.get("/maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response()
				.asString();
		System.out.println(getResponse);
		JsonPath js1 = ReusableMethods.rawToJson(getResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
