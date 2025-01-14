package pages;

import com.github.javafaker.Faker;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CreateTafweejPage {
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    private Home homePage;

    private final By expandFirstPendingTafweej = By.xpath("(//div[text()='Pending Tafweej']/ancestor::td/ancestor::tr/td[11]/a)[1]");
    private final By createTafweejButton = By.xpath("/html/body/div[4]/div/a[1]/span");
    private final By selectArrivalToSaudi = By.xpath("(//*[@id='TafweejType'])[1]/ancestor::label/span");

    private final By showPilgrim = By.xpath("//button[contains(text(), 'Show Pilgrims')]");

    private final By selectAllMutamers = By.xpath("(//*[contains(@class,'checkbox')]/span)[1]");
    private final By allMutamersList = By.xpath("//*[@id='MuatamerList']/tbody");

    private final By vehicalType = By.xpath("//*[@id='VehicalType']");
    private final By vehicalTypeValue = By.xpath("//*[@id='VehicalType']/option[@value=3]");
    private final By addVehicalType = By.xpath("//*[text()='Add']");

    private final By nextStep = By.xpath("//* [contains(text(),'Next Step')]");
    private final By submitVehicle = By.xpath("//button[contains(@id,'btnSubmit')]");







    public CreateTafweejPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.testData = new SHAFT.TestData.JSON("simpleJSON.json");
        this.homePage = new Home(driver);

    }
    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void createTafweej(){
        driver.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120)) // Increase page load timeout
                .scriptTimeout(Duration.ofSeconds(180));

        driver.getDriver().get(testData.getTestData("url")+"/bsp/UmrahOperators/TafweejGroup");
        click(expandFirstPendingTafweej);
        click(createTafweejButton);
        click(selectArrivalToSaudi);
        driver.element().clickUsingJavascript(showPilgrim);

        waitForElementVisibility(allMutamersList);

        click(selectAllMutamers);
        click(nextStep);
        boolean isDisplayed = false;
        int attempts = 0;
        while (!isDisplayed && attempts < 10) { // Retry up to 10 times
            isDisplayed = driver.element().isElementDisplayed(vehicalType);
            if (!isDisplayed) {
                try {
                    Thread.sleep(1000); // Wait 1 second before retrying
                    click(nextStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            attempts++;
        }

        homePage.selectRandomFromDDL(vehicalType);
        click(addVehicalType);
        click(submitVehicle);
            }

    public void click(By by){
        driver.element().click(by);
    }
    public CreateTafweejPage type(By by, String text){
        driver.element().type(by, text);
        return this;
    }
}
