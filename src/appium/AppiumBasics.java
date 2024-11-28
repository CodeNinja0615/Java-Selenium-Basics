package appium;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends AppiumBaseTest {
//	https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
	@Test
	public void appiumTest() throws MalformedURLException, URISyntaxException {
//		driver.activateApp("io.appium.android.apis");

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Sameer");
		List<WebElement> buttons = driver.findElements(AppiumBy.className("android.widget.Button"));
		buttons.get(1).click();
	}
}
