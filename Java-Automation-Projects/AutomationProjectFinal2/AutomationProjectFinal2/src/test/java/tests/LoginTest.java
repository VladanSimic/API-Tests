package tests;

import model.LoginUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.Login;
import utils.Utils;

import java.util.List;

public class LoginTest extends BaseTest{

    BasePage basePage;

    Login login;

    @BeforeMethod(alwaysRun = true)
    public void localSetUp(){
        login = new Login(driver);
    }

    @Test(groups = "smoke", description = "User buys product, and goes through the entire process")
    public void buyProduct(){
        login.userLogin("standard_user", "secret_sauce")
        .moveProductToCart()
        .goToCart()
        .checkout()
        .populateCheckoutForm("Vladan", "Simic", 11000);

        Assert.assertTrue(login.isUserBoughtProduct());
    }

    @Test(groups = "smoke", description = "User's logging in", dataProvider = "dpTest", dataProviderClass = Utils.class)
    public void userLoginTest(String username, String password){
        login.userLogin(username, password);
        Assert.assertTrue(login.isUserLoggedIn());

    }

    @Test
    public void loginUserFromJson(){
        List<LoginUserModel> list = Utils.getDataFromJson();
        System.out.println(list.get(0).getUsername());
        System.out.println(list.get(0).getPassword());

    }

    @Test(dataProvider = "getData", dataProviderClass = Utils.class)
    public void userLoginTestFromJson(LoginUserModel loginUserModel){
        login.userLogin(loginUserModel.getUsername(), loginUserModel.getPassword());
    }




}
