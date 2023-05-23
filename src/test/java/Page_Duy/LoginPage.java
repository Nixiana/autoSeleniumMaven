package Page_Duy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

	private WebDriver driver;
	
    By usernameTextBox  = By.xpath("//input[@name='username']");
    By passwordTextBox  = By.xpath("//input[@name='password']");
    By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");
    By widgetCard       = By.xpath("//div[@class='orangehrm-dashboard-widget-name']/p[@class='oxd-text oxd-text--p']");
    By dashBoardTitle   = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
    By loginLogo        = By.xpath("//div[@class='orangehrm-login-layout-blob']/div[@class='orangehrm-login-logo']");
    By adminPageTitle   = By.xpath("//div[@class='oxd-topbar-header-title']/span[@class='oxd-topbar-header-breadcrumb']");
    By adminPageButton  = By.xpath("//li[@class='oxd-main-menu-item-wrapper']/a[@href='/web/index.php/admin/viewAdminModule']");
    By systemUserFilter = By.xpath("//div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']");
    By systemUserRecord = By.xpath("//div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']");
	
    public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
    
    public String getLoginPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
    
    public boolean verifyLoginPageTitle() {
		String expectedTitle = "OrangeHRM";
		return getLoginPageTitle().equals(expectedTitle);
	}
    
    public void login(String username, String password) throws Exception {
		enterUserName(username);
		enterPassword(password);
		clickLoginButton();
		Thread.sleep(1000);
	}

    public void clickLoginButton() {
		// TODO Auto-generated method stub
		WebElement login = driver.findElement(loginButton);
		if (login.isDisplayed()) {
			login.click();
		}
		
	}

    public void enterPassword(String password) {
		// TODO Auto-generated method stub
    	WebElement passwordTxtBox = driver.findElement(passwordTextBox);
		if (passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(password);
	}

    public void enterUserName(String username) {
		// TODO Auto-generated method stub
    	WebElement userNameTextBox = driver.findElement(usernameTextBox);
		if (userNameTextBox.isDisplayed())
			userNameTextBox.sendKeys(username);
	}
    
    public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
}
