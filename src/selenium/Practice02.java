package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Practice02 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/angularpractice/");

		driver.findElement(
				By.xpath("//div/input[@class='form-control ng-untouched ng-pristine ng-invalid' and @name='name']"))
				.sendKeys("Sameer Akhtar");
		driver.findElement(By.cssSelector("[name='email']")).sendKeys("akhtarsameer743@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("Sameerking01!");
		driver.findElement(By.id("exampleCheck1")).click();

		WebElement dropDown = driver.findElement(By.id("exampleFormControlSelect1"));
		Select select = new Select(dropDown);
		select.selectByVisibleText("Male");

		driver.findElement(By.id("inlineRadio1")).click();
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys("15/06/2002");

		driver.findElement(By.className("btn-success")).click();
		System.out.println(
				driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText());

	}
}
