package javaBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InterviewQuest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.switchTo().frame(0);
		
	}
}
