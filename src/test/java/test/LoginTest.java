package test;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static util.driver.DriverHolder.getDriver;

@Epic("User Account")
@Feature("Login")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void loginBeforeMethod() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(description = "Verify that a valid user can login to the application")
    @Severity(BLOCKER)
    @Description("Verify that a valid user can login to the application")
    @Story("As a user I should be able to login to the application")
    public void testValidLogin() {
        //На главной клик войти
        assertTrue(loginPage.getMainButtonText().contains("ГЛАВНАЯ"));
        loginPage.clickSignIn();
        assertEquals(getDriver().getTitle(), "Мой аккаунт — Skillbox");

        //Заполняем имя и пароль
        loginPage.login("elka","test");

        //Отображается имя пользователя AP
        assertEquals(loginPage.getLoggedInUsername(), "elka");

        //Отображается кнопка выйти
        loginPage.logOutIsDisplayed();
    }

    @Test(description = "Verify that an invalid user can't login to the application")
    @Severity(NORMAL)
    @Description("Verify that an invalid user can't login to the application")
    @Story("As an invalid user I should not be able to login to the application")
    public void testInValidLogin() {
        //На главной клик войти
        assertTrue(loginPage.getMainButtonText().contains("ГЛАВНАЯ"));
        loginPage.clickSignIn();
        assertEquals(getDriver().getTitle(), "Мой аккаунт — Skillbox");

        //Заполняем невалидное имя и пароль
        loginPage.login("elka@mail.com","password");

        //Проверяем отображение ошибки тк пользователь уже зарегистрирован
        loginPage.errorMessageTextIsDisplayed();

        //Проверяем корректность текста в ошибке
        assertEquals(loginPage.errorMessageText(), "Неизвестный адрес почты. Попробуйте еще раз или введите имя пользователя.");
    }
}
