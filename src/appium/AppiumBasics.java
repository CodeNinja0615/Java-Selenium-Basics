package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics {

	@Test
	public void appiumTest() throws MalformedURLException, URISyntaxException {
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C://Users//HP//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ZA222JVBYL");
//		options.setApp("C:/Users/HP/Downloads/General-Store.apk");
//		adb shell dumpsys window | findstr "mCurrentFocus"
//		options.setAppPackage("com.instagram.barcelona");
//		options.setAppActivity("com.instagram.barcelona.mainactivity.BarcelonaActivity");
		options.setNoReset(true);
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.activateApp("com.instagram.barcelona");
		driver.terminateApp("com.instagram.barcelona");
		driver.close();
		service.stop();
	}
}
