package apiTesting;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;

public class ECommerceAPITest {

	public static void main(String[] args) {
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("akhtarsameer743@gmail.com");
		loginRequest.setUserPassword("Sameerking01!");

		RequestSpecification reqLogin = given().spec(reqSpec).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract()
				.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());

	}
}