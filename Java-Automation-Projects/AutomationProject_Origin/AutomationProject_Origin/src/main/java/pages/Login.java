package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage{

    private By myAccountNavBar = By.cssSelector("a[title='My Account']");
    private By navbarLoginOption = By.xpath("//a[text()='Login']");
    private By loginEmailField = By.cssSelector("#input-email");
    private By loginPasswordField = By.cssSelector("#input-password");
    private By loginButton = By.cssSelector("input[type='submit']");
    private By labelMyAccount = By.xpath("//h2[text()='My Account']");

    public String expectedLabelText = "My Account";

    public Login(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage(){
        clickOnElement(myAccountNavBar);
        clickOnElement(navbarLoginOption);
    }

    public boolean loginUser(String username, String password){
        goToLoginPage();
        typeIn(loginEmailField, username);
        typeIn(loginPasswordField, password);
        clickOnElement(loginButton);
        return readElementsContent(labelMyAccount).equals(expectedLabelText);
    }
}
