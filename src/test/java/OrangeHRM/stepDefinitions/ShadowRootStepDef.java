package OrangeHRM.stepDefinitions;

import OrangeHRM.Pages.ShadowRoot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShadowRootStepDef {
    ShadowRoot  shadowRoot = new ShadowRoot();

    @Given("user navigates to Shadow Login challenge page")
    public void user_navigates_to_shadow_login_challenge_page() {
        // Write code here that turns the phrase above into concrete actions
        shadowRoot.goToChallenge();
    }


    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        shadowRoot.loginInsideShadowDom(username,password);
    }
    @Then("user should see to dashboard page")
    public void user_should_see_to_dashboard_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("user should see to dashboard page");
    }
}
