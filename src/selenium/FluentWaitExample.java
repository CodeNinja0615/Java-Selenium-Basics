package selenium;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitExample {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// WebDriverWait now has FluentWait functionality in Selenium 4
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Maximum wait time
//		wait.pollingEvery(Duration.ofSeconds(10)); // Poll every 2 seconds
//		wait.ignoring(NoSuchElementException.class); // Ignore NoSuchElementExceptions

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		// Waiting for a specific element to be visible
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

		// Custom condition using the apply method (checking visibility of the element)
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement targetElement = driver.findElement(By.id("finish"));
				if (targetElement.isDisplayed()) {
					System.out.println("Element is visible...");
					return targetElement; // Element is visible, return it
				} else {
					System.out.println("Waiting for element to be visible...");
					return null; // Keep waiting until the element becomes visible
				}
			}
		});
		System.out.println(element.getText());

	}

}
