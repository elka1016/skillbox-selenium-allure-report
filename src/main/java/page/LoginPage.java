package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final By emailTextBox = By.name("email");
    private final By userTextBox = By.name("username");
    private final By passwordTextBox = By.name("password");
    private final By mainMenuText = By.xpath("//a[contains(text(),'Главная')]");
    private final By enterButtonText = By.xpath("//a[contains(text(),'Войти')]");
    private final By errorMessageText = By.cssSelector("ul.woocommerce-error");
    private final By registerButton = By.xpath("//button[@type='button']");
    private final By logOutButton = By.xpath("//a[contains(text(),'Выйти')]");
    private final By registerButtonAccount = By.name("register");
    private final By usernameLabel = By.xpath("//strong");
    private final By loginButton = By.name("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Заполнить имя
    public void setUser(String user) {
        sendKeys(userTextBox, user);
    }

    //Заполнить емаил
    public void setEmail(String email) {
        sendKeys(emailTextBox, email);
    }

    //Заполнить пароль
    public void setPassword(String password) {
        sendKeys(passwordTextBox, password);
    }

    //Проверка отображение элемента
    public void waitForElementPresent(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.withMessage(error_message + "\n");
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    //Кнопка Войти на Главной
    public void clickSignIn() {
        waitForElementPresent(enterButtonText, "Кнопка Войти не отображается");
        click(enterButtonText);
    }

    //Кнопка войти в Личный Кабинет
    public void clickSignInAccount() {
        waitForElementPresent(loginButton, "Кнопка Войти не отображается");
        click(loginButton);
    }

    // Кнопка Зарегистрироваться на странице Мой аккаунт
    public void clickRegister() {
        click(registerButton);
    }

    // Кнопка Зарегистрироваться на странице Регистрация
    public void clickRegisterAccount() {
        click(registerButtonAccount);
    }

    // Кнопка Выйти отображается
    public void logOutIsDisplayed() {
        waitForElementPresent(logOutButton, "Кнопка Войти не отображается");
    }

    // Проверяем отображение ошибки
    public void errorMessageTextIsDisplayed() {
        waitForElementPresent(errorMessageText, "Ошибка авторизации не отображается");
    }

    //Отправляем имя пользователя, пароль
    public void login(String user, String password) {
        setUser(user);
        setPassword(password);
        clickSignInAccount();
    }

    //Заполняем поля регистрации имя, почта, пароль
    public void register(String user, String email, String password) {
        setUser(user);
        setEmail(email);
        setPassword(password);
        clickRegisterAccount();
    }

    public String getMainButtonText() {
        return getElement(mainMenuText).getText();
    }

    public String errorMessageText() {
        return getElement(errorMessageText).getText();
    }

    //Имя пользователя в Мой аккаунт
    public String getLoggedInUsername() {
        return getElement(usernameLabel).getText();
    }

}
