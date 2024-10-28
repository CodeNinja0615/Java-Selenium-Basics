package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice05 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		WebElement frameTop = driver.findElement(By.xpath("//frame[@name='frame-top']"));
		driver.switchTo().frame(frameTop);
		WebElement frameMid = driver.findElement(By.xpath("//frame[@name='frame-middle']"));
		driver.switchTo().frame(frameMid);
		String text = driver.findElement(By.id("content")).getText();
		System.out.println(text);
	}

}
