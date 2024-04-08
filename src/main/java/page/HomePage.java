package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By tabPromo = By.xpath("//aside[@id='accesspress_storemo-3']");
    private final By addToButton = By.xpath("//a[contains(text(),'В корзину')]");
    private final By detailsButton = By.xpath("//a[contains(text(),'Подробнее')]");
    private final By orderButton = By.xpath("//a[contains(text(),'ОФОРМИТЬ ЗАКАЗ')]");
    private final By authText = By.xpath("//a[contains(text(),'Авторизуйтесь')]");
    private final By placeOrderButton = By.id("place_order");
    private final By billingFirstName = By.id("billing_first_name");
    private final By billingLasttName = By.id("billing_last_name");
    private final By billingAddress1 = By.id("billing_address_1");
    private final By billingCity = By.id("billing_city");
    private final By billingState = By.id("billing_state");
    private final By billingPostcode = By.id("billing_postcode");
    private final By billingPhone = By.id("billing_phone");
    private final By billingEmail = By.id("billing_email");
    private final By countryRegion = By.id("select2-billing_country-container");
    private final By inputFieldCountryRegrion = By.cssSelector("input.select2-search__field");
    private final By titleOrderedText = By.cssSelector("h2.post-title");
    private final By numberOrderText = By.xpath("//strong");
    private final By dateOrderText = By.xpath("//li[2]/strong");
    private final By emailOrderText = By.xpath("//li[3]/strong");
    private final By summOrder = By.xpath("//bdi");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Переход в категорию Планшеты
    public void selectTabPromo() {
        click(tabPromo);
    }

    //Нажать В корзину
    public void clickAddTo() {
        click(addToButton);
    }

    //Нажать подробнее
    public void openDetails() {
        click(detailsButton);
    }

    //Нажать оформить заказ
    public void clickOrderButton() {
        click(orderButton);
    }

    //Нажать авторизуйтесь
    public void clickAuthText() {
        click(authText);
    }

    //Нажать заказать в оформлении заказа
    public void clickPlaceOrderButton() {
        click(placeOrderButton);
    }

    //Скролл вниз до кнопки оформление заказа
    public void scrollToPlaceOrderButton() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 250);");
    }

    //Заполняем заказ имя, фамилия, страна, адресс. город, область, индекс, номер, емаил
    public void fillOrderDetails(String name, String lastName, String country, String address1, String city, String state,
                                 String postCode, String phone, String email) {
        sendKeys(billingFirstName, name);
        sendKeys(billingLasttName, lastName);
        click(countryRegion);
        sendKeys(inputFieldCountryRegrion, country);
        sendKeys(billingAddress1, address1);
        sendKeys(billingCity, city);
        sendKeys(billingState, state);
        sendKeys(billingPostcode, postCode);
        sendKeys(billingPhone, phone);
        sendKeys(billingEmail, email);
    }

    public String getTitleOrderText() {
        return getElement(titleOrderedText).getText();
    }

    public String getNumberOrder() {
        return getElement(numberOrderText).getText();
    }

    public String getDateOrder() {
        return getElement(dateOrderText).getText();
    }

    public String getEmailOrder() {
        return getElement(emailOrderText).getText();
    }

    public String getSummOrder() {
        return getElement(summOrder).getText();
    }

}
