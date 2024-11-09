package chromeDevTools;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.fetch.Fetch;

public class NetworkMocking {
	// ---Fetch
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty())); // ---To enable fetch events and issuing of
																			// requestPause
		// ---If pattern is empty it will work for all http calls else workon targetted
		// patterns of http calls

		devTools.addListener(Fetch.requestPaused(), request -> {
			if (request.getRequest().getUrl().contains("shetty")) {
				String mockedURL = request.getRequest().getUrl().replace("=shetty", "=BadGuy"); // ---Can chnage the api
																								// call value
				System.out.println(mockedURL);

				// -----If continueRequest is not sent Automation will not be able to launch url
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedURL),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));
			} else {
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));
			}
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("[routerlink*='library']")).click();
		System.out.println(driver.findElement(By.tagName("p")).getText());
	}
}
