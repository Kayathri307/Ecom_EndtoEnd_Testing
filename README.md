Selenium TestNG Automation Framework

This project is a Test Automation Framework built using Java, Selenium WebDriver, TestNG, and Maven.
It follows the Page Object Model (POM) design pattern and supports data-driven testing with JSON, a retry mechanism for flaky tests, and screenshot capture on failure for easier debugging.

📂 Project Structure
Selenium/
│── src/main/java
│   ├── AbstractComponents/       # Base reusable components
│   ├── IBM.Selenium/             # Page Object classes (POM)
│   └── resources/                # Config & Extent Report utilities
│
│── src/test/java
│   ├── Data/                     # Test Data (JSON + DataReader utility)
│   ├── IBM.Selenium/             # Test Cases
│   └── TestComponents/           # BaseTest, Retry logic, Listeners
│
│── test-output/                  # Default TestNG reports
│── reports/                      # Extent Reports with screenshots
│── pom.xml                       # Maven dependencies
│── testng.xml                    # TestNG suite configuration
│── README.md                     # Project documentation

✨ Key Features

✅ Selenium WebDriver for browser automation
✅ TestNG for test execution & reporting
✅ Page Object Model (POM) for reusable and maintainable code
✅ JSON Data-Driven Testing for lightweight test input
✅ Retry Mechanism for re-running flaky tests
✅ Listeners with Screenshot Capture on failure
✅ Extent Reports with embedded screenshots for detailed analysis
✅ Cross-browser Testing support (Chrome, Edge, Firefox)

⚙️ Prerequisites

Java JDK 8+

Maven 3.6+

TestNG Plugin in Eclipse/IntelliJ

Browser installed (Chrome, Firefox, Edge)

🛠️ How to Run

Clone the repository

git clone <your-repo-url>
cd Selenium


Install dependencies

mvn clean install


Run TestNG suite

mvn test -DsuiteXmlFile=testng.xml


View Reports

TestNG Report → test-output/index.html

Extent Report → reports/index.html

📊 Reports & Examples
🔹 JSON Test Data (Purchase.json)
[
  {
    "email": "kayathrikayu307@gmail.com",
    "password": "Kayathri21",
    "product": "ZARA COAT 3"
  },
  {
    "email": "kayathri307@gmail.com",
    "password": "Kayathri21",
    "product": "ADIDAS ORIGINAL"
  }
]

🔹 Retry Mechanism
public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxRetry = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            return true;
        }
        return false;
    }
}

🔹 Screenshot on Failure (Extent Report Integration)
@Override
public void onTestFailure(ITestResult result) {
    WebDriver driver = ((BaseTest) result.getInstance()).driver;
    String screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
    test.addScreenCaptureFromPath(screenshotPath);
}

📝 Example Test Cases

AppTest.java → Valid purchase flow (JSON-driven)

ErrorValidation.java → Negative tests (invalid login, invalid product)

StartingPage.java → Login & navigation tests

Sample Extent Report
<img width="959" height="385" alt="image" src="https://github.com/user-attachments/assets/5f56e272-088e-47ec-baf1-9c5d7e005258" />


Passed Test

Failed Test with Screenshot

Skipped Test with reason

👤 Author

Kayathri
💼 Open to roles in QA | Automation Engineer | Full Stack Testing
