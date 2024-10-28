package selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScopeTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//		List<WebElement> links = driver.findElements(By.tagName("a"));
//		for (int i = 0; i <= links.size()-1; i++) {
//			String link = links.get(i).getAttribute("href");
//			if(link != "") {
//				System.out.println(link);
//			}
//		}

		WebElement footerDriver = driver.findElement(By.id("gf-BIG"));

		List<WebElement> footerLinks = footerDriver.findElements(By.cssSelector("a[href*='http']"));
		for (WebElement footerLink : footerLinks) {
			String link = footerLink.getAttribute("href");
			System.out.println(link);
		}

		WebElement firstColumnDriver = footerDriver.findElement(By.xpath("(//table//td/ul)[1]"));
		List<WebElement> links1 = firstColumnDriver.findElements(By.cssSelector("a[href*='http']"));
		System.out.println(links1.size());
		String clickOnLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		for (WebElement link : links1) {
			link.sendKeys(clickOnLink);
		}
		Thread.sleep(5000);
		Set<String> windows = driver.getWindowHandles();

		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		driver.quit();
	}
}
