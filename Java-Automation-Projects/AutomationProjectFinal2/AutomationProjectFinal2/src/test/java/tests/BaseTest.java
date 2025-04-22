package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getInstance().setDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
