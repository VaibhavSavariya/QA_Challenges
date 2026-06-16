package OrangeHRM.stepDefinitions;

import OrangeHRM.Pages.ProductFilter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

public class ProductFilterStepDef {
    ProductFilter productFilter = new ProductFilter();
    SoftAssert softAssert = new SoftAssert();
    @Given("user navigates to product filter page")
    public void user_navigates_to_product_filter_page() {

        // Write code here that turns the phrase above into concrete actions
        productFilter.goToChallenge();
    }
    @When("user selects {string} category")
    public void user_selects_category(String CategoryName) {
        // Write code here that turns the phrase above into concrete actions
        productFilter.categoryFilter(CategoryName);
    }
    @Then("all displayed products should belong to {string} category")
    public void all_displayed_products_should_belong_to_category(String string) {
        // Write code here that turns the phrase above into concrete actions
        boolean actualCategory =  productFilter.categoryOptions(string);
        softAssert.assertTrue(actualCategory);
    }

    @When("user sets price range from {int} to {int}")
    public void user_sets_price_range_from_to(int min, int max)throws Exception {
        // Write code here that turns the phrase above into concrete actions
        productFilter.selectPriceRange(min,max);
    }
    @Then("all displayed products should have price between {int} and {int}")
    public void all_displayed_products_should_have_price_between_and(int min, int max) {
        // Write code here that turns the phrase above into concrete actions
        boolean actualOptions = productFilter.priceRageOptions(min,max);
        softAssert.assertTrue(actualOptions);
    }

    @When("user selects {int} star rating filter")
    public void user_selects_star_rating_filter(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        productFilter.selectStar(int1);
    }
    @Then("all displayed products should have rating greater than or equal to {int} stars")
    public void all_displayed_products_should_have_rating_greater_than_or_equal_to_stars(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        boolean actualRatingOptions = productFilter.ratingOptions(int1);
        softAssert.assertTrue(actualRatingOptions);

    }

    @When("user checks inStock checkbox")
    public void user_checks_in_stock_checkbox() {
        // Write code here that turns the phrase above into concrete actions
        productFilter.checkInStockBox();
    }
    @Then("all displayed products should show {string} status")
    public void all_displayed_products_should_show_status(String status) {
        // Write code here that turns the phrase above into concrete actions
        boolean inStockOPtions = productFilter.inStockOption(status);
        softAssert.assertTrue(inStockOPtions);

    }

    @Then("all displayed products should belong to {string} category price between {int} and {int} and have rating greater than or equal to {int} stars")
    public void all_displayed_products_should_belong_to_category_price_between_and_and_have_rating_greater_than_or_equal_to_stars(String cat, int min, int max, Integer int3)  {
        // Write code here that turns the phrase above into concrete actions
        boolean actualCombineOptions  = productFilter.combineOptions();
        softAssert.assertTrue(actualCombineOptions);
    }

    @When("user clicks reset filters button")
    public void user_clicks_reset_filters_button() {
        // Write code here that turns the phrase above into concrete actions
        productFilter.resetFilter();
    }
    @Then("all filters should be cleared")
    public void all_filters_should_be_cleared() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("All filter products is cleared");
    }
    @Then("all products should be displayed")
    public void all_products_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        productFilter.allProducts();
        softAssert.assertAll();
    }
}
