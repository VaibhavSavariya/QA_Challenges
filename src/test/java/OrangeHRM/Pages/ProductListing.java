package OrangeHRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static OrangeHRM.Utils.Wait.waitForElement;
import static OrangeHRM.Utils.Wait.waitForElements;

public class ProductListing {
    final By diffDropBtn = By.xpath("//label[contains(text(),'Diff')]/parent::div");
    final By selectMedium = By.xpath("//ul[@role='listbox']//li[contains(text(),'Medium')]");
    final By viewBtn = By.xpath("//div//h2[contains(text(),'Listing')]/parent::div/parent::div/child::div//button");
    final By pagesNo = By.xpath("//nav[contains(@aria-label,'pagination')]//ul//li");
    final By nextBtn = By.xpath("//button[contains(text(),'Next')]");
    final By prevBtn = By.xpath("//button[contains(text(),'Prev')]");
    final By products = By.xpath("//div[contains(@class,'grid-cols-1')]//div//div");
    final By productsTitles = By.xpath("//h6[contains(@class,'font-semi')]");
    final By productsCategory = By.xpath("//div[contains(@class,'grid-cols-1')]//div//div//p");
    final By productsPrice = By.xpath("//h6[contains(@class,'text-green-600')]");
    final By productsRating = By.xpath("//div[contains(@class,'grid-cols-1')]//div//div//span[@role='img']");
    final By activePageNoEle = By.xpath("//button[@aria-current='page']");


    WebElement activePageNo;
    WebElement firstPageProEle;

    public void goToChallenge (){
        waitForElement(diffDropBtn).click();
        waitForElement(selectMedium).click();
        waitForElement(viewBtn).click();
    }

    public void goToPageNumber(Integer pageNo) {
        List<WebElement> pageNumbers = waitForElements(pagesNo);
        for (WebElement pageNumber : pageNumbers) {
            if(String.valueOf(pageNo).equals(pageNumber.getText())){
               activePageNo = pageNumber;
               activePageNo.click();
                break;
            }
        }
    }

    public String activePageNo(){
       return activePageNo.getText();
    }


    public List<String> getProductNames() {
        return waitForElements(productsTitles).stream().map(WebElement::getText).toList();
    }

    public String nextPage() throws Exception{
        waitForElement(nextBtn).click();
        Thread.sleep(2000);
        return waitForElement(activePageNoEle).getText();
    }
    public String prevPage()throws Exception{
        waitForElement(prevBtn).click();
        Thread.sleep(2000);
        return waitForElement(activePageNoEle).getText();
    }
    public int totalProducts(){
        List<WebElement> totalProducts = waitForElements(productsTitles);
        return totalProducts.size();
    }

    public long verifyCountByCategory(String cat){
        WebElement nextBtnEle = waitForElement(nextBtn);
        long finalCount=0;
        while(true) {
            long count = waitForElements(productsCategory)
                    .stream()
                    .map(WebElement::getText)
                    .map(text -> text.split(":")[1].trim())
                    .filter(category ->
                            category.equalsIgnoreCase(cat))
                    .count();
            finalCount+=count;
            if(!nextBtnEle.isEnabled()) {
                break;
            }
            nextBtnEle.click();
        }
        return finalCount;
    }

    public void displayProductWithHighestRatingAndHighestPrice(String category) {
        double highestPrice = Double.MIN_VALUE;
        String highestPriceText = "";
        int highestRating = 0;
        List<String> highestRatingProducts = new ArrayList<>();

        while (true) {
            // ✅ Fetch all product cards as a grouped unit
            List<WebElement> productCards = waitForElements(products);

            for (WebElement card : productCards) {
                try {
                    // ✅ Each card has exactly ONE of each — findElement() is correct here
                    String productCategory = card.findElement(By.xpath(".//p")).getText().split(":")[1].trim();

                    if (!productCategory.equalsIgnoreCase(category)) continue;

                    String priceText = card.findElement(
                            By.xpath(".//h6[contains(@class,'text-green-600')]")
                    ).getText().replace("$", "").trim();

                    String ariaLabel = card.findElement(
                            By.xpath(".//span[@role='img']")
                    ).getAttribute("aria-label");

                    String title = card.findElement(
                            By.xpath(".//h6[contains(@class,'font-semi')]")
                    ).getText().trim();

                    // ✅ Now process the clean values
                    double price = Double.parseDouble(priceText);
                    int rating = Integer.parseInt(ariaLabel.split(" ")[0]);

                    if (price > highestPrice) {
                        highestPrice = price;
                        highestPriceText = priceText;
                    }
                    if (rating > highestRating) {
                        highestRating = rating;
                    }
                    if (rating == 5) {
                        highestRatingProducts.add(title);
                    }

                } catch (NoSuchElementException e) {
                    // ✅ Gracefully skip cards missing expected elements
                    System.out.println("Skipping incomplete card: " + e.getMessage());
                }
            }

            // Re-fetch next button to avoid stale reference
            WebElement nextBtnEle = waitForElement(nextBtn);
            if (!nextBtnEle.isEnabled()) break;

            nextBtnEle.click();
        }

        System.out.println("Category filtered: " + category);
        System.out.println("Highest price: " + highestPriceText);
        System.out.println("Highest rating: " + highestRating);
        System.out.println("5-star products count: " + highestRatingProducts.size());
        System.out.println("5-star products: " + highestRatingProducts);
    }
}
