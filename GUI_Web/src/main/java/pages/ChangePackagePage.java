package pages;

import com.github.javafaker.Faker;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChangePackagePage {
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    private final Faker faker;
    private final By groupWithMOFANoIssued = By.xpath("//*[@id='MofaIssuedTabCount']");
    private final By qaAction = By.xpath("(//*[@id='qa-actions']/i)[1]");
    private final By editPackage = By.xpath("/html/body/div[5]/div/a[7]");

    private final By selectAllMutamers = By.xpath("(//*[contains(@class,'checkbox')]/span)[1]");
    private final By allMutamersList = By.xpath("//*[@id='MuatamerList']/tbody");

    private final By packageName = By.xpath("//* [@id='PackageName']");
    private final By packageDesc = By.xpath("//* [@id='PackageDescription']");

    private final By nextStep = By.xpath("//* [contains(text(),'Next Step')]");


    private final By arrivalDate = By.xpath("//* [@id='Search_ArrivalFlightDate']");
    private final By todayDate = By.xpath("//*[contains(@class, 'today day')]");


    private final By searchButton = By.xpath("(//*[@type='button']/span)[1]");
    private final By selectFirstFlight = By.cssSelector("#tblArrivalFlightsBody > tr:nth-child(1) > td:nth-child(7) > label > span");

    private final By departureFlightTab = By.xpath("//*[@id='kt_form']/div[2]/div[1]/div/div/div[1]/ul/li[2]/label");
    private final By departureDate = By.xpath("//* [@id='Search_DepartureFlightDate']");

    private final By searchDepartureButton = By.xpath("(//*[@type='button']/span)[2]");
    private final By selectFirstDepartureFlight = By.cssSelector("#tblDepartureFlightsBody > tr.odd > td:nth-child(7) > label > span");

    private final By cityDDL = By.xpath("//*[@title='City']");
    private final By cityMakkah = By.xpath("//*[contains(@id,'select2-City-result') and text()='Makkah']");
    private final By addCityButton = By.xpath("//*[@id='addCityButton']");

    private final By clickMakkahHotel = By.xpath("//*[contains(@id,'headingCity')]");
    private final By brn = By.xpath("//*[@name='BRN']");


    private final By hotelDDL = By.xpath("//*[@title='Hotel Name']");
    private final By hotelValue = By.xpath("//*[contains(@id,'select2-None_GDS_Hotel_Makkah')]/li[3]");
    private final By hotelPrice = By.xpath("//*[@name='None_GDS_PurchasingPrice']");
    private final By hotelCheckoutDate = By.xpath("//*[@name='None_GDS_CheckOut']");
    private final By hotelAdd = By.xpath("//*[contains(@id,'HotelBrnForm')]//button");

    private final By transportationFirst = By.xpath("(//*[contains(@id,'headingTrans')])[1]");


    private final By transportationSecond = By.xpath("(//*[contains(@id,'headingTrans')])[2]");

    private final By submitToUO = By.xpath("//button[contains(@id,'btnSubmit')]");







    public ChangePackagePage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.faker = new Faker();
        this.testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }
    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void changePackage(){

        driver.browser().navigateToURL(testData.getTestData("url")+"/bsp/UmrahOperators/home/submittedgroups");
        click(groupWithMOFANoIssued);
        driver.element().clickUsingJavascript(qaAction);
        driver.element().clickUsingJavascript(editPackage);

        waitForElementVisibility(allMutamersList);

        click(selectAllMutamers);
        click(nextStep);

        type(packageName, "Automation edit Program " + faker.lorem().word());
        type(packageDesc, "Automation edit Program Desc " + faker.lorem().word());
        click(nextStep);

        click(nextStep);

        click(nextStep);

        click(nextStep);

        click(submitToUO);










    }

    public void click(By by){
        driver.element().click(by);
    }
    public ChangePackagePage type(By by, String text){
        driver.element().type(by, text);
        return this;
    }
    public String calculateDatePlus(int days) {
        LocalDate todayPlus = LocalDate.now().plusDays(days);
        return todayPlus.format(DateTimeFormatter.ofPattern("dd")); // Format it as needed (e.g., day only or full date)
    }
}
