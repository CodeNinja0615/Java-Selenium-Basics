package selenium;
 
import java.time.Duration;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class Practice01 {
 
	public static void main(String[] args) throws InterruptedException {		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> checkBoxesText = driver.findElements(By.xpath("//input[@type='checkbox']/parent::label"));
		for(WebElement checkBoxText: checkBoxesText) {
			System.out.println(checkBoxText.getText());
		}
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		checkBoxes.get(0).click();
		System.out.println(checkBoxes.get(0).isSelected());
		checkBoxes.get(0).click();
		System.out.println(checkBoxes.get(0).isSelected());
		System.out.println(checkBoxes.size());
	}
}