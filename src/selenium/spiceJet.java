package selenium;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class spiceJet {
	public static void main(String[] args) {
		ChromeOptions chromeOptions = new ChromeOptions();
		// Disable pop-ups
		chromeOptions.addArguments("--disable-popup-blocking");

		// Automatically grant location access permission
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.geolocation", 1); // 1 - Allow, 2 - Block

		// Disable notifications
		prefs.put("profile.default_content_setting_values.notifications", 1); // 1 - Allow, 2 - Block

		chromeOptions.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.spicejet.com/");
//		driver.findElement(By.xpath(
//				"(//div[@class='css-1dbjc4n r-18u37iz r-19h5ruwr-184en5c'] //div[@class='css-76zvg2 css-bfa6kz r-1862ga2 r-1gkfh8e'])[2]"))
//				.click();
		driver.findElement(By.xpath(
				"//div[@class='css-1dbjc4n r-18u37iz r-19h5ruw r-184en5c']//div[@class='css-76zvg2 css-bfa6kz r-1862ga2 r-1gkfh8e' and text()='Currency']"))
				.click();
		List<WebElement> options = driver.findElements(By.xpath(
				"//div[@class='css-1dbjc4n r-1habvwh r-1loqt21 r-1777fci r-1mi0q7o r-1yt7n81 r-m611by r-1otgn73']/div[@class='css-76zvg2 r-homxoj r-ubezar']"));
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("USD")) {
				option.click();
				break;
			}
		}
	}
}
