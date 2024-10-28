package selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CalendarTest {

	public static void main(String[] args) {
		String monthOfYear = "6";
		String dayOfMonth = "15";
		String year = "2026";
		String[] calendar = { monthOfYear, dayOfMonth, year };

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.cssSelector(".react-date-picker__calendar-button.react-date-picker__button")).click();
		driver.findElement(By.className("react-calendar__navigation__label")).click();
		driver.findElement(By.className("react-calendar__navigation__label")).click();

		driver.findElement(By.xpath("//button[text()='" + year + "']")).click();
		List<WebElement> calMonths = driver
				.findElements(By.cssSelector(".react-calendar__year-view__months button abbr"));
		calMonths.get(Integer.parseInt(monthOfYear) - 1).click();
		driver.findElement(By.xpath("//abbr[text()='" + dayOfMonth + "']")).click();

		String calMonth = driver.findElement(By.cssSelector(
				".react-date-picker__inputGroup__input.react-date-picker__inputGroup__month.react-date-picker__inputGroup__input--hasLeadingZero"))
				.getAttribute("value");
		if (calMonth.contains(monthOfYear)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		List<WebElement> dateList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));
		for (int i = 0; i < dateList.size(); i++) {
			String text = dateList.get(i).getAttribute("value");
			Assert.assertEquals(text, calendar[i]);
		}
		System.out.println("Date Matching");
		driver.close();
	}

}
