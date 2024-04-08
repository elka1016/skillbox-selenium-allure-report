package test;

import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static util.driver.DriverHolder.getDriver;

@Epic("User Account")
@Feature("Register")
public class RegisterUserTest extends BaseTest {

    private LoginPage loginPage;
    String generateUserString = RandomStringUtils.randomAlphabetic(5);

    @BeforeMethod
    public void loginBeforeMethod() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(description = "Verify that a valid user can register to the application")
    @Severity(BLOCKER)
    @Description("Verify that a valid user can register to the application")
    @Story("As a user I should be able to register to the application")
    public void testRegisterNewUser() {
        //На главной клик войти
        assertTrue(loginPage.getMainButtonText().contains("ГЛАВНАЯ"));
        loginPage.clickSignIn();
        assertEquals(getDriver().getTitle(), "Мой аккаунт — Skillbox");

        //В форме авторизации Клик Зарегистрироваться
        loginPage.clickRegister();
        assertEquals(getDriver().getTitle(), "Регистрация — Skillbox");

        //Регистрируем рандомное имя, рандомный емаил и пароль
        loginPage.register(generateUserString, generateUserString+"@skillbox.test", "password");
        loginPage.logOutIsDisplayed();
    }

    @Test(description = "Verify that an exist user can't register to the application")
    @Severity(BLOCKER)
    @Description("Verify that an exist user can't register to the application")
    @Story("As an exist user I should not be able to register to the application")
    public void testRegisterExistUser() {
        //На главной клик войти
        assertTrue(loginPage.getMainButtonText().contains("ГЛАВНАЯ"));
        loginPage.clickSignIn();
        assertEquals(getDriver().getTitle(), "Мой аккаунт — Skillbox");

        //В форме авторизации Клик Зарегистрироваться
        loginPage.clickRegister();
        assertEquals(getDriver().getTitle(), "Регистрация — Skillbox");

        //Регистрируем имя, емаил и пароль
        loginPage.register("test", "test@skillbox.test", "password");

        //Проверяем отображение ошибки тк пользователь уже зарегистрирован
        loginPage.errorMessageTextIsDisplayed();

        //Проверяем корректность текста в ошибке
        assertEquals(loginPage.errorMessageText(), "Error: Учетная запись с такой почтой уже зарегистировавана. Пожалуйста авторизуйтесь.");
    }
}
