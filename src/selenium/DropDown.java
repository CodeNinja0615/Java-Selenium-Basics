package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropDown {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		Boolean isSelected = driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected();
		Assert.assertFalse(isSelected);
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		isSelected = driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected();
		Assert.assertTrue(isSelected);

//		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//div[@class='row1 padding-bottom-3 home-dis-checkboxes']//label"));

		System.out.println(checkboxes.size());
//		for(WebElement checkbox: checkboxes){
//			checkbox.click();
//			System.out.println(checkbox.getText());
//		}

		// ---Static drop down
		WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());

		driver.findElement(By.id("divpaxinfo")).click();
		for (int i = 1; i < 5; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		driver.findElement(By.id("btnclosepaxoption")).click();
		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");

		// ----Dynamic drop down
		driver.findElement(By.xpath(
				"//div[@class='left1']/span[1]/following-sibling::input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"))
				.click();
		driver.findElement(By.xpath("//a[@value='BLR']")).click();

		// driver.findElement(By.xpath("(//a[@value='DEL'])[2]")).click();

//		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']/table[@id='citydropdown']/tbody/tr/td[@class='mapbg']/div[@class='search_options_menucontent']/div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul/li/a[@value='DEL']")).click();

		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='DEL']"))
				.click();
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");

		Thread.sleep(2000);

		// ----To click on element in Auto suggestive drop down
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}

		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();

//		System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());

		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		String attr = driver.findElement(By.id("Div1")).getAttribute("style");
		if (attr.contains("1")) {
			Assert.assertTrue(true);
			driver.findElement(By.name("ctl00$mainContent$view_date2")).click();
		} else {
			Assert.assertTrue(false);
		}
	}
}
