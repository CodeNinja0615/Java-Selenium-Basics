package selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
		List<WebElement> links = footerDriver.findElements(By.cssSelector("a[href*='http']"));
		
		SoftAssert softAss = new SoftAssert();
		for (WebElement link : links) {
			String url = link.getAttribute("href");
//			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection)new URI(url).toURL().openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			softAss.assertTrue(responseCode<400,"Bad URL: "+ url +" "+ responseCode);
		}
		softAss.assertAll();
		driver.quit();
	}
}
