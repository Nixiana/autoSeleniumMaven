package OrangeHRM_Duy;

import static common.Browser.currentUrl;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLoginAdmin {
	public String urlTest = "https://opensource-demo.orangehrmlive.com/";
	public String pathChromeDriver = "D://OrangeHRM//chromedriver.exe";
	public WebDriver driver;
	
	@BeforeTest
	public void BeforeTest() {
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(urlTest);
	}
	
	@AfterTest
	public void AfterTest() {
		driver.close();
		driver.quit();
	}
	
	@Test
	public void testLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		
		
		//Open browser and vavigate to OrangHRM		
		WebElement userNameTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		userNameTextBox.sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		//Get and print account name
		WebElement accoutName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']")));
		System.out.println(accoutName.getText());
		
		//Navigate to Admin page
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		WebElement adminTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='User Management']")));
		Assert.assertEquals(adminTitle.getText(), "User Management");
		
		
		
				
	}
}
