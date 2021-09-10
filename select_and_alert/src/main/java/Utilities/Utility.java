package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class Utility {

    public static void screenShot(WebDriver driver, String fileName) {

        try {

            TakesScreenshot ss = (TakesScreenshot) driver;
            File source = ss.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(source, new File("C:\\Users\\user\\IdeaProjects\\select_and_alert\\src\\Screenshots\\"+fileName+".png"));
            System.out.println("Screenshot captured\n Filename :" +fileName+".png");

        }
        catch (Exception e){

            System.out.println("Exception caught while capturing ScreenShot :: " +e.getMessage());

        }

    }
}

