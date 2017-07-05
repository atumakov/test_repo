package ru.infsol.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.infsol.pages.AdminPage;
import ru.infsol.pages.LoginPage;
import ru.infsol.pages.OfficerPage;
import ru.infsol.pages.VehiclePage;
import ru.yandex.qatools.allure.annotations.Step;

import java.awt.*;
import java.sql.Driver;

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
    public void setUp() throws AWTException {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30, 500);

        loginPage = LoginPage.getInstance(driver, wait);
        officerPage = OfficerPage.getInstance(driver, wait);
        vehiclePage = VehiclePage.getInstance(driver, wait);
        adminPage = AdminPage.getInstance(driver, wait);

        loginPage.startPage();

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
        officerPage.enterGrzNumber(grzNumber);
        officerPage.findGrz();
        officerPage.getTableElemets();
        Assert.assertTrue(grzNumber.contains(vehiclePage.getGrzNumber()));
        officerPage.restartPage();
    }

    /*
     * 1. Вход во кладку Админисрирование/пользователи
     * 2. Открываем карту с пользователя с именем Тест
     * 3. Заполняем все необходимые для сохранения поля
     * 4. Меняем тип пользователя на администратора
     * 5. Сохраняем карту пользователя
     * 6. ищем в таблице пользователя с установленным логином TEST-03011981 если находим заканчиваем тест
     */


    @Test(dependsOnMethods = "grzTest",
            alwaysRun = true,priority = 2)
    public void adminWorkWithUser() throws Exception {
        adminPage.enterInAdministratodPage();
        adminPage.enterToUserCard();
        adminPage.workWithUserCard();
        adminPage.workWithGroupOptions("Оператор");
        adminPage.saveUserCard();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,\"ТЕСТ\")]")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(.,\"TEST-03011981\")]")).getText().contains("03011981"));
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }


}
