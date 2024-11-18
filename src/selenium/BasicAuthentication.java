package selenium;

import java.net.URI;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAuthentication {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("herokuapp");

		((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "admin"));
		driver.get("https://the-internet.herokuapp.com/basic_auth");
//		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		
	}
}
