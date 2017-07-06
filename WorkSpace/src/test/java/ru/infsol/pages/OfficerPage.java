package ru.infsol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander Tumakov on 30.06.2017.
 */
public class OfficerPage {
    @FindBy(css = "#officer-list-filter-regNo")
    private WebElement grzTextArea;

    @FindBy(css = ".k-icon.k-i-search")
    private WebElement findButton;

    @FindBy(xpath = "//tbody[@role='rowgroup']")
    private WebElement tableElements;


    @FindBy(css = "#offence-list-filter-offenceDateFrom")
    private WebElement dateTextFrom;

    @FindBy(css = "#offence-list-filter-offenceDateTo")
    private WebElement dateTextTo;

    @FindBy(css = "#offence-list-filter-draft")
    private WebElement draftFilter;

    @FindBy(css = "#offence-list-filter-verified")
    private WebElement verifiedFilter;

    @FindBy(css = "#offence-list-filter-confirmed")
    private WebElement confirmedFilter;

    @FindBy(css = "#offence-list-filter-rejected")
    private WebElement offenceListFilterRejected;

    @FindBy(css = "#offence-list-filter-withdrawn")
    private WebElement offenceListFilterWithdrawn;

    @FindBy(css = "#offence-list-filter-signed")
    private WebElement offenceListFilterSigned;

    @FindBy(css = "#offence-list-filter-other")
    private WebElement offenceListFilterOther;

    @FindBy(css = "#offence-list-mail")
    private WebElement reloadMailStatusButton;

    @FindBy(css = "#offence-list-download")
    private WebElement downloadReestrButton;

    @FindBy(css = "#offence-list-extended-filter-button")
    private WebElement extendedFilterButton;


    private static OfficerPage page;
    private String grzNumber;
    private static WebDriver driver;
    private static WebDriverWait wait;

    private OfficerPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public static OfficerPage getInstance(WebDriver driver, WebDriverWait wait) {
        if (page == null) {
            page = new OfficerPage(driver, wait);
            return page;
        } else {
            return page;
        }
    }

    public void enterGrzNumber(String string) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#officer-list-filter-regNo")));
        grzNumber = string;
        grzTextArea.clear();
        grzTextArea.sendKeys(string);
    }

    public void findGrz() throws Exception {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".k-icon.k-i-search")));
        findButton.click();
    }

    public void getTableElemets() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".k-selectable>tbody>tr")));
        List<WebElement> array = tableElements.findElements(By.cssSelector(".k-selectable>tbody>tr"));
        Thread.sleep(1000);
        array.get(0).click();
    }

    public void restartPage() {
        driver.get("http://5.189.128.204/officer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#offence-list-filter-draft")));
        grzTextArea.clear();
        try {
            findGrz();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDateTextFrom(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#offence-list-filter-offenceDateFrom")));
        dateTextFrom.clear();
        dateTextFrom.sendKeys(date);
    }
    public void setDateTextTo(String date){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#offence-list-filter-offenceDateTo")));
        dateTextTo.clear();
        dateTextTo.sendKeys(date);
    }

}
