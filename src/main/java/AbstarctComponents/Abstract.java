package AbstarctComponents;
import java.time.Duration;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import IBM.Selenium.Cartpage;
import IBM.Selenium.Myorderpage;

import IBM.Selenium.Cartpage;

public class Abstract {
   
	 WebDriver driver;
 public Abstract(WebDriver driver) 
{
	  this.driver = driver;
	  PageFactory.initElements(driver, this);
	
}
 @FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
 @FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
 
public void waitForElementToBeAppear(WebElement submitOrder) 
	{

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) submitOrder));
}
public void waitForWebElementToAppear(List<WebElement> options) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(options));

}
protected void waitForElementToBeAppear(By product2) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy( product2));
	
}

protected void waitForWebElementToAppear(WebElement Locoter) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(Locoter));
	
}

public void waitForElementToDisappear(WebElement ele) throws InterruptedException
{
	Thread.sleep(1000);
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//	wait.until(ExpectedConditions.invisibilityOf(ele));


}
public Cartpage cartButton() {
	
	cartButton.click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
    Cartpage cartPage = new Cartpage(driver);
	return cartPage;
}
public Myorderpage myOrderButton() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(orderHeader));
	

	orderHeader.click();
    Myorderpage myorderpage = new Myorderpage(driver);
    return myorderpage;
}
public void elementToBeClickable(WebElement checkoutEle) {
	   // Wait for checkout button to be clickable
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(checkoutEle));
}
}