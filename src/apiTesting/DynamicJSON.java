package apiTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apiTesting.files.ReusableMethods;
import apiTesting.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJSON {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";

		// ---POST Call to add data
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook(isbn, aisle)).when().post("Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String msg = js.get("Msg");
		String id = js.get("ID");
		System.out.println(msg);
		System.out.println(id);

		//---body(new String(Files.readAllBytes(Paths.get("")))) can also use this read json file
//		Content of the file to String -> Byte -> String
		// ---DELETE Call to delete a data
		String delRes = given().log().all().header("Content-Type", "application/json").body(payload.DeleteBook(id))
				.when().post("/Library/DeleteBook.php").then().assertThat().statusCode(200).extract().response()
				.asString();
		System.out.println("Del:" + delRes);
		JsonPath delJs = ReusableMethods.rawToJson(delRes);
		String msg1 = delJs.get("msg");
		System.out.println(msg1);

	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] { { "sameer", "987" }, { "Sameerking", "009" } };
	}
}
