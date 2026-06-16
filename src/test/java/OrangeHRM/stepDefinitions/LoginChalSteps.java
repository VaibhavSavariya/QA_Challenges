package OrangeHRM.stepDefinitions;


import OrangeHRM.Pages.LoginChallenge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginChalSteps {
    LoginChallenge loginChal = new  LoginChallenge();

    @Given("user navigates to login challenge page")
    public void user_navigates_to_login_challenge_page() {
        // Write code here that turns the phrase above into concrete actions
        loginChal.goToChallenge();
    }
    @When("user enters valid creds")
    public void user_enters_valid_creds() {
        // Write code here that turns the phrase above into concrete actions
        loginChal.loginWithValidCreds("admin","admin123");
    }

    @Then("user should navigates to dashboard page")
    public void user_should_navigates_to_dashboard_page() {
        // Write code here that turns the phrase above into concrete actions
        String actual = loginChal.verifyLogin();
        Assert.assertEquals(actual,"Admin Dashboard");
        loginChal.logout();
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        loginChal.loginWithValidCreds(username,password);
    }
    @Then("user should see error message {string}")
    public void user_should_see_error_message(String errorMessage) {
        // Write code here that turns the phrase above into concrete actions
        String actualMsg = loginChal.errMsg();
        Assert.assertEquals(actualMsg,errorMessage);
    }

}
