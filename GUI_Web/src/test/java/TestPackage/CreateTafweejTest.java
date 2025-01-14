package TestPackage;

import com.shaft.driver.SHAFT;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ChangePackagePage;
import pages.CreateTafweejPage;
import pages.UmrahLoginPage;

import java.time.Duration;

@Execution(ExecutionMode.CONCURRENT)
public class CreateTafweejTest {
    private  SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "createTafweej")
    public void createTafweej() {
        new CreateTafweejPage(driver).createTafweej();


    }


    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass(){
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    @BeforeMethod(description = "Setup Browser instance.")
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        System.setProperty("browser.pageLoadTimeout", "120");
        System.setProperty("browser.scriptTimeout", "120");
        System.setProperty("disable.openReport", "true");
        UmrahLoginPage umrahLoginPage = new UmrahLoginPage(driver);

        umrahLoginPage.navigateToURL();
        umrahLoginPage.loginByUser(testData.getTestData("UOUserName"), testData.getTestData("password"));
        driver.getDriver().manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(120))
                .scriptTimeout(Duration.ofSeconds(120));

    }

    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        //driver.quit();
    }
}
