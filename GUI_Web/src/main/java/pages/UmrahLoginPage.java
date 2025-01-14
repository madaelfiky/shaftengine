package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class UmrahLoginPage {
    private SHAFT.GUI.WebDriver driver;
    private String url = "https://web-huic.haj.gov.sa/newumrah-test";
    private String title = "Google";
    private By loginButtonMain  = By.xpath("//*[contains(text(),' Login ')]");
    private By usernameField = By.xpath("//*[contains(@placeholder,'User Name')]");
    private By passwordField = By.xpath("//*[contains(@placeholder,'Password')]");
    //private By pilgrimCompanyButtonMain = By.xpath("//*[contains(text(),'Pilgrim Company')]");
    private By mainButtonUserNameAndPassword = By.xpath("(//*[contains(text(),'Username / Password')])[1]");
    private By loginButton = By.xpath("(//*[contains(text(),' Login ')])[2]");
    private By verifyButton = By.xpath("//*[contains(text(),' Verify ')]");

    public UmrahLoginPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

     public UmrahLoginPage navigateToURL(){
        driver.browser().navigateToURL(url + "/home");
        return this;
    }


    public UmrahLoginPage loginByUser(String username, String password){
        click(loginButtonMain);
        click(mainButtonUserNameAndPassword);
        type(usernameField, username);
        type(passwordField, password);
        click( loginButton);
        //waitForElementToBePresent(loginButton);  // Ensure element is present

        String otp = "1234"; // The 4-digit OTP

        for (int i = 1; i <= otp.length(); i++) {
            String xpath = "//*[@formcontrolname='code_" + i + "']"; // Dynamic XPath for OTP input fields
            type(By.xpath(xpath), String.valueOf(otp.charAt(i - 1))); // Enter each digit
        }
        click(verifyButton);
        return this;
    }

    @Step("And I search for '{query}'.")
    public Results searchForQuery(String query){
        driver.element().type(loginButtonMain, query)
                .keyPress(loginButtonMain, Keys.ENTER);
        return new Results(driver);
    }
    public UmrahLoginPage click(By by){
        driver.element().click(by);
        return this;
    }
    public UmrahLoginPage type(By by, String text){
        driver.element().type(by, text);
        return this;
    }
}
