package selenium;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Miscelleanous {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
//        driver.manage().deleteAllCookies();
//		driver.get(
//				"https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
//		WebElement webelm1 = driver.findElement(By.xpath("//input[@type='email']"));
//		webelm1.sendKeys("Akhtarsameer743@gmail.com");
//		WebElement webelm2 = driver.findElement(By.xpath("//input[@id='continue']"));
//		webelm2.click();
//		WebElement webelm3 = driver.findElement(By.xpath("//input[@type='password']"));
//		webelm3.sendKeys("sameerak");
//		WebElement webelm4 = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
//		webelm4.click();
		driver.get("https://www.google.com/");
		Thread.sleep(5000);
//		Set<Cookie> allCookies = driver.manage().getCookies();
//		for (Cookie cookie : allCookies) {
//			driver.manage().deleteCookie(cookie);
//			System.out.println("Deleted cookie: " + cookie.getName());
//			driver.navigate().refresh();
//		}
//		driver.manage().deleteCookieNamed("x-acbin");
//		driver.navigate().refresh();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\MovieWebDev\\ss.png"));
	}
}
