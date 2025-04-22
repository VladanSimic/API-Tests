package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.SignIn;


public class SignInTest extends BaseTest{


    SignIn signIn;

    @BeforeMethod(alwaysRun = true)
    public void localSetUp(){

        signIn = new SignIn(driver);
    }

    @Test(groups = "smoke", description = "Check if user is logged in with his Google account")
    @Parameters({"username", "password"})
    public void signInWithGoogleTest(String username, String password){
        signIn.userLogin(username, password)
                .openMenuAndAbout()
                .signInWithGoogle();
        Assert.assertTrue(signIn.isUserSignIn());
    }




}
