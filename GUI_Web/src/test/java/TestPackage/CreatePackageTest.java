package TestPackage;

import com.shaft.driver.SHAFT;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreatePackagePage;
import pages.EACreateGroupPage;
import pages.UmrahLoginPage;


@Execution(ExecutionMode.CONCURRENT)
public class CreatePackageTest {
    private  SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    private final int numberOfMutamers = 1;

    @Test(description = "createPackage")
    public void createPackage() {
        new CreatePackagePage(driver).createPackage(numberOfMutamers);
    }


    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass(){
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    @BeforeMethod(description = "Setup Browser instance.")
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new UmrahLoginPage(driver).navigateToURL();
        new UmrahLoginPage(driver).loginByUser(testData.getTestData("EAUserName"), testData.getTestData("password"));
        new EACreateGroupPage(driver).createGroup(numberOfMutamers);
        driver.browser().navigateToURL("https://web-huic.haj.gov.sa/newumrah-test/bsp/Identity/Logout");

        UmrahLoginPage umrahLoginPage = new UmrahLoginPage(driver);
        umrahLoginPage.navigateToURL();
        umrahLoginPage.loginByUser(testData.getTestData("UOUserName"), testData.getTestData("password"));



    }

    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        //driver.quit();
    }
}
