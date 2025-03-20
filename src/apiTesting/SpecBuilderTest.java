package apiTesting;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderTest {

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

		//----Request Spec. is used for given and when-------//
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		//------Response Spec. is used for then-----//
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		RequestSpecification res = given().spec(reqSpec).body(jsonBody);

		Response response = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();

		System.out.println(response.asPrettyString());

	}
}
