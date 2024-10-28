package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Practice03 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("password")).sendKeys("learning");
		// --Clicking on User radio button
		driver.findElement(By.xpath("//input[@value='user']/following-sibling::span")).click();
		// --Explicit wait initializes
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// ---Using explicit wait for user dialog acceptance
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='modal-body'] p")));
		driver.findElement(By.id("okayBtn")).click();
		// --Drop down for selecting role
		WebElement dropdown = driver
				.findElement(By.cssSelector("div[class='form-group'] select[class='form-control']"));
		Select select = new Select(dropdown);
		select.selectByIndex(2);
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		// ---Verify Home page after successful login
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'ProtoCommerce Home')]")));
		// --Adding all the products to cart
		List<WebElement> addCarts = driver.findElements(By.cssSelector(".btn.btn-info"));
		for (WebElement addCart : addCarts) {
			addCart.click();
		}
		String countInCart = String.valueOf(addCarts.size());
		// --Going to cart
		WebElement checkOutToCart = driver.findElement(By.cssSelector(".nav-link.btn.btn-primary"));
		if (checkOutToCart.getText().contains(countInCart)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		checkOutToCart.click();
		// --Checking out
		WebElement checkOut = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-success")));
		checkOut.click();
		// --Auto Suggestive Drop Down
		WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
		country.sendKeys("Ind");
		// ----Waiting for suggestions in in drop down
		List<WebElement> suggestions = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='suggestions'] a")));
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase("India")) {
				suggestion.click();
				break;
			}
		}
		driver.findElement(By.cssSelector("label[for='checkbox2']")).click();
		driver.findElement(By.xpath("//input[@value='Purchase']")).click();
		// --Verify alert Message
		WebElement msgSuccess = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));
		if (msgSuccess.getText().contains("Success")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

}