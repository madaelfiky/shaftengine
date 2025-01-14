package TestPackage;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EACreateGroupPage;
import pages.UmrahLoginPage;

public class EACreateGroupPageTest {
    private  SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    private final int numberOfMutamers = 3;

    @Test(description = "createGroup")
    public void createGroupEA() {
        new EACreateGroupPage(driver).createGroup(numberOfMutamers);
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
    }

    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        //driver.quit();
    }
}
