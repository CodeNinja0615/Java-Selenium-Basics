package apiTesting;

import static io.restassured.RestAssured.given;

import apiTesting.files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").log().all().when().post("/oauthapi/oauth2/resourceOwner/token").then()
				.assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String accessToken = js.get("access_token");
		System.out.println(accessToken);

		String resGet = given().queryParam("access_token", accessToken).log().all().when()
				.get("/oauthapi/getCourseDetails").then().assertThat().statusCode(401).extract().response().asString();
		System.out.println(resGet);
	}

}
