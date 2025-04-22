package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Login extends BasePage {

    By usernameField = By.cssSelector("input[name='user-name']");
    By passwordField = By.cssSelector("input[name='password']");
    By loginField = By.cssSelector("input[type='submit']");
    By actualText = By.xpath("//span[contains (text(), 'Products')]");

    By addToCartField = By.cssSelector("button[name='add-to-cart-sauce-labs-backpack']");
    By goToCartField = By.cssSelector("a[class='shopping_cart_link']");
    By checkoutField = By.id("checkout");

    By firstnameField = By.xpath("//div[@class='form_group']//input[@name='firstName']");
    By lastnameField = By.id("last-name");
    By zipcodeField = By.cssSelector("input[name='postalCode']");
    By continueField = By.id("continue");
    By finishButton = By.cssSelector("button[name='finish']");


    By actualText1 = By.xpath("//h2[contains(text(), 'THANK YOU FOR YOUR ORDER')]");

    public Login(WebDriver driver) {
        super(driver);
    }

    public Login userLogin(String username, String password) {
        typeIn(usernameField, username);
        typeIn(passwordField, password);
        clickOnElement(loginField);
        return this;
    }

    public boolean isUserLoggedIn() {
        String expectedText = "PRODUCTS";
        String actualTextOnPage = getElement(actualText).getText();
        if (expectedText.equals(actualTextOnPage)) {
            System.out.println("User has been successfully logged in");
            return true;
        } else {
            System.out.println("User has not been successfully logged in");
        }
        return false;
    }

    public Login moveProductToCart() {
        clickOnElement(addToCartField);
        return this;
    }

    public Login goToCart() {
        clickOnElement(goToCartField);
        return this;
    }

    public Login checkout() {
        clickOnElement(checkoutField);
        return this;
    }

    public Login populateCheckoutForm(String firstname, String lastname, int zipCode) {
        typeIn(firstnameField, firstname);
        typeIn(lastnameField, lastname);
        typeIn(zipcodeField, String.valueOf(zipCode));
        moveToElementAndClick(continueField);
        clickOnElement(finishButton);
        return this;
    }

    public boolean isUserBoughtProduct() {
        String expectedText1 = "THANK YOU FOR YOUR ORDER";
        String actualTextOnPage1 = getElement(actualText1).getText();
        if (expectedText1.equals(actualTextOnPage1)) {
            System.out.println("User has been successfully bought a product!");
            return true;
        } else {
            System.out.println("User has NOT been successfully bought a product!");
        }
        return false;
    }

    public void moveToElementAndClick(By locator){
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();
    }


}
