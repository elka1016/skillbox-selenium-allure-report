package test;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.*;
import static util.driver.DriverHolder.getDriver;

@Epic("Order Placing")
@Feature("Buy")
public class BuyItemTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void loginBeforeMethod() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test(description = "Verify that the user able to order items")
    @Severity(CRITICAL)
    @Description("Verify that the user able to order items")
    @Story("As a user I should be able to order items")
    public void testBuyItem() {
//        assertTrue(loginPage.getMainButtonText().contains("ГЛАВНАЯ"));
        //На Главной выбрать категорию планшеты
        homePage.selectTabPromo();
        //Нажать В корзину
        homePage.clickAddTo();
        //Нажать Подробнее
        homePage.openDetails();
        //Нажать Оформить заказ
        homePage.clickOrderButton();
        //Нажать авторизуйтесь
        homePage.clickAuthText();
        //Заполнить имя пользователя и пароль
        loginPage.login("elka","test");
        //Заполнить имя, фамилия, выбрать страну, адрес, город, область, индекс, телефон, адрес почты
        homePage.fillOrderDetails("Elena", "Ivanova", "Russia", "Sanf ul.", "Samara",
                "Samarskay", "440108", "+79879876655", "elka@test.ru");
        //Скрол до кнопки Оформить заказ
        homePage.scrollToPlaceOrderButton();
        //Нажать Оформить заказ
        homePage.clickPlaceOrderButton();

        //Проверяем отображение заказа(номер заказа, дата, емаил, сумма)
        assertNotNull(homePage.getTitleOrderText());
        assertNotNull(homePage.getNumberOrder());
        assertNotNull(homePage.getDateOrder());
        assertEquals(homePage.getEmailOrder(), "elka@test.ru");
        assertNotNull(homePage.getSummOrder());
    }
}
