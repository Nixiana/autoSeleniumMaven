package Page_Duy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class Browser {
	
	private WebDriver driver;
	static String driverPath = "resources\\drivers\\";
	
	public WebDriver getDriver() {
		return driver;
	}

	//select browser before run. Ex: Chrome, firefox, IE
	public void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		default:
			System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
			driver = initChromeDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching Chrome browser...");
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void openBrowser(String browserType, String appURL) {
		try {
			
			//init Browser and driver
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}
	
	@AfterClass
	public void closeBrowser() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}
	
}
