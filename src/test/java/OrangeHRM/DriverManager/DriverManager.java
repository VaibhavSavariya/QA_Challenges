package OrangeHRM.DriverManager;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static final ThreadLocal<WebDriver> driverManager = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driverManager.get();
    }

    public static void setDriver(WebDriver dr){
            driverManager.set(dr);
    }

    public static void unload(){
        driverManager.remove();
    }
}
