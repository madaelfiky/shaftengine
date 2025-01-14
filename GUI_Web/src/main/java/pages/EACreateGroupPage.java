package pages;

import com.github.javafaker.Faker;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EACreateGroupPage {
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    private final Faker faker;
    private final By eaContractNavigateSecondPage = By.xpath("//*[@id='ContractsListTable_paginate']/ul/li[3]/a");
    private final By eaContractClickSecondPage = By.xpath("//* [contains(@href ,'23068954')]");
    private final By menuGroups = By.xpath("(//* [@id='qa-menu-groups'])[1]");
    private final By createGroup = By.cssSelector("#qa-create-group");
    private final By groupName = By.cssSelector("#GroupNameEn");
    private final By embassyDDL = By.xpath("(//* [@id='select2-EmbassyId-container'])[1]");
    private final By embassyDDLValue = By.xpath("//*[@id='select2-EmbassyId-results']/li[2]");
    private final By nextButton = By.cssSelector("#qa-next");
    private final By closeButton = By.cssSelector("#saCloseBtn");
    private final By addMutamer = By.xpath("//* [text()='Add Muatamer']");
    private final By passportPicture = By.cssSelector("#PassportPictureUploader");
    private final By vaccinationPictureUploader = By.cssSelector("#VaccinationPictureUploader");
    private final By residencyPictureUploader = By.cssSelector("#ResidencyPictureUploader");
    private final By residencyPictureExpiryDate = By.xpath("//*[@id='dvResidencyExpiryDate']/input");
    private final By nationalityDDL = By.cssSelector("#select2-NationalityId-container");
    private final By nationalityValue = By.cssSelector("#select2-NationalityId-results >li:nth-of-type(49)");
    private final By firstNameEn = By.cssSelector("#FirstNameEn");
    private final By secondNameEn = By.cssSelector("#SecondNameEn");
    private final By thirdNameEn = By.cssSelector("#ThirdNameEn");
    private final By familyNameEn = By.cssSelector("#FamilyNameEn");
    private final By firstNameAr = By.cssSelector("#FirstNameAr");
    private final By familyNameAr = By.cssSelector("#FamilyNameAr");

    private final By mobileDDL = By.cssSelector("#select2-MobileCountryKey-container");
    private final By mobileValue = By.cssSelector("#select2-MobileCountryKey-results >li:nth-of-type(2)");
    private final By mobiletext = By.cssSelector("#MobileNo");
    private final By email = By.cssSelector("#Email");
    //private final By genderDDL = By.cssSelector("#select2-Gender-container");
    //private final By genderValue = By.cssSelector("#select2-Gender-results >li:nth-of-type(2)");
    private final By martialStatusDDL = By.cssSelector("#select2-MartialStatus-container");
    private final By martialStatusValue = By.cssSelector("#select2-MartialStatus-results >li:nth-of-type(2)");
    private final By job = By.cssSelector("#Job");
    private final By countryOfBirthDDL = By.cssSelector("#select2-BirthCountry-container");
    private final By countryOfBirthValue = By.cssSelector("#select2-BirthCountry-results >li:nth-of-type(2)");
    private final By birthCity = By.cssSelector("#BirthCity");
    private final By issueCity = By.cssSelector("#IssueCity");
    private final By passportTypeDDL = By.cssSelector("#select2-PassportType-container");
    private final By passportTypeValue = By.cssSelector("#select2-PassportType-results >li:nth-of-type(2)");
    private final By passportNumber = By.cssSelector("#PassportNumber");
    private final By captchCode = By.cssSelector("#CaptchaCode");
    private final By saveButton = By.cssSelector("#qa-add-mutamer-save");

    private final By qaAction = By.xpath("(//*[@id='qa-actions']/i)[1]");
    private final By sendToUmrahButton = By.xpath("/html/body/div[5]/div/a[4]/span");
    private final By confirmButton = By.cssSelector("#confirmBtn");
    private final By nextStepButton = By.xpath("//* [contains(text() ,'Next Step')]");








    public EACreateGroupPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.faker = new Faker();
        this.testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void createGroup(int numberOfMutamers){
        click(eaContractNavigateSecondPage);
        click(eaContractClickSecondPage);
        click(menuGroups);
        click(createGroup);
        String randomGroupName = faker.lorem().word();
        String randomGroupNumber = faker.number().digits(5);
        type(groupName, "Automation Shaft Engine " + randomGroupName + randomGroupNumber);
        click(embassyDDL);
        click(embassyDDLValue);
        click(nextButton);
        click(closeButton);

        for (int i = 1; i <= numberOfMutamers; i++) {

           String randomWord = faker.lorem().word();
           String randomPassportNumber = faker.number().digits(5);
           String filePath = testData.getTestData("passportPicture");
           String filePath2 = testData.getTestData("ftmPicture");

           click(addMutamer);
           type(passportPicture, filePath);
           type(vaccinationPictureUploader, filePath2);
           //type(residencyPictureUploader,filePath2);


            click(mobileDDL);
            click(mobileValue);

            type(mobiletext, "1119892921");
            type(email, "test@test.com");



            click(martialStatusDDL);
            click(martialStatusValue);

            type(job, "swq");

            click(countryOfBirthDDL);
            click(countryOfBirthValue);
            type(birthCity, randomWord);

            type(issueCity, randomWord);

            click(passportTypeDDL);
            click(passportTypeValue);
          /*try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            boolean isDisplayed = false;
            int attempts = 0;
            while (!isDisplayed && attempts < 10) { // Retry up to 10 times
                isDisplayed = driver.element().isElementDisplayed(residencyPictureExpiryDate);
                if (!isDisplayed) {
                    try {
                        Thread.sleep(1000); // Wait 1 second before retrying
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                attempts++;
            }

            if (isDisplayed) {
                System.out.println("Element is now visible!");
                click(nationalityDDL);
                click(nationalityValue);
            } else {
                System.out.println("Element was not visible after waiting.");
            }

            driver.element().waitToBeInvisible(residencyPictureUploader);


            type(firstNameAr, "احمدان");
            type(familyNameAr, "احمد");
            type(firstNameEn, "First"+randomWord);
            type(secondNameEn, "Second"+randomWord);
            type(thirdNameEn, "Third"+randomWord);
            type(familyNameEn, "Family"+randomWord);

            driver.element().setValueUsingJavaScript(passportNumber,"N"+randomPassportNumber+i);

            type(captchCode, "1");
            click(saveButton);
            click(closeButton);
                    }

        click(nextStepButton);
        driver.element().clickUsingJavascript(qaAction);
        click(sendToUmrahButton);

        click(confirmButton);

        //cy.xpath("(//*[@id='qa-actions']/i)[1]").click();

        //cy.xpath("/html/body/div[5]/div/a[4]/span").click();


        //cy.get("#confirmBtn").click();


    }

    public void click(By by){
        driver.element().click(by);
    }
    public EACreateGroupPage type(By by, String text){
        driver.element().type(by, text);
        return this;
    }
}
