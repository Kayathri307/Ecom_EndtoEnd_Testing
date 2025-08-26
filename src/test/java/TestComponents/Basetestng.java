package TestComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import IBM.Selenium.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetestng {

	protected WebDriver driver;
	public WebDriver getDriver() {
        return driver;
    }
    public LandingPage LandingPage;

    public WebDriver initializeDriver() throws Exception {
        // Temporarily disable properties file usage
        String browser = "chrome"; // Hardcoded for now
        System.out.println("Browser is: " + browser);

        String browsername =System.getProperty("browser") !=null ? System.getProperty("browser"):browser;
        if (browsername.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (browsername.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            // Suppress automation info bars and password popup
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-infobars");

            // Disable popups and permissions
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.geolocation", 2);        // Block location
            prefs.put("profile.default_content_setting_values.media_stream_mic", 2);   // Block mic
            prefs.put("profile.default_content_setting_values.media_stream_camera", 2); // Block camera
            prefs.put("profile.default_content_setting_values.notifications", 2);      // Block notifications
            prefs.put("profile.default_content_setting_values.popups", 2);             // Block popups

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
        
            

        } else if (browsername.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else {
            throw new RuntimeException("Unsupported browser: " + browsername);
        }

        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

	}
	//To give life to the driver passing the driver from the listener 
    public String getscreenShot(String testCaseName,WebDriver driver ) throws IOException
    {
  	  TakesScreenshot  ts = (TakesScreenshot)driver;
  	File source = ts.getScreenshotAs(OutputType.FILE);
  	 // Define the folder path
     String screenshotsDir = System.getProperty("user.dir") + "/reports/screenshots/";
     
     // Create the folder if it doesn't exist
     File folder = new File(screenshotsDir);
     if (!folder.exists()) {
         folder.mkdirs();
     }

     // Save the screenshot with the test case name
     String destinationPath = screenshotsDir + testCaseName + ".png";
     File destination = new File(destinationPath);

     FileUtils.copyFile(source, destination);
     return destinationPath;
     }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws Exception {
        driver = initializeDriver();
        LandingPage = new LandingPage(driver);
        LandingPage.Url();
        return LandingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Properly close browser session
        }
    }
}
