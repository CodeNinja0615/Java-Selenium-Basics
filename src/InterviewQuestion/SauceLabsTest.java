package InterviewQuestion;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLabsTest {

	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.get(" https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@Test
	public void sauceLabsTest() throws IOException {
		//----Login user
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		WebElement elemnt = driver.findElement(By.cssSelector(".product_sort_container"));
		
		//---Sorting with price high to low
		Select drdo = new Select(elemnt);
		drdo.selectByValue("lohi");
		
		//--Adding the last(most expensive) and second last(Second most expensive) item to cart
		List<WebElement> products = driver.findElements(By.cssSelector(".pricebar"));
//		for(int i = 0; i < products.size(); i++) {
		products.get(products.size()-1).findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ")).click();
		products.get(products.size()-2).findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ")).click();
//		}
		driver.findElement(By.cssSelector(".shopping_cart_link")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));
		Assert.assertEquals(cartItems.size(), 2);
		Double sum = 0.0;
		List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));
		for(WebElement price: prices) {
			String strPrice = price.getText();
//			String SplStr = strPrice.split("$")[0];
			String SplStr =strPrice.substring(1, strPrice.length());
			double priceD = Double.parseDouble(SplStr);
			sum = sum + priceD;
		}
		Assert.assertEquals(sum.toString(), "79.98");
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("postal-code")).sendKeys("226001");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		String confMsg = driver.findElement(By.cssSelector(".complete-header")).getText();
		Assert.assertEquals(confMsg, "Thank you for your order!");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(" purchase_success.png"));
	}
	
	@Test
	public void invalidLogin() throws IOException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("invalid_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("wrong_pass");
		driver.findElement(By.id("login-button")).click();
		String errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(" login_failed.png"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
