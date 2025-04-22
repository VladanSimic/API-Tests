package tests;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    static WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    @Parameters
    public void setUp(String browser){
        DriverManager.setDriver(browser);
        //driver.get("https://the-internet.herokuapp.com/login");
        driver.get("https://demo.opencart.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
//done