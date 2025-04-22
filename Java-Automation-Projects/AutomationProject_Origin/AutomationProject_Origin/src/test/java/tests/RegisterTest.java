package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Register;

public class RegisterTest extends BaseTest{

    Register register;
    @BeforeMethod (alwaysRun = true)
    public void localSetUp(){
        register = new Register(driver);
    }

    @Test
    public void registerUserTest(){
        Assert.assertTrue(register.registerUser());
    }




}
