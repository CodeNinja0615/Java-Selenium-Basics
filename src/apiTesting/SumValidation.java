package apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiTesting.files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses() {

		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int sum = 0;
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			sum += price * copies;
		}
		System.out.println("Sum of all course prices: "+sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount: "+purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);
	}
}
