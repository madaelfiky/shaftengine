package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Home {
    private SHAFT.GUI.WebDriver driver;
    private String url = "https://www.google.com/";
    private String title = "Google";
    private By searchBox = By.name("q");

    public Home(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    @Step("When I navigate to the Home page.")
    public Home navigate(){
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Then the browser title should be 'Google'.")
    public Home verifyBrowserTitleIsCorrect(){
        driver.verifyThat().browser().title().isEqualTo(title).perform();
        return this;
    }

    @Step("And I search for '{query}'.")
    public Results searchForQuery(String query){
        driver.element().type(searchBox, query)
                .keyPress(searchBox, Keys.ENTER);
        return new Results(driver);
    }

    public void selectRandomFromDDL(By dropdownLocator) {

        WebElement dropdownElement = driver.getDriver().findElement(dropdownLocator);
        List<WebElement> options = dropdownElement.findElements(By.xpath("./option"));
        if (options.isEmpty()) {
            options = dropdownElement.findElements(By.xpath("./li"));
        }
        if (options.size() > 1) {
            // Generate a random index, skipping the first option if it's a placeholder
            int randomIndex = new Random().nextInt(options.size() - 1) + 1;

            // Get the text of the random option
            String randomOptionText = options.get(randomIndex).getText();

            // Select the option by visible text
            driver.element().select(dropdownLocator, randomOptionText);

            // Log the selected option
            System.out.println("Randomly selected option: " + randomOptionText);
        } else {
            System.out.println("No selectable options available.");
        }
    }
}
