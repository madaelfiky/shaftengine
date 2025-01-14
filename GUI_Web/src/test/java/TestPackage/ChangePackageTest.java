package TestPackage;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ChangePackagePage;
import pages.UmrahLoginPage;

public class ChangePackageTest {
    private  SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "changePackage")
    public void changePackage() {
        new ChangePackagePage(driver).changePackage();
    }


    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass(){
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    @BeforeMethod(description = "Setup Browser instance.")
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new UmrahLoginPage(driver).navigateToURL();
        new UmrahLoginPage(driver).loginByUser(testData.getTestData("UOUserName"), testData.getTestData("password"));


    }

    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        //driver.quit();
    }
}
