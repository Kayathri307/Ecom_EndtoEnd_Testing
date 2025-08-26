package IBM.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstarctComponents.Abstract;

public class ConfirmationPage extends Abstract {

	  WebDriver driver;

	public ConfirmationPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
	    waitForWebElementToAppear(confirmationMessage); // Assuming this is a utility in your Abstract class
	    return confirmationMessage.getText();
	}

	

	
	
}

	
