package ru.infsol.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.infsol.pages.AdminPage;
import ru.infsol.pages.LoginPage;
import ru.infsol.pages.OfficerPage;
import ru.infsol.pages.VehiclePage;

import java.util.Random;

/**
 * Created by  Alexander Tumakov  on 30.06.2017.
 */
public class Tests {
    private LoginPage loginPage;
    private OfficerPage officerPage;
    private VehiclePage vehiclePage;
    private AdminPage adminPage;
    private WebDriver driver;
    private WebDriverWait wait;
    private String grzNumber;


    @BeforeSuite
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30, 500);

        loginPage = LoginPage.getInstance(driver, wait);
        officerPage = OfficerPage.getInstance(driver, wait);
        vehiclePage = VehiclePage.getInstance(driver, wait);
        adminPage = AdminPage.getInstance(driver, wait);

        try {
            loginPage.startPage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    * Подготовка: вход в ЦАФАП
    * 1. в поле ввода поиска по ГРЗ вводимтся номер ГРЗ
    * 2. нажимается кнопка поиска
    * 3. выбирается первая запись в таблице
    * 4. открывается карта нарушения
    * 5. проверка по грз введенному в поле поиск и отображенному в карте нарушения
    * 6. возврат на страницу правонарушений
    * */

    @Test(priority = 1)
    public void grzTest() throws Exception {
        grzNumber = "Х040КУ30";
        officerPage.setDateTextFrom("01.05.2017");
        officerPage.setDateTextTo("06.07.2017");
        officerPage.enterGrzNumber(grzNumber);
        officerPage.findGrz();
        officerPage.getTableElemets();
        Assert.assertTrue(grzNumber.contains(vehiclePage.getGrzNumber()));
    }

    
    @Test(dependsOnMethods = "grzTest",
            alwaysRun = true,priority = 2)
    public void adminCreateNewUserTest() throws Exception {
        String prefix = getRandomWord(3);
        adminPage.enterInAdministratorPage();
        adminPage.addNewUser();
        adminPage.workWithUserInfo(prefix);
        adminPage.workWithGroupOptions("Оператор");
        adminPage.setUserPassword();
        adminPage.saveUserCard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,\"ТЕСТ\")]")));
        adminPage.isLoginExist();
        adminPage.exitAndEnterInUserInterface(prefix);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".arm-user-name")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(.,\"ТЕСТОВ\")]")).isDisplayed());

    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    private String getRandomWord(int length) {
        String r = "";
        for(int i = 0; i < length; i++) {
            r += (char)(Math.random() * 26 + 97);
        }
        return r;
    }

}
