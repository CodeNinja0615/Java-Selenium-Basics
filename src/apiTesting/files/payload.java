package apiTesting.files;

public class payload {

	public static String AddPlace() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Sameer Akhtar\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"Ed park\",\r\n"
				+ "    \"Education\"\r\n" + "  ],\r\n" + "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}\r\n" + "";
	}

	public static String CoursePrice() {
		return "{\r\n" + "  \"dashboard\": {\r\n" + "    \"purchaseAmount\": 910,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n" + "  },\r\n" + "  \"courses\": [\r\n" + "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n" + "      \"price\": 50,\r\n" + "      \"copies\": 6\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"title\": \"Cypress\",\r\n" + "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n" + "    },\r\n" + "    {\r\n" + "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n" + "      \"copies\": 10\r\n" + "    }\r\n" + "  ]\r\n" + "}\r\n" + "";
	}

	public static String AddBook(String isbn, String aisle) {
		return "{\n" + "\n" + "\"name\":\"Learn Appium Automation with Java\",\n" + "\"isbn\":\"" + isbn + "\",\n"
				+ "\"aisle\":\"" + aisle + "\",\n" + "\"author\":\"Sameer Akhtar\"\n" + "}\n" + "";
	}

	public static String DeleteBook(String ID) {
		return "{\n" + " \n" + "\"ID\" : \"" + ID + "\"\n" + " \n" + "}";
	}
}
