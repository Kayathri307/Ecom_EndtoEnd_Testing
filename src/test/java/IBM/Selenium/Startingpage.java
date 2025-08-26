package IBM.Selenium;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.deser.ValueInstantiator.Base;
import com.sun.net.httpserver.Authenticator.Retry;

import AbstarctComponents.Abstract;

import TestComponents.Basetestng;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Startingpage extends Basetestng{
  

public static WebDriver driver;
String productname = "ZARA COAT 3";
@Test(dataProvider="getData",groups= {"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws Exception{
		
		
		productCatalog productCatalog =LandingPage.loginAppCrendentails(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getproductlist();
		productCatalog.addToCart(productname);
	    Cartpage cartpage =productCatalog.cartButton();
	     Boolean match = cartpage.VerifyProductDisplay(productname);
	    Assert.assertTrue(match);
	    CheckOutpage checkOutpage =cartpage.checkOutButton();
	    checkOutpage.selectCountry("India");
	    ConfirmationPage confirmationPage = checkOutpage.submitOrder();
	    String ConfirmationMessage=  confirmationPage.getConfirmationMessage();
	    Assert.assertTrue( ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	    
	   
		
		
}
   @Test(dependsOnMethods={"submitOrder"})
	public void orderHistory() {
	   productCatalog productCatalog =LandingPage.loginAppCrendentails("kayathri307@gmail.com", "Kayathri21");
	   Myorderpage myorderpage=productCatalog.myOrderButton();
	   Assert.assertTrue(myorderpage.VerifyOrderDisplay(productname));
	   
	 }
   
   
   

   
   @DataProvider
   public Object[][] getData() throws IOException {
       List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/Data/Purchase.json");
       return new Object[][] {
           { data.get(0) },
           { data.get(1) }
       };
   }
}
   
  /*  @DataProvider
 public Object[][] getData() throws IOException
	  {
	  
		
		HashMap<String,String> map = new HashMap<String,String>();
	map.put("email", "kayathrikayu307@gmail.com");
	map.put("password", "Kayathri21");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "kayathrikayu307@gmail.com");
		map1.put("password", "Kayathri21");
		map1.put("product", "ADIDAS ORIGINAL");
	  
	    return new Object[][]  {{map}, {map1 } };
}
}*/
 /*@DataProvider
    public Object[][] getData()
   {
	   return new Object[][]  {{"kayathrikayu307@gmail.com","Kayathri21","ZARA COAT 3"}, {"kayathrikayu307@gmail.com","Kayathri21",
		   "ADIDAS ORIGINAL" } };
		  }}
		   /*starting method 
	   public  void submitOrder(String email, String password,String product) throws Exception{
		
		
		productCatalog productCatalog =LandingPage.loginAppCrendentails(email, password);
	   */
