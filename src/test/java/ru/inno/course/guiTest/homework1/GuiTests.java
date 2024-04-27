package ru.inno.course.guiTest.homework1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuiTests {
    private WebDriver driver;

    @BeforeEach
    public void setDriver() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешная авторизация")
    public void checkSuccessfulAuth() {
        driver.get("http://www.uitestingplayground.com/sampleapp");
        driver.findElement(By.cssSelector("input[name=UserName]")).sendKeys("inno");
        driver.findElement(By.cssSelector("input[name=Password]")).sendKeys("pwd");
        driver.findElement(By.cssSelector("#login")).click();
        WebElement textSuccess = driver.findElement(By.cssSelector(".text-success"));
        String text = textSuccess.getText();
        assertEquals("Welcome, inno!", text);
    }

    @Test
    @Tag("positive")
    @DisplayName("Переименование кнопки")
    public void checkRenameButton() {
        driver.get("http://www.uitestingplayground.com/textinput");
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Привет");
        driver.findElement(By.cssSelector("#updatingButton")).click();
        String newButtonName = driver.findElement(By.cssSelector("#updatingButton")).getText();
        assertEquals("Привет", newButtonName);
    }

    @Test
    @Tag("negative")
    @DisplayName("Отправить форму без обязательных для заполнения полей")
    public void checkSubmitFormWithoutRequredFields() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        driver.findElement(By.cssSelector("input[name=first-name]")).sendKeys("Nick");
        driver.findElement(By.cssSelector("input[name=last-name]")).sendKeys("Harrison");
        driver.findElement(By.cssSelector("input[name=address]")).sendKeys("20806 Fadel Junctions");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Los Angeles");
        driver.findElement(By.cssSelector("input[name=country]")).sendKeys("USA");
        driver.findElement(By.cssSelector("input[name=job-position]")).sendKeys("Chemist");
        driver.findElement(By.cssSelector("input[name=company]")).sendKeys("Koss Inc");
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        WebElement successfulField = driver.findElement(By.cssSelector(".alert-success"));
        String successfulFieldBackgroundColor = successfulField.getCssValue("background-color");
        assertEquals("rgba(209, 231, 221, 1)", successfulFieldBackgroundColor);

        WebElement dangerFields = driver.findElement(By.cssSelector(".alert-danger"));
        String dangerFieldsBackgroundColor = dangerFields.getCssValue("background-color");
        String dangerFieldsText = dangerFields.getText();
        assertEquals("rgba(248, 215, 218, 1)", dangerFieldsBackgroundColor);
        assertEquals("N/A", dangerFieldsText);
    }

    @Test
    @Tag("positive")
    @DisplayName("Дождаться картинку")
    public void waiteThirdImage() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#award"), "src", "https://bonigarcia.dev/selenium-webdriver-java/img/award.png"));
        String text = driver.findElement(By.cssSelector("#award")).getAttribute("src");
        assertTrue(text.endsWith("award.png"));
    }

    @Test
    @Tag("positive")
    @DisplayName("Добавить книги в корзину")
    public void addBooks() throws Exception {
        driver.get("https://www.labirint.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cookie-policy")));
        driver.findElement(By.xpath("//*[@id=\"minwidth\"]/div[4]/div[2]/button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        WebElement searchInput = driver.findElement(By.cssSelector("input#search-field"));
        searchInput.sendKeys("java");
        searchInput.sendKeys(Keys.ENTER);
        List<WebElement> buttonList = driver.findElements(By.cssSelector("._btn.btn-tocart.buy-link"));
        buttonList.forEach(WebElement::click);
        Thread.sleep(5000L);
        WebElement cartCount = driver.findElement(By.xpath("//*[@id=\"minwidth\"]/div[4]/div/div[1]/div[1]/div[2]/div/ul/li[6]/a/span[1]/span[1]/span[3]"));
        String count = cartCount.getText();
        cartCount.click();
        Thread.sleep(5000L);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement basket = driver.findElement(By.cssSelector("#basket-step1-default > div.b-carttotal.b-carttotal-m-fixed > div.js-order-summary-block.order-summary.order-summary-default > div.mb10 > span > span:nth-child(1) > span.vue-object"));
        String basketText = basket.getText();
        String countItemText = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        assertEquals("60", count);
        assertEquals("В корзине 60 товаров", basketText + " " + countItemText);
    }
}
