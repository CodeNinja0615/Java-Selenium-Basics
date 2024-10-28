package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice07 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> rows = driver.findElements(By.cssSelector(".left-align #product tr:nth-child(1) th"));
		System.out.println("Number of Rows " + rows.size());
		List<WebElement> columns = driver.findElements(By.cssSelector(".left-align #product tr td:nth-child(3)"));
		System.out.println("Number of Column " + columns.size());
		List<WebElement> columnSecondContents = driver
				.findElements(By.cssSelector(".left-align #product tr:nth-child(3) td"));
		for (WebElement columnSecondContent : columnSecondContents) {
			System.out.print(columnSecondContent.getText() + "|");
		}
	}
}
