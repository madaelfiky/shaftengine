package pages;

import com.github.javafaker.Faker;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatePackagePage {
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    private final Faker faker;
    private final By uoTabCount = By.xpath("//*[@id='UoTabCount']");
    private final By qaAction = By.xpath("(//*[@id='qa-actions']/i)[1]");
    private final By reviewPassportButton = By.xpath("/html/body/div[5]/div/a[5]/span");

    private final By reviewButton = By.xpath("//*[@name= 'review']");
    private final By yesButton = By.cssSelector("#confirmBtn");
    private final By okButton = By.xpath("//* [text()= 'Ok']");

    private final By createPackage = By.xpath("/html/body/div[5]/div/a[2]/span");
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
    private final By hotelCheckInDate = By.xpath("//*[@name='None_GDS_CheckOut']");
    private final By hotelCheckoutDate = By.xpath("//*[@name='None_GDS_CheckOut']");
    private final By hotelAdd = By.xpath("//*[contains(@id,'HotelBrnForm')]//button");

    private final By transportationFirst = By.xpath("(//*[contains(@id,'headingTrans')])[1]");


    private final By transportationSecond = By.xpath("(//*[contains(@id,'headingTrans')])[2]");

    private final By submitToUO = By.xpath("//button[contains(@id,'btnSubmit')]");







    public CreatePackagePage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.faker = new Faker();
        this.testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    public void createPackage(int numberOfMutamers){

        driver.browser().navigateToURL(testData.getTestData("url")+"/bsp/UmrahOperators/home/submittedgroups");
        click(uoTabCount);
        driver.element().clickUsingJavascript(qaAction);
        driver.element().clickUsingJavascript(reviewPassportButton);

        for (int i = 1; i <= numberOfMutamers; i++) { // Loop to click the first 4 elements
            By reviewButtonWithIndex = By.xpath("(//*[@name='review'])[" + i + "]");
            driver.element().clickUsingJavascript(reviewButtonWithIndex);
            click(yesButton);
            click(okButton);

        }


        driver.browser().navigateToURL(testData.getTestData("url")+"/bsp/UmrahOperators/home/submittedgroups");
        click(uoTabCount);
        driver.element().clickUsingJavascript(qaAction);
        click(createPackage);
        type(packageName, "Automation create Program " + faker.lorem().word());
        type(packageDesc, "Automation create Program Desc " + faker.lorem().word());
        click(nextStep);

        click(arrivalDate);

        String datePlus1 = calculateDatePlus(1);

        // Create a dynamic XPath for the date
        By dateElementPlus1 = By.xpath("//td[text()='" + datePlus1 + "']"); // Adjust XPath to match your date picker
        click(dateElementPlus1);
        //click(todayDate);

        click(searchButton);
        click(selectFirstFlight);


        click(departureFlightTab);

        click(departureDate);

        String datePlus7 = calculateDatePlus(7);

        // Create a dynamic XPath for the date
        By dateElementPlus7 = By.xpath("//td[text()='" + datePlus7 + "']"); // Adjust XPath to match your date picker
        click(dateElementPlus7);

        click(searchDepartureButton);
        click(selectFirstDepartureFlight);
        click(nextStep);

        click(cityDDL);
        click(cityMakkah);
        click(addCityButton);
        click(nextStep);

        click(clickMakkahHotel);
        type(brn, "AutoBRN" + faker.number().digits(5));
        click(hotelDDL);
        click(hotelValue);
        type(hotelPrice,faker.number().digits(3));
        click(hotelCheckInDate);
        click(dateElementPlus1);
        click(hotelCheckoutDate);
        click(dateElementPlus7);
        click(hotelAdd);
        click(nextStep);

        click(transportationFirst);

        for (int i = 1; i <= 2; i++) {
            // Dynamically construct the XPath for each element
            if (i == 2) {
                // Click to open the second form
                click(transportationSecond); // Replace with the correct locator for 'transportationSecond'
            }
            By elementLocator = By.xpath("(//*[@name='Brn'])[" + i + "]");
            type(elementLocator,faker.number().digits(3));

            By transCompany = By.xpath("(//*[contains(@id,'select2-company')])[" + i + "]");
            click(transCompany);

            By transCompanySelection = By.xpath("(//*[contains(@id,'select2-company') and text()='Saudi Public Transport Company' ])[" + i + "]");
            click(transCompanySelection);

            By transCompanyPrice = By.xpath("(//*[contains(@id,'Trans_PurchasingPrice')])[" + i + "]");
            type(transCompanyPrice,faker.number().digits(3));


            By transCompanyAdd = By.xpath("(//button[contains(@type,'submit')]/span)[" + i + "]");
            click(transCompanyAdd);

        }

        click(submitToUO);










    }

    public void click(By by){
        driver.element().click(by);
    }
    public CreatePackagePage type(By by, String text){
        driver.element().type(by, text);
        return this;
    }
    public String calculateDatePlus(int days) {
        LocalDate todayPlus = LocalDate.now().plusDays(days);
        return todayPlus.format(DateTimeFormatter.ofPattern("dd")); // Format it as needed (e.g., day only or full date)
    }
}
