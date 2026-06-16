package OrangeHRM.Pages;

import OrangeHRM.DriverManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

import static OrangeHRM.Utils.Wait.waitForElement;
import static OrangeHRM.Utils.Wait.waitForElements;

public class ProductFilter {
    final By diffDropBtn = By.xpath("//label[contains(text(),'Diff')]/parent::div");
    final By selectMedium = By.xpath("//ul[@role='listbox']//li[contains(text(),'Medium')]");
    final By viewBtn = By.xpath("//div//h2[contains(text(),'Filtering')]/parent::div/parent::div/child::div//button");
    final By categoryDropDown = By.xpath("//label[@id='category']/parent::div");
    final By categoryOptions = By.xpath("//ul[@role='listbox']//li");
    final By sliderRoot = By.xpath("//span[contains(@class,'MuiSlider-root')]");
    final By minThumb = By.xpath("//span[@data-index='0'][contains(@class,'MuiSlider-thumb')]");
    final By maxThumb = By.xpath("//span[@data-index='1'][contains(@class,'MuiSlider-thumb')]");
    final By ratingsEle = By.xpath("//span[contains(@class,'MuiRating-root')]");
    final By inStockCheck = By.xpath("//input[@type='checkbox']");
    final By inStockCheckParent = By.xpath("//input[@type='checkbox']/parent::span");
    final By resetBtn = By.xpath("//button[contains(text(),'Reset')]");
    final By allProducts = By.xpath("//div[contains(@class,'shadow-sm')]");
    final By stockStatus = By.xpath("//span[contains(@class,'MuiTypography-caption')]");


    public void goToChallenge (){
        waitForElement(diffDropBtn).click();
        waitForElement(selectMedium).click();
        waitForElement(viewBtn).click();
    }

    public void categoryFilter(String categoryName){
        waitForElement(categoryDropDown).click();
        List<WebElement> options  = waitForElements(categoryOptions);
        for (WebElement option : options) {
            if(option.getText().equals(categoryName)){
                option.click();
                break;
            }
        }
    }

    public boolean categoryOptions(String categoryName){
        List<WebElement> products = waitForElements(allProducts);
        boolean allMatch = true;
        for (WebElement product : products) {
            try {
                String fullText = product
                        .findElement(By.xpath(".//p[contains(@class,'css-vouxdn')]"))
                        .getText();

                // ✅ Split and extract category
                String[] parts = fullText.split("•");
                if (parts.length < 3) continue;

                String productCat = parts[0].trim();   // "Electronics"
                String price      = parts[1].replace("₹", "").trim(); // "251"
                String rating     = parts[2].replace("☆", "").trim(); // "4"

                String title = product
                        .findElement(By.xpath(".//p[contains(@class,'font-medium')]"))
                        .getText()
                        .trim();

                // ✅ Check if product belongs to selected category
                if (productCat.equalsIgnoreCase(categoryName)) {
                    System.out.println("✅ MATCH    | " + title
                            + " | Category: " + productCat
                            + " | Price: ₹" + price
                            + " | Rating: " + rating);
                } else {
                    System.out.println("❌ MISMATCH | " + title
                            + " | Expected: " + categoryName
                            + " | Found: " + productCat);
                    allMatch = false;
                }

            } catch (NoSuchElementException e) {
                System.out.println("Skipping malformed card: " + e.getMessage());
            }
        }

        return allMatch;
    }

    public void selectPriceRange(int min, int max)throws Exception{
        System.out.println("Console "+min +" " + max);
        WebElement track = waitForElement(sliderRoot);
        WebElement minThumbEle = waitForElement(minThumb);
        WebElement maxThumbEle = waitForElement(maxThumb);
        WebDriver driver = DriverManager.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

        int sliderMin = 0;
        int sliderMax = 1000;
        int trackWidth = track.getSize().getWidth();

        int minOffSet = (int)(trackWidth*((double)(min-sliderMin)/(sliderMax-sliderMin)));
        int maxOffSet = (int)(trackWidth*((double)(max-sliderMin)/(sliderMax-sliderMin)));

        action.clickAndHold(minThumbEle).moveByOffset(minOffSet,0).release().perform();

        Thread.sleep(500);
        // Move max thumb — offset is measured from its CURRENT position, so subtract
        int currentMaxOffset = (int)(trackWidth * 1.0); // thumb starts at max end
        action.clickAndHold(maxThumbEle)
                .moveByOffset(maxOffSet - currentMaxOffset, 0)
                .release()
                .perform();

        Thread.sleep(500);
    }

    public boolean priceRageOptions(int min, int max){
        List<WebElement> products = waitForElements(allProducts);
        boolean allMatch = true;
        for (WebElement product : products) {
            try {
                String fullText = product
                        .findElement(By.xpath(".//p[contains(@class,'css-vouxdn')]"))
                        .getText();

                // ✅ Split and extract category
                String[] parts = fullText.split("•");
                if (parts.length < 3) continue;

                String productCat = parts[0].trim();   // "Electronics"
                int price      = Integer.parseInt(parts[1].replace("₹", "").trim()); // "251"
                String rating     = parts[2].replace("☆", "").trim(); // "4"

                String title = product
                        .findElement(By.xpath(".//p[contains(@class,'font-medium')]"))
                        .getText()
                        .trim();

                // ✅ Check if product belongs to selected category
                if (price>=min && price<=max) {
                    System.out.println("✅ MATCH    | " + title
                            + " | Category: " + productCat
                            + " | Price: ₹" + price
                            + " | Rating: " + rating);
                } else {
                    System.out.println("❌ MISMATCH | " + title
                            );
                    allMatch = false;
                }

            } catch (NoSuchElementException e) {
                System.out.println("Skipping malformed card: " + e.getMessage());
            }
        }

        return allMatch;
    }


