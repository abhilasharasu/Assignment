package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

import java.time.Duration;

public class GenerateKeyTest extends BaseTest {

    @Test
    public void validateKeyMaskingAfter10Seconds() {

        // Open UIDAI Website
        driver.get("https://uidai.gov.in/");

        DashboardPage dashboard =
                new DashboardPage(driver);

        // Click Generate Key Button
        dashboard.clickGenerateButton();

        // Capture Generated Key
        String visibleKey =
                dashboard.getGeneratedKey();

        System.out.println("Generated Key Before Masking: "
                + visibleKey);

        // Validate Key Format
        Assert.assertTrue(
                visibleKey.startsWith("sk_test_"),
                "Generated key format is invalid"
        );

        // Wait Until Key Gets Masked
        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(15));

        wait.until(driver ->
                dashboard.getGeneratedKey()
                        .contains("****"));

        // Capture Masked Key
        String maskedKey =
                dashboard.getGeneratedKey();

        System.out.println("Generated Key After Masking: "
                + maskedKey);

        // Validate Key Masking
        Assert.assertTrue(
                maskedKey.contains("****"),
                "Key is not masked after 10 seconds"
        );
    }
}