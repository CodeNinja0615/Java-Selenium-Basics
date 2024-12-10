package WinAppDriver;

import java.io.File;
import java.net.URI;
import java.time.Duration;
import org.openqa.selenium.Keys;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.options.WindowsOptions;

public class WinAppDriverDemo {
	public static void main(String[] args) {
		AppiumDriverLocalService service = null;
		WindowsDriver driver = null;
		try {
			String currentUser = System.getProperty("user.name");
			service = new AppiumServiceBuilder()
					.withAppiumJS(new File("C://Users//" + currentUser
							+ "//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
			service.start();
			WindowsOptions options = new WindowsOptions();
			options.setCapability("platformName", "Windows");
			options.setCapability("deviceName", "WindowsPC");
			// ----CMD---Get-StartApps
			options.setCapability("app", "Microsoft.GamingApp_8wekyb3d8bbwe!Microsoft.Xbox.App");
			driver = new WindowsDriver(new URI("http://127.0.0.1:4723").toURL(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(AppiumBy.accessibilityId("ProfileSettingsButton_MainButton")).click();
			driver.findElement(AppiumBy.name("View profile")).click();
//	        driver.findElement(AppiumBy.accessibilityId("SearchBoxInput")).sendKeys("Minecraft", Keys.ENTER);
			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			driver.quit();
			service.stop();
		}
	}
}