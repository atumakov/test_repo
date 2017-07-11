package ru.infsol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Максим on 04.07.2017.
 */
public class AdminPage {


    @FindBy(xpath = ".//*[text()='Администрирование']/..")
    private WebElement adminPageButton;

    @FindBy(xpath = "//a[@onclick=\"return gai.administration.departments.saveUser(this);\"]")
    private WebElement saveButtonInUserCard;

//    @FindBy(xpath = "//div[@class=\"k-multiselect-wrap k-floatwrap\"]")
//    private WebElement userGroupField;

    @FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
    private WebElement userGroupField;

    @FindBy(xpath = "//li[@data-offset-index=\"0\"]")
    private WebElement resultGroupAfterFind;

    @FindBy(xpath = ".//*[text()='(выход)']/..")
    private WebElement exitButton;

    @FindBy(css = ".ui-button.ui-dialog-search-button")
    private WebElement addUserButton;

    @FindBy(css = ".k-selectable>tbody")
    private WebElement userTable;

    @FindBy(css = ".k-selectable>tbody>tr")
    private WebElement userInfoRow;

    @FindBy(css = "input#user-firstName")
    private WebElement firstNameTextField;

    @FindBy(css = "#user-middleName")
    private WebElement middleNameTextField;

    @FindBy(css = "#user-lastName")
    private WebElement lastNameTextField;

    @FindBy(css = "#user-login")
    private WebElement userLoginTextField;

    @FindBy(css = "#user-birthDate")
    private WebElement userBirthDate;

    @FindBy(css = "#user-password")
    private WebElement userPasswordTextField;

    @FindBy(css = "#user-repeatPassword")
    private WebElement userRepeatPasswordTextField;


    private static AdminPage page;
    private static WebDriver driver;
    private static WebDriverWait wait;

    private AdminPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public static AdminPage getInstance(WebDriver driver, WebDriverWait wait) {
        if (page == null) {
            page = new AdminPage(driver, wait);
            return page;
        } else {
            return page;
        }
    }

    public void enterInAdministratorPage() {
        driver.get("http://5.189.128.204/officer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Администрирование']/..")));
        adminPageButton.click();
    }


    public void enterToUserCard() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".k-selectable>tbody")));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//td[contains(.,\"ТЕСТ\")]")).click();
    }

    public void addNewUser() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".k-selectable>tbody")));
        driver.findElement(By.xpath("//a[contains(text(),\"Добавить пользователя\")]")).click();
    }

    public void workWithUserInfo(String userPrefix) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#user-firstName")));
        firstNameTextField.clear();
        firstNameTextField.sendKeys("ТЕСТ" + userPrefix);
        middleNameTextField.clear();
        middleNameTextField.sendKeys("ТЕСТОВИЧ" + userPrefix);
        lastNameTextField.clear();
        lastNameTextField.sendKeys("ТЕСТОВ" + userPrefix);
        userBirthDate.clear();
        userBirthDate.sendKeys("03011981");
        userLoginTextField.clear();
        userLoginTextField.sendKeys("TEST-03011981" + userPrefix);
    }

    public void setUserPassword() {
        userPasswordTextField.clear();
        userPasswordTextField.sendKeys("My12345678");
        userRepeatPasswordTextField.clear();
        userRepeatPasswordTextField.sendKeys("My12345678");
    }


    public void workWithGroupOptions(String group) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //div[@class='k-multiselect-wrap k-floatwrap']")));
        userGroupField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-groups-list']")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='user-groups_listbox']")));
        List<WebElement> elements = driver.findElement(By.xpath("//ul[@id='user-groups_listbox']"))
                .findElements(By.xpath("//li[@role='option']"));

        for (WebElement e : elements) {
            if (e.getText().contains(group)) {
                e.click();
            }
        }
        firstNameTextField.click();
    }

    public void saveUserCard() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@onclick=\"return gai.administration.departments.saveUser(this);\"]")));
        saveButtonInUserCard.click();
    }

    public void exitAndEnterInUserInterface(String userPostfix) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".k-selectable>tbody")));
        exitButton.click();
        try {
            LoginPage.getInstance(driver, wait).startPageParams("TEST-03011981" + userPostfix, "My12345678");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isLoginExist() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (driver.findElement(By.xpath("//p[contains(text(),\"Такой логин уже был использован\")]")).isDisplayed()) {
                driver.findElement(By.cssSelector(".k-icon.k-i-close")).click();
                driver.findElement(By.cssSelector(".k-icon.k-i-close")).click();
            }
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            System.err.print("Warning : user exist!");
        }

    }

}
