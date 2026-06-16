package OrangeHRM.Utils;

import OrangeHRM.DriverManager.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil  {

    public static String takeScreenshot() throws Exception {

        WebDriver driver = DriverManager.getDriver();
        String fileName = System.currentTimeMillis() + ".png";
        String path = System.getProperty("user.dir") + "/test-output/screenshots/" + fileName;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(
                    src,
                    new File(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    }
