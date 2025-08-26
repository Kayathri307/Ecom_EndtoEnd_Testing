package IBM.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstarctComponents.Abstract;

public class LandingPage extends Abstract{
	WebDriver driver;
	
	public  LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	//Locator
	@FindBy(id ="userEmail")
	WebElement Userid;
	
   @FindBy(id="userPassword")
   WebElement UserPassword;
   
   @FindBy(css="div #login")
   WebElement Login;
   @FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
   public productCatalog loginAppCrendentails(String Username,String Password)
   {
	   Userid.sendKeys(Username);
	   UserPassword.sendKeys(Password);
	   Login.click();
	   productCatalog productCatalog = new productCatalog(driver);
	   return productCatalog;
   }
   public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
   public void Url() {
	   driver.get("https://rahulshettyacademy.com/client");
   }
   
}
