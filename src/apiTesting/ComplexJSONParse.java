package apiTesting;

import org.testng.Assert;

import apiTesting.files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.CoursePrice());
		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		// Print Title of the first course
		String titleFirstCourse = js.getString("courses[0].title");
		System.out.println(titleFirstCourse);

		// Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String title = js.getString("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(title + " : " + price);
		}

		// Print no of copies sold by RPA course
		for (int i = 0; i < count; i++) {
			String title = js.getString("courses[" + i + "].title");
			if (title.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("Copies sold by RPA course: " + copies);
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int sum = 0;
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			sum += price * copies;
		}
		System.out.println("Sum of all Course prices: " + sum);
		System.out.println("Purchase Amount: " + purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);
		
	}
}



/*
 
 {
  "dashboard":{
    "purchaseAmount":"910",
    "website":"rahulshettyacademy.com"
  },
  "courses": [
    {
    "title": "Selenium Python",
    "price": "50",
    "copies": "6"
    },
    {
    "title": "Cypress",
    "price": "40",
    "copies": "4"
    },
    {
    "title": "RPA",
    "price": "45",
    "copies": "10"
    }
  ]
}
 
 */
