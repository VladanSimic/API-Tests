package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register extends BasePage{

    private By myAccountNavBar = By.cssSelector("a[title='My Account']");
    private By navbarRegisterOption = By.xpath("//a[text()='Register']");
    private By firstNameField = By.cssSelector("#input-firstname");
    private By lastNameField = By.cssSelector("#input-lastname");
    private By emailField = By.cssSelector("#input-email");
    private By telephoneField = By.cssSelector("#input-telephone");
    private By passwordField = By.cssSelector("#input-password");
    private By confirmPasswordField = By.cssSelector("#input-confirm");
    private By privacyPolicyCheckbox = By.cssSelector("input[name='agree']");
    private By continueButton = By.cssSelector("input[type='submit']");
    private By successMessage = By.xpath("//div[@id='content']/h1");

    public String userEmail = faker.internet().emailAddress();
    public String userPassword = faker.internet().password(7,12,true,true,true);
    private String expectedSuspectText = "Your Account Has Been Created";

    public Register(WebDriver driver) {
        super(driver);
    }


    public void goToRegisterPage(){
        clickOnElement(myAccountNavBar);
        clickOnElement(navbarRegisterOption);
    }

    public boolean registerUser(){
        goToRegisterPage();
        typeIn(firstNameField, faker.name().firstName());
        typeIn(lastNameField, faker.name().lastName());
        typeIn(emailField, userEmail);
        typeIn(telephoneField, faker.phoneNumber().phoneNumber());
        typeIn(passwordField, userPassword);
        typeIn(confirmPasswordField, userPassword);
        clickOnElement(privacyPolicyCheckbox);
        clickOnElement(continueButton);

        return readElementsContent(successMessage).equals(expectedSuspectText);

    }




}
