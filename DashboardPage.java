package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {

        this.driver = driver;
    }

    By generateButton =
            By.id("generateKeyBtn");

    By generatedKey =
            By.id("generatedKey");

    public void clickGenerateButton() {

        driver.findElement(generateButton).click();
    }

    public String getGeneratedKey() {

        return driver.findElement(generatedKey)
                .getText();
    }
}