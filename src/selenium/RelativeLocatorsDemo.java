package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

public class RelativeLocatorsDemo {
	
	@Test
	public void relativeLocators() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		String text = driver.findElement(RelativeLocator.with(By.tagName("label")).above(By.xpath("//input[@name='name']"))).getText();
		System.out.println(text);
		driver.findElement(RelativeLocator.with(By.cssSelector("[name='bday']")).below(By.tagName("label"))).sendKeys("15-06-2002");
		driver.findElement(RelativeLocator.with(By.id("exampleCheck1")).toLeftOf(By.cssSelector("[for='exampleCheck1']"))).click();
		driver.quit();
	}

}
