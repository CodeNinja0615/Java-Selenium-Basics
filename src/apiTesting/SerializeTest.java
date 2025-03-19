package apiTesting;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		AddPlace jsonBody = new AddPlace();
		jsonBody.setAccuracy(50);
		jsonBody.setAddress("3/6K/5D Ainuddinpur kareli Allahabad");
		jsonBody.setLanguage("Hindi-IN");
		Location loca = new Location();
		loca.setLat(-32.009);
		loca.setLng(32.009);
		jsonBody.setLocation(loca);
		List<String> types = new ArrayList<String>();
		types.add("Ed park");
		types.add("Education");
		jsonBody.setTypes(types);
		jsonBody.setPhone_number("6387374031");
		jsonBody.setWebsite("RandomGut.co.in");
		jsonBody.setName("Sameer Akhtar");

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res = given().log().all().queryParam("key", "qaclick123").body(jsonBody)
				.header("Content-Type", "application/json").when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).extract().response();
		System.out.println(res.asPrettyString());

	}
}
