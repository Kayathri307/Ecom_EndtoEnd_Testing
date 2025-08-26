package IBM.Selenium;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestComponents.Basetestng;
import TestComponents.Retry;

public class ErrorValidation extends Basetestng{
	@Test(
		         // Supplies test data
		    groups = {"Purchase"},            // Assigns this test to 'Purchase' group
		    retryAnalyzer = Retry.class       // Retries this test on failure using custom logic
		)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		
		LandingPage.loginAppCrendentails("kayathrikayu307@gmail.com", "Kaathri21");
		Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());

	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

String productname = "ZARA COAT 3";
		
		productCatalog productCatalog =LandingPage.loginAppCrendentails("kayathri307@gmail.com", "Kayathri21");
		List<WebElement> products = productCatalog.getproductlist();
		productCatalog.addToCart(productname);
	    Cartpage cartpage =productCatalog.cartButton();
	     Boolean match = cartpage.VerifyProductDisplay(productname);
	    Assert.assertTrue(match);
		
	

	}

}
