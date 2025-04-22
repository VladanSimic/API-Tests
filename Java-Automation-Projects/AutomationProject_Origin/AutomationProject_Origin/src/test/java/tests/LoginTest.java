package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.Login;

public class LoginTest extends BaseTest {

    Login login;
    @BeforeMethod (alwaysRun = true)
    public void localSetUp(){
        login = new Login(driver);


    }

    @Test (groups = "basic")
    @Parameters({"emailUsername", "password"})
    public void loginTest(String emailUsername, String password){
        Assert.assertTrue(login.loginUser(emailUsername, password));
    }


}
//done