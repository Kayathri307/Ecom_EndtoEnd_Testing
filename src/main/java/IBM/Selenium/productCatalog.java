package IBM.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstarctComponents.Abstract;

public class productCatalog extends Abstract {
       WebDriver driver;
	
	public  productCatalog(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	//Locator
   @FindBy(css=".mb-3")
   List<WebElement> ProductList;
   @FindBy(css = ".ng-animating")
	WebElement spinner;
   
	By product = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
   
   
   public List<WebElement> getproductlist()
   {
	   waitForElementToBeAppear(product);
	return ProductList;
	
	 }
   
  
public WebElement getproductByname(String productName) {
	   WebElement prod =getproductlist().stream().filter(p->p.findElement(By.cssSelector("b")).getText()
			   .equals(productName)).findFirst().orElse(null);
	   return prod;
   }
   public void addToCart(String productName) throws InterruptedException {
	   WebElement prods =  getproductByname(productName);
	   prods.findElement( addToCart).click();
	   waitForElementToBeAppear(toastMessage);
	   waitForElementToDisappear(spinner);
   }
   
  
   
   

}
