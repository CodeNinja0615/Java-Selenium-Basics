package selenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Ecommerce {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
//		driver.findElement(By.xpath(
//				"//h4[text()='Cucumber - 1 Kg']/following-sibling::div[@class='product-action']/button[text()='ADD TO CART']"))
//				.click();
		String[] itemsNeeded = { "Brocolli", "Cucumber", "Tomato", "Beans" }; // ---Array of items
		addItems(driver, itemsNeeded);
		Assert.assertTrue(true);
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//div[@class='action-block']/button[text()='PROCEED TO CHECKOUT']")).click();
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
//		WebElement promoInfo = driver.findElement(By.cssSelector(".promoInfo"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement promoInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
		promoInfo.isDisplayed();
		if (promoInfo.getText().equalsIgnoreCase("Code applied ..!")) {
			Assert.assertTrue(true);
			driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		} else {
			Assert.assertTrue(false);
		}

	}

	private static void addItems(WebDriver driver, String[] itemsNeeded) {

		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		int j = 0;
		for (int i = 0; i < products.size(); i++) {
			String[] name = products.get(i).getText().split("-"); // --Splitting from "-"
//			ArrayList<String> itemList = new ArrayList<String>(Arrays.asList(itemsNeeded)); // -----Converting array to
//																							 arrayList(creating an
//																							 array takes less memory
//																							 than creating an
//																							 arrayList)
			List<String> itemList = Arrays.asList(itemsNeeded); // -- ArrayList can also be use instead using List
																// because it is immutable

			if (itemList.contains(name[0].trim())) { // -----removing blank spaces from string with .trim
				List<WebElement> buttons = driver
						.findElements(By.xpath("//div[@class='product-action']/button[@type='button']")); // ---- not
//																											adding
//																											with text
//																											cause it
//																											is
//																											dynamic
				buttons.get(i).click();// ----Adding item to the cart
				j++;
				if (j == itemList.size()) {
					break;
				}
			}
		}
	}
}
