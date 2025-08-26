package IBM.Selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import AbstarctComponents.Abstract;

public class Cartpage extends Abstract {
	WebDriver driver;
	public Cartpage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	
	
	
	public Boolean VerifyProductDisplay(String productName) {
		 for (WebElement product : cartProducts) {
		        System.out.println("Cart contains: '" + product.getText() + "'");
		    }

		    Boolean match = cartProducts.stream()
		        .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		    
		   
		    return match;

	}
	public CheckOutpage checkOutButton() {
	    // Scroll the button into view
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutEle);
	    
	    // Add an explicit wait until it is clickable
	    elementToBeClickable(checkoutEle);
	    
	    // Wait a little for any overlay to disappear (like toast/snackbar)
	    try {
	        Thread.sleep(1000); // adjust if needed
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    checkoutEle.click();
	    CheckOutpage checkOutpage = new CheckOutpage(driver);
	    return checkOutpage;
	}



	
	

}
