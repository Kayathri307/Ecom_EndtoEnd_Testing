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

public class Myorderpage extends Abstract {

	WebDriver driver;
    public Myorderpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
    public Boolean VerifyOrderDisplay(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until at least one product name is visible
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));

        return productNames.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }


	



	
	

}
