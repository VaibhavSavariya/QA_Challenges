package OrangeHRM.Utils;

import OrangeHRM.DriverManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static OrangeHRM.Utils.ScrollUtil.scrollToElement;

public class Wait {
    public static WebElement waitForElement(By locator) {
        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(30));

        WebElement element =  wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator));

        scrollToElement(element);
        return element;
    }
    public static WebElement waitForShadowElement(By locator, SearchContext shadowRoot) {
        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(30));

        WebElement element =  wait.until(
                driver->shadowRoot.findElement(locator));

        scrollToElement(element);
        return element;
    }
    public static WebElement waitForElement(By locator, String condition) {
        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(30));

        switch (condition) {
            case "visibility":
                WebElement visibleElement =  wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator));

                scrollToElement(visibleElement);
        return visibleElement;
            case "presence":
                WebElement presenceElement =  wait.until(
                        ExpectedConditions
                                .presenceOfElementLocated(locator));

                scrollToElement(presenceElement);
                return presenceElement;
            case "clickable":
                WebElement clickableElement = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));
                scrollToElement(clickableElement);
                return clickableElement;
            default:
                throw new IllegalArgumentException(
                        "Unknown wait condition: '" + condition + "'. " +
                                "Valid values: 'visibility', 'presence', 'clickable'");
        }

    }
    public static List<WebElement> waitForElements(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(30));

        return wait.until(
                ExpectedConditions
                        .presenceOfAllElementsLocatedBy(locator));
    }
}
