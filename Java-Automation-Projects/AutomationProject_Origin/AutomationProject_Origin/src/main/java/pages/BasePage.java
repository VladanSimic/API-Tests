package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {


    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker = new Faker();

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // CUSTOM TJ. HELPER METODE
    protected WebElement getElement(By locator){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }

    protected void typeIn(By locator, String text){
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickOnElement(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String readElementsContent(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }


}
//done