package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBaseTest {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, URISyntaxException {
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(
//						new File("C://Users//HP//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
		
		Map<String, String> env = new HashMap<>(System.getenv());
		env.put("ANDROID_HOME", "/Users/sameerakhtar/Library/Android/sdk");
		env.put("ANDROID_SDK_ROOT", "/Users/sameerakhtar/Library/Android/sdk");

		service = new AppiumServiceBuilder().usingDriverExecutable(new File("/opt/homebrew/opt/node@22/bin/node")) // Explicit Node.js path
				.withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js")) // Appium path
				.withEnvironment(env) // ✅ Pass environment variables explicitly
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ZA222JVBYL");
//		options.setApp("C:/Users/HP/Downloads/General-Store.apk");
//		adb shell dumpsys window | findstr "mCurrentFocus"
//		options.setAppPackage("com.androidsample.generalstore");
//		options.setAppActivity("com.androidsample.generalstore.MainActivity");

		options.setAppActivity("com.instagram.barcelona.mainactivity.BarcelonaActivity");
		options.setAppPackage("com.instagram.barcelona");
		options.setAppWaitForLaunch(true);
		options.setNoReset(true);
		options.setGpsEnabled(true);
		options.autoGrantPermissions();
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.unlockDevice();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
