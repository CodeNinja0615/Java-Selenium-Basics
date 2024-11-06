package chromeDevTools;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {
	
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		//---Send command to CDP Method to invoke and get access to chrome dev tools
		//---https://chromedevtools.github.io/devtools-protocol/ //---Get commands form here
		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude", 40);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
		driver.findElement(By.cssSelector(".LC20lb")).click();
	}
}
