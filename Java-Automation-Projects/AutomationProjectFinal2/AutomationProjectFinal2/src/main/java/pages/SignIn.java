package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignIn extends BasePage{

    By usernameField = By.cssSelector("input[name='user-name']");
    By passwordField = By.cssSelector("input[name='password']");
    By loginField = By.cssSelector("input[type='submit']");
    By menuField = By.id("react-burger-menu-btn");

    By aboutField = By.xpath("//a[text()='About']");
    By signInField = By.xpath("//span[text()='Sign in']");
    By signInWithGoogleField = By.xpath("//a[@title='Authenticate with Google']");
    By populateEmailField = By.cssSelector("input[autocomplete='username']");
    By continueField = By.xpath("//span[text()='Даље']");
    By resultField = By.xpath("//span[text()='Нисте успели да се пријавите']");



    public SignIn(WebDriver driver) {
        super(driver);
    }

    public SignIn userLogin(String username, String password) {
        typeIn(usernameField, username);
        typeIn(passwordField, password);
        clickOnElement(loginField);
        return this;
    }

    public SignIn openMenuAndAbout() {
        clickOnElement(menuField);
        clickOnElement(aboutField);
        return this;
    }

    public SignIn signInWithGoogle() {
        clickOnElement(signInField);
        clickOnElement(signInWithGoogleField);
        typeIn(populateEmailField, "simicvladan921@gmail.com");
        clickOnElement(continueField);
        return this;
    }

    public boolean isUserSignIn() {
        String expectedText = "Нисте успели да се пријавите";

        String actual [] = getElement(resultField).getText().split("(^A-Z)");
        String actualText = actual[0];
        if (actualText.equals(expectedText)){
            System.out.println("TEST PASSED FOR YOUR SIGN IN");
            return true;
        } else {
            System.out.println("TEST FAILED FOR YOUR SIGN IN");
        }
        return false;
    }

}
