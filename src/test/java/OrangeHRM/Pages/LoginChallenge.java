package OrangeHRM.Pages;

import org.openqa.selenium.By;

import static OrangeHRM.Utils.Wait.waitForElement;

public class LoginChallenge {
final By diffDropBtn = By.xpath("//label[contains(text(),'Diff')]/parent::div");
final By selectEasy = By.xpath("//ul[@role='listbox']//li[contains(text(),'Easy')]");
final By viewBtn = By.xpath("//div//h2[contains(text(),'Role')]/parent::div/parent::div/child::div//button");
final By username = By.xpath("//label[contains(text(),'User')]/following::input[@type='text']");
final By password = By.xpath("//label[contains(text(),'Pass')]/following::input[@type='password']");
final By loginBtn = By.xpath("//button[contains(text(),'Login')]");
final By dashboardHeading = By.xpath("//p[contains(text(),'Admin Dash')]");
final By logoutBtn = By.xpath("//button[contains(text(),'Log')]");
final By errorMsg  =  By.xpath("//div[@role='alert']/following::div[contains(@class,'message')]");


public void goToChallenge (){
    waitForElement(diffDropBtn).click();
    waitForElement(selectEasy).click();
    waitForElement(viewBtn).click();
}
public void loginWithValidCreds(String user, String pass) {
        waitForElement(username).sendKeys(user);
        waitForElement(password).sendKeys(pass);
        waitForElement(loginBtn).click();

}
public String verifyLogin(){
    return waitForElement(dashboardHeading).getText();
}
public void logout(){
    waitForElement(logoutBtn).click();
}

public String errMsg(){
    return waitForElement(errorMsg).getText();
}


}
