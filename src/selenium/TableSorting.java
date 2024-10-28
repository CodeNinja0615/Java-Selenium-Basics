package selenium;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TableSorting {

	@Test
	public void tablesOperation() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.cssSelector("th[aria-sort='descending']")).click();
		// Get all the items/vegetables from table
		List<WebElement> veggies = driver
				.findElements(By.cssSelector(".table.table-bordered tbody tr td:nth-child(1)"));
		// Get text from WebElement using stream
		List<String> orignalList = veggies.stream().map(s -> s.getText()).collect(Collectors.toList());
		// Sort the list using stream
		List<String> sortedList = orignalList.stream().sorted().collect(Collectors.toList());
		// Assert that that list are equal
		Assert.assertTrue(orignalList.equals(sortedList));

		// --------- Locate a corresponding price of an item --------//

		// Filter to find a particular item and get the following sibling data using a function
		List<String> price = veggies.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPriceVeggie(s))
				.collect(Collectors.toList());
		// iterate through pages to check on which page the element is present with while loop
		while (price.isEmpty()) {
			driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
			// get the table for new page
			veggies = driver.findElements(By.cssSelector(".table.table-bordered tbody tr td:nth-child(1)"));
			price = veggies.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPriceVeggie(s))
					.collect(Collectors.toList());
		}
		price.forEach(s -> System.out.println(s));

		// Iterate through pages to check on which page the element is present with do while loop
		List<String> amount;
		do {
			List<WebElement> row = driver
					.findElements(By.cssSelector(".table.table-bordered tbody tr td:nth-child(1)"));
			amount = row.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPriceVeggie(s))
					.collect(Collectors.toList());

			if (amount.isEmpty()) {
				driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
			}
		} while (amount.isEmpty());
		amount.forEach(s -> System.out.println(s));
		driver.quit();
	}

	private String getPriceVeggie(WebElement s) {
		return s.findElement(By.xpath("following-sibling::td[1]")).getText();
	}

	@Test
	public void tableSortingProblem() {
		//---Find any particular item and filter the result
		String itemtext = "Rice";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.id("search-field")).sendKeys(itemtext);
		List<WebElement> itemElement = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> filteredList = itemElement.stream().filter(s -> s.getText().contains(itemtext))
				.collect(Collectors.toList());
		Assert.assertEquals(itemElement.size(), filteredList.size());
//		Assert.assertTrue(match);
		driver.quit();
	}
}