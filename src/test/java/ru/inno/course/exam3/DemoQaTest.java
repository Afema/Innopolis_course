package ru.inno.course.exam3;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.inno.course.exam3.Pages.LoginPage;
import ru.inno.course.exam3.Pages.ProfilePage;
import ru.inno.course.exam3.helpers.PropsHelper;
import ru.inno.course.exam3.model.Book;
import ru.inno.course.exam3.model.BookCollection;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoQaTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    private final Book book1 = new Book("9781449325862");
    private final Book book2 = new Book("9781449331818");
    private final Book book3 = new Book("9781449337711");
    private final Book book4 = new Book("9781449365035");
    private final Book book5 = new Book("9781491904244");
    private final Book book6 = new Book("9781491950296");
    private final BookCollection twoBookCollection = new BookCollection(new Book[]{book1, book2});
    private final BookCollection sixBookCollection = new BookCollection(new Book[]{book1, book2, book3, book4, book5, book6});

    @BeforeEach
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @AfterEach
    public void quit() {
        deleteAllBooksUsingAPI();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Сценарий 1: Проверка пустой таблицы")
    @Description("При первой авторизации нового юзера коллекция должна быть пустой")
    @Owner("Emelina Oxana")
    @Severity(SeverityLevel.CRITICAL)
    public void checkEmptyTable() {
        loginPage.authorize(PropsHelper.getLogin(), PropsHelper.getPass());
        assertEquals("No rows found", profilePage.getEmptyData(), "При первой авторизации нового юзера коллекция НЕпустая");
    }

    @Test
    @Tag("positive")
    @DisplayName("Сценарий 2: Проверка добавления 6 книг в коллекцию")
    @Description("Проверка добавления книг")
    @Owner("Emelina Oxana")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddingBooks() {
        addBooksUsingAPI(sixBookCollection); //Это БАГ приложения, нет возможности с gui добавить
        loginPage.authorize(PropsHelper.getLogin(), PropsHelper.getPass());
        profilePage.setTenRowsOnPage();
        assertEquals(6, profilePage.getListOfBooks().size(), "В коллекцию не добавились две книги");
    }

    @Test
    @Tag("positive")
    @DisplayName("Сценарий 3: Проверка удаления книг из коллекции")
    @Description("Проверка удаления книг")
    @Owner("Emelina Oxana")
    @Severity(SeverityLevel.CRITICAL)
    public void checkDeletingBooks() {
        loginPage.authorize(PropsHelper.getLogin(), PropsHelper.getPass());
        addBooksUsingAPI(twoBookCollection);
        profilePage.refreshPage();
        //profilePage.openProfilePage(); Это БАГ приложения - при переходе сюда обнуляется коллекция книг (потому закомментила как и в других тестах этот шаг)
        assertEquals(2, profilePage.getListOfBooks().size(), "В коллекцию не добавились две книги");
        profilePage.deleteAllBooks();
        //profilePage.openProfilePage();
        assertEquals("No rows found", profilePage.getEmptyData(), "После удаления всех книг коллекция непустая");
    }

    private void addBooksUsingAPI(BookCollection books) {
        given()
                .auth()
                .preemptive()
                .basic(PropsHelper.getLogin(), PropsHelper.getPass())
                .contentType(ContentType.JSON)
                .body(books)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(PropsHelper.getBookStoreUrl())
                .then()
                .statusCode(201);
    }

    private void deleteAllBooksUsingAPI() {
        given()
                .auth()
                .preemptive()
                .basic(PropsHelper.getLogin(), PropsHelper.getPass())
                .contentType(ContentType.JSON)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .delete(PropsHelper.getBooksByUserIdUrl())
                .then()
                .statusCode(204);
    }
}