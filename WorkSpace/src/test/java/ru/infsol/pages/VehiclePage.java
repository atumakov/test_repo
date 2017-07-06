package ru.infsol.pages;

import org.apache.tika.io.TemporaryResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alexander Tumakov on 30.06.2017.
 */
public class VehiclePage {

    //    #vehicle-regNumber
    @FindBy(css = "#vehicle-regNumber")
    private WebElement grzTextArea;

    private static VehiclePage page;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Logger l;
    private VehiclePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        l=  Logger.getLogger(VehiclePage.class.getName());
        PageFactory.initElements(driver, this);
    }

    public static VehiclePage getInstance(WebDriver driver, WebDriverWait wait) {
        if (page == null) {
            page = new VehiclePage(driver, wait);
            return page;
        } else {
            return page;
        }
    }

    public String getGrzNumber() {


        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vehicle-regNumber']")));
            Thread.sleep(1500);
        }catch (Exception e){
            l.log(Level.SEVERE, "Exception: " + driver.getTitle() + ":" + driver.getCurrentUrl(), e);
        }
        return grzTextArea.getText();
    }

}
