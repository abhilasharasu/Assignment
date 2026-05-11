package base;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass
    public void apiSetup() {

        RestAssured.baseURI =
                "https://uidai.gov.in";
    }

    @BeforeMethod
    public void browserSetup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}