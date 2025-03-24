package apiTesting;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.CreateOrderReq;
import pojo.CreateOrderRes;
import pojo.CreateProdRes;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.Orders;

public class ECommerceAPITest {

	public static void main(String[] args) {

		// ---Getting token and userID
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("akhtarsameer743@gmail.com");
		loginRequest.setUserPassword("Sameerking01!");
		//-relaxedHTTPSValidation using this for SSL
		RequestSpecification reqLogin = given().relaxedHTTPSValidation().spec(reqSpec).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract()
				.as(LoginResponse.class);
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();

		// ---POST call to create a product with formParam/param
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		RequestSpecification reqCreateProd = given().spec(addProductBaseReq).param("productName", "Qwerty")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productDescription", "Adidas Originals").param("productFor", "family")
				.param("productPrice", "92002").param("productSubCategory", "shirts").multiPart("productImage",
						new File("/Users/sameerakhtar/Downloads/gotham-batman-logo-art-ia7ahcvp2jfxyfty (1).jpg"));

		CreateProdRes createProdRes = reqCreateProd.when().post("/api/ecom/product/add-product").then()
				.extract().as(CreateProdRes.class);
		String productId = createProdRes.getProductId();

		// ---POST call to create an order
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		Orders orders = new Orders();
		orders.setCountry("India");
		orders.setProductOrderedId(productId);
		List<Orders> list = new ArrayList<Orders>();
		list.add(orders);
		CreateOrderReq createOrder = new CreateOrderReq();
		createOrder.setOrders(list);
		RequestSpecification createOrderReq = given().spec(createOrderBaseReq).body(createOrder);
		CreateOrderRes createOrderRes = createOrderReq.when().post("/api/ecom/order/create-order").then()
				.extract().as(CreateOrderRes.class);

		// ---DELETE Call to delete the product
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		RequestSpecification deleteProdReq = given().spec(deleteProductBaseReq).pathParam("productId", productId);
		String deleteRes = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().extract()
				.asPrettyString();
		JsonPath js = new JsonPath(deleteRes);
		String deleteResponse = js.get("message");
		Assert.assertEquals(deleteResponse, "Product Deleted Successfully");
		System.out.println(deleteResponse);

	}
}