    public void selectStar(Integer ratingStar){
        List<WebElement> ratings = waitForElements(ratingsEle);
        for (WebElement rating :  ratings) {
            WebElement star = rating.findElement(By.xpath(".//input[@value="+ratingStar+"]/preceding-sibling::label//span[text()='"+ratingStar+" Stars']/parent::label"));
            star.click();
        }
    }

    public boolean ratingOptions(Integer  ratingStar){
        List<WebElement> products = waitForElements(allProducts);
        boolean allMatch = true;
        for (WebElement product : products) {
            try {
                String fullText = product
                        .findElement(By.xpath(".//p[contains(@class,'css-vouxdn')]"))
                        .getText();

                // ✅ Split and extract category
                String[] parts = fullText.split("•");
                if (parts.length < 3) continue;

                String productCat = parts[0].trim();   // "Electronics"
                String price      = parts[1].replace("₹", "").trim(); // "251"
                String ratingRaw  = parts[2].replaceAll("[^0-9]", "").trim();

                String title = product
                        .findElement(By.xpath(".//p[contains(@class,'font-medium')]"))
                        .getText()
                        .trim();

                // ✅ Check if product belongs to selected category
                if (Integer.parseInt(ratingRaw)>=ratingStar) {
                    System.out.println("✅ MATCH    | " + title
                            + " | Category: " + productCat
                            + " | Price: ₹" + price
                            + " | Rating: " + ratingRaw);
                } else {
                    System.out.println("❌ MISMATCH | " + title
                            + " | Expected: " + ratingStar
                            + " | Found: " + productCat);
                    allMatch = false;
                }

            } catch (NoSuchElementException e) {
                System.out.println("Skipping malformed card: " + e.getMessage());
            }
        }

        return allMatch;
    }

    public void checkInStockBox(){
        WebElement checkbox = waitForElement(inStockCheck,"presence");
        if (!checkbox.isSelected()) {
            // Not checked → click to check it
            checkbox.click();
            System.out.println("Checkbox: checked");
        } else {
            System.out.println("Checkbox: already checked, skipping");
        }

    }

    public boolean inStockOption(String status){
        List<WebElement> products = waitForElements(allProducts);
        boolean allMatch = true;
        for (WebElement product : products) {
            try {

                String title = product
                        .findElement(By.xpath(".//p[contains(@class,'font-medium')]"))
                        .getText()
                        .trim();
                String productStatus  = product.findElement(stockStatus).getText();

                // ✅ Check if product belongs to selected category
                if (productStatus.equals(status) ) {
                    System.out.println("✅ MATCH    | " + title
                            + " | Status: " + productStatus
                            );
                } else {
                    System.out.println("❌ MISMATCH | " + title
                    );
                    allMatch = false;
                }

            } catch (NoSuchElementException e) {
                System.out.println("Skipping malformed card: " + e.getMessage());
            }
        }

        return allMatch;
    }


    public boolean combineOptions(){
        List<WebElement> products = waitForElements(allProducts);
        boolean allMatch = true;
        for (WebElement product : products) {
            try {

                String fullText = product
                        .findElement(By.xpath(".//p[contains(@class,'css-vouxdn')]"))
                        .getText();

                // ✅ Split and extract category
                String[] parts = fullText.split("•");
                if (parts.length < 3) continue;

                String productCat = parts[0].trim();   // "Electronics"
                int price      = Integer.parseInt(parts[1].replace("₹", "").trim()); // "251"
                String rating     = parts[2].replace("☆", "").trim(); // "4"


                String title = product
                        .findElement(By.xpath(".//p[contains(@class,'font-medium')]"))
                        .getText()
                        .trim();
                String productStatus  = product.findElement(stockStatus).getText();

                // ✅ Check if product belongs to selected category
                if (productCat.equals("Sports") && productStatus.equals("In Stock") && (price>=200 && price<=500)) {
                    System.out.println("✅ MATCH    | " + title
                            + " | Status: " + productStatus
                            + " | Price: " + price
                    );
                } else {
                    System.out.println("❌ MISMATCH | " + title
                    );
                    allMatch = false;
                }

            } catch (NoSuchElementException e) {
                System.out.println("Skipping malformed card: " + e.getMessage());
            }
        }

        return allMatch;
    }

    public void resetFilter(){
        List<WebElement> products = waitForElements(allProducts);
        System.out.println("After filter products: " + products.size());
        waitForElement(resetBtn).click();
    }
    public void allProducts(){
        List<WebElement> products = waitForElements(allProducts);
        System.out.println("All products: " + products.size());
    }
}
