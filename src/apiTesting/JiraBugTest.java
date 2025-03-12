package apiTesting;

import static io.restassured.RestAssured.given;

import java.io.File;

import apiTesting.files.ReusableMethods;
import apiTesting.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraBugTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://akhtarsameer743-1741794891192.atlassian.net";

		// --Create issue/Bug
		String response = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Authorization",
						"Basic YWtodGFyc2FtZWVyNzQzQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBWMXd6YndYR3JRc1ltZDNzSkJySldoOTM5cGE1UnlaY0xEaWEwaVB4QU5oMnZramdzRWxmVHN1aWRhMzRaNlFmUnVBUEw4Y1Zic2Y1b3luTFNFYmY2LVRLaElzamRhTVliZGpYNGNfamZzc2JTbWZlSUlYYkhGajZGbUNocnVSdU43QjF0REp1X0F0ckJxOXQ2TUYtNzZBdkZTQ09DdTRrZXhsai1mNnNNX2M9NUI5NDE0NEQ=")
				.body(payload.JiraCreateBug()).log().all().post("/rest/api/3/issue").then().log().all().assertThat()
				.statusCode(201).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String issueID = js.get("id");
		System.out.println(issueID);

		// --Add Attachment
		String attachmentResp = given().pathParam("key", issueID).header("X-Atlassian-Token", "no-check").header(
				"Authorization",
				"Basic YWtodGFyc2FtZWVyNzQzQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBWMXd6YndYR3JRc1ltZDNzSkJySldoOTM5cGE1UnlaY0xEaWEwaVB4QU5oMnZramdzRWxmVHN1aWRhMzRaNlFmUnVBUEw4Y1Zic2Y1b3luTFNFYmY2LVRLaElzamRhTVliZGpYNGNfamZzc2JTbWZlSUlYYkhGajZGbUNocnVSdU43QjF0REp1X0F0ckJxOXQ2TUYtNzZBdkZTQ09DdTRrZXhsai1mNnNNX2M9NUI5NDE0NEQ=")
				.multiPart("file",
						new File("/Users/sameerakhtar/Downloads/gotham-batman-logo-art-ia7ahcvp2jfxyfty (1).jpg"))
				.log().all().post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js1 = ReusableMethods.rawToJson(attachmentResp);
		String attchID = js1.get("[0].id");
		System.out.println(attchID);

	}

}
