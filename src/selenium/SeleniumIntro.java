package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumIntro /*implements WebDriver*/{

	public static void main(String[] args) {
		
		//-----Invoking Browser
		//---Chrome -- ChromeDriver -> Methods close, get
		// WebDriver -> interface to implement its own close, get
		// WebDriver methods + class methods
		
		//---chromedriver.exe -> Chrome Browser
		// Steps to invoke chrome driver //
		//--System.setProperty("webdriver.chrome.driver", "C:/Users/HP/Downloads/chromedriver.exe");
		// Selenium Manager //---> only active if the above line is not defined
		
		WebDriver driver = new ChromeDriver(); //---WebDriver implementation of ChromeDriver to access all the methods of WebDriver through class
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/");
		
		String title = driver.getTitle();
		
		System.out.println(title);
		String URL = driver.getCurrentUrl();
		
		System.out.println(URL);
		
		driver.quit();
		//driver.close();
		
	}

}
