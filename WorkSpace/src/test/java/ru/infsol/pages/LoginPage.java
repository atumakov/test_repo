package ru.infsol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Alexander Tumakov on 30.06.2017.
 */
public class LoginPage {

    @FindBy(css = "input#login")
    private WebElement login;

    @FindBy(css = "input#password")
    private WebElement password;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = "#sudis-button")
    private WebElement sudisButton;


    private static LoginPage page;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private String url;

    private LoginPage(WebDriver driver, WebDriverWait wait) {

        url = "http://fism_viewer:SuperInfSol2017@5.189.128.204/login?isLocal=true";
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.driver.manage().window().maximize();

    }

    public static LoginPage getInstance(WebDriver driver, WebDriverWait wait) {
        if (page == null) {
            page = new LoginPage(driver, wait);
            return page;
        } else {
            return page;
        }
    }


    public void startPage() throws Exception {
        driver.get(url);

        setLogin();
        setPassword();
        enterToSystem();
    }

    private void setLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#login")));
        login.sendKeys("tumakovaa");

    }

    private void setPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#password")));
        password.sendKeys("6031769Aa");

    }

    private void enterToSystem() {
        loginButton.click();
    }


}
