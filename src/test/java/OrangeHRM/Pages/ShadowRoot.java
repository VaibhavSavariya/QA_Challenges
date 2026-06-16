package OrangeHRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import static OrangeHRM.Utils.Wait.waitForElement;
import static OrangeHRM.Utils.Wait.waitForShadowElement;

public class ShadowRoot {
    final By diffDropBtn = By.xpath("//label[contains(text(),'Diff')]/parent::div");
    final By selectMedium = By.xpath("//ul[@role='listbox']//li[contains(text(),'Medium')]");
    final By viewBtn = By.xpath("//div//h2[contains(text(),'Shadow')]/parent::div/parent::div/child::div//button");
    final By shadowRoot = By.xpath("//div[@id='shadow-root-section']");
    final By username = By.cssSelector("#shadow-username");
    final By password = By.cssSelector("#shadow-password");
    final By loginBtn = By.cssSelector("#shadow-login");


    public void goToChallenge (){
        waitForElement(diffDropBtn).click();
        waitForElement(selectMedium).click();
        waitForElement(viewBtn).click();
    }
    public void loginInsideShadowDom(String user,String pass)throws Exception{
        SearchContext shadowHost = waitForElement(shadowRoot).getShadowRoot();
        waitForShadowElement(username,shadowHost).sendKeys(user);
        waitForShadowElement(password,shadowHost).sendKeys(pass);
        waitForShadowElement(loginBtn,shadowHost).click();
        Thread.sleep(3000);
    }
}
