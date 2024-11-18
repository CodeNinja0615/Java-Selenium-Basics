package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.chrome.ChromeDriver;

public class RobotClassDemo {
	
	public static void main(String[] args) throws AWTException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_ALT);
		robo.keyPress(KeyEvent.VK_F4);
		robo.keyRelease(KeyEvent.VK_F4);
		robo.keyRelease(KeyEvent.VK_ALT);
	}
}
