package minipro;
import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot { // Class to take screenshot

    public static void takeScreenshot(WebDriver driver, String name) throws IOException {

        File s = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(s, new File(
                System.getProperty("user.dir") + "\\Screenshot\\Screenshots\\" + "Screenshot" + name + ".jpg"));
        // Save the screenshots as Screenshot(i).jpg
    }

}