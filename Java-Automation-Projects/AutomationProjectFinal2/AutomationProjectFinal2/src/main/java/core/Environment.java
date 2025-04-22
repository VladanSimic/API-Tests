package core;

import org.openqa.selenium.WebDriver;

public class Environment {

    private String homeUrl;
    private WebDriver driver;

    public Environment(WebDriver driver) {
        this.driver = driver;
    }

    public void openBrowser(String env){
        if (env.equalsIgnoreCase("DEV")){
            homeUrl = "https://www.saucedemo.com/";
        } else if (env.equalsIgnoreCase("UAT")) {
            homeUrl = "https://the-internet.herokuapp.com/login";
        }
        driver.get(homeUrl);
    }
}
