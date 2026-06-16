package OrangeHRM.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
//        dryRun = true, // any of the functions are missed in the Step Definition for any Step in Feature File, it will give us the message.
        features = "src/test/resources/FeatureFiles/LoginChal.feature",
        glue = {"OrangeHRM"},
        plugin = {
                "pretty",
                "html:test-output/cucumber.html",
                "json:test-output/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true

)


public class LoginChal extends AbstractTestNGCucumberTests {
}
