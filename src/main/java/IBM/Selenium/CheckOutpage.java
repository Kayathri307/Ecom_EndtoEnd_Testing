package IBM.Selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

import AbstarctComponents.Abstract;

public class CheckOutpage extends Abstract {
	WebDriver driver;
	public CheckOutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".action__submit")
	WebElement submitOrder;
	
	@FindBy(css =".form-group input")
	WebElement selectCountry;
	
	@FindBy(css=".list-group button")
	List<WebElement> options;
	
	public void selectCountry(String countryname ) {
		Actions action = new Actions(driver);
		
		action.click(selectCountry).sendKeys(countryname).clickAndHold().build().perform();
		waitForWebElementToAppear(options);
		
	//List<String> option = options.stream().filter(options->options.getText().equals(countryname)).toList();
		
		options.stream().filter(option->option.getText().equals(countryname)).forEach(
			option->{
					System.out.println(option.getText());
			});
	
	 WebElement matchedOptions =options.stream().filter(option->option.getText().equals(countryname)).findFirst().orElse(null);
	 action.click(matchedOptions).perform();
	 
		
				
	}
	
	public ConfirmationPage submitOrder() {
	    
	    elementToBeClickable(submitOrder);
	    
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitOrder);
	    
	    try {
	        submitOrder.click();
	    } catch (Exception e) {
	        // Fallback to JS click if normal click fails
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitOrder);
	    }
	    
	    return new ConfirmationPage(driver);
	
	}
}

	

	
