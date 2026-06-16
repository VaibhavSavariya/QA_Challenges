package OrangeHRM.stepDefinitions;

import OrangeHRM.Pages.ProductListing;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductChalSteps {
    ProductListing productListing = new ProductListing();
    private List<String> firstPageProducts;
    private List<String> secondPageProducts;
    long categoryProductCount = 0;
    SoftAssert softAssert = new SoftAssert();
    @Given("user navigates to product listing page")
    public void user_navigates_to_product_listing_page() {
        // Write code here that turns the phrase above into concrete actions
        productListing.goToChallenge();
        firstPageProducts = productListing.getProductNames();
    }
    @When("user clicks page number {int}")
    public void user_clicks_page_number(Integer pageNo) {
        // Write code here that turns the phrase above into concrete actions
        productListing.goToPageNumber(pageNo);
    }
    @Then("page number {int} should be active")
    public void page_number_should_be_active(Integer PageNo) {
        // Write code here that turns the phrase above into concrete actions
        String actualPageNo = productListing.activePageNo();
        softAssert.assertEquals(actualPageNo, PageNo.toString());
    }
    @Then("products displayed should be different from page {int}")
    public void products_displayed_should_be_different_from_page(Integer PageNo) {
        // Write code here that turns the phrase above into concrete actions
        secondPageProducts =  productListing.getProductNames();
        softAssert.assertNotEquals(secondPageProducts, firstPageProducts);
    }
    @When("user clicks Next button")
    public void user_clicks_next_button() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        String actualPageNo= productListing.nextPage();
        softAssert.assertEquals(actualPageNo, "3");
    }

    @When("user clicks Previous button")
    public void user_clicks_previous_button()throws Exception {
        // Write code here that turns the phrase above into concrete actions
        String actualPageNo =  productListing.prevPage();
        softAssert.assertEquals(actualPageNo, "2");
    }
    @Then("total products displayed should be {int}")
    public void total_products_displayed_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Integer total =  productListing.totalProducts();
        softAssert.assertEquals(total, int1);
    }

    @When("user search for {string}")
    public void userSearchFor(String categoryName) {
        // Write code here that turns the phrase above into concrete actions
        categoryProductCount = productListing.verifyCountByCategory(categoryName);

    }

    @Then("total books products displayed should be {int}")
    public void totalBooksProductsDisplayedShouldBe(long total) {
        // Write code here that turns the phrase above into concrete actions
        softAssert.assertEquals(categoryProductCount, total);
    }


    @When("user search for {string} it should displayed product with highest rating and highest price")
    public void userSearchForItShouldDisplayedProductWithHighestRatingAndHighestPrice(String categoryName) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        productListing.displayProductWithHighestRatingAndHighestPrice(categoryName);
        softAssert.assertAll();
    }
}
