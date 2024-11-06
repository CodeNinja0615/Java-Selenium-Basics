package chromeDevTools;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.emulation.Emulation;

public class MobileEmulatorTest {
	
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		//---Send command to CDP Method to invoke and get access to chrome dev tools
		//---https://chromedevtools.github.io/devtools-protocol/ //---Get commands form here
		
//		devTools.send(Emulation.setDeviceMetricsOverride(600, 1080, 75, true, Optional.empty(), Optional.empty(),
//				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
//				Optional.empty(), Optional.empty(), Optional.empty()));
		
		
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 600);
		deviceMetrics.put("height", 1080);
		deviceMetrics.put("deviceScaleFactor", 75);
		deviceMetrics.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		driver.findElement(By.linkText("Library")).click();
	}

}
