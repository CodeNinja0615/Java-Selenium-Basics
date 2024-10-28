package selenium;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptionsDemo {

	public static void main(String[] args) {
		//--https://www.selenium.dev/documentation/webdriver/drivers/options/
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("");
//		options.setCapability("proxy", proxy);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());
	}

}
