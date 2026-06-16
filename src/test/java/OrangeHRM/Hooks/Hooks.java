package OrangeHRM.Hooks;

import OrangeHRM.DriverManager.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    @Before
    public void setup() {
        System.out.println("starting......");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                "false"));

        if(headless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }



        DriverManager.setDriver(
                new ChromeDriver(options));

        DriverManager.getDriver()
                .manage()
                .window()
                .maximize();

        DriverManager.getDriver().get(
                "https://www.cnarios.com/challenges");
        System.out.println("Browser Opened....");
    }

    @After(order = 1)
    public void captureScreenshot(Scenario scenario) {

        if(scenario.isFailed()) {
            System.out.println(
                    "FAILED SCENARIO: "
                            + scenario.getName());

            System.out.println(
                    "CURRENT URL: "
                            + DriverManager.getDriver()
                            .getCurrentUrl());

            byte[] screenshot =
                    ((TakesScreenshot)
                            DriverManager.getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    scenario.getName());
        }
    }

    @After(order = 0)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {

            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
