package ru.inno.course.x_clientsTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import ru.inno.course.x_clientsTests.model.Company;
import ru.inno.course.x_clientsTests.model.Employee;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;


public class XClientsBusinessTest {
    public static final String URL_COMPANY = "https://x-clients-be.onrender.com/company";
    public static final String URL_EMPLOYEE = "https://x-clients-be.onrender.com/employee";
    public static final String URL_AUTH = "https://x-clients-be.onrender.com/auth/login";
    public static String TOKEN;
    public static Company newCompany = new Company();
    public static Employee newEmployee = new Employee("April");
    public static Employee newEmployee2 = new Employee("Raf");
    public static Employee newEmployee3 = new Employee("Nick");

    @BeforeAll
    @DisplayName("Авторизация пользователя в роли администратора.Создать компанию")
    public static void authorizeAdmin() {
        String ninjaCreds = """
                {
                  "username": "donatello",
                  "password": "does-machines"
                }
                """;
        TOKEN = given().log().all()
                .body(ninjaCreds)
                .contentType(ContentType.JSON)
                .when().post(URL_AUTH)
                .then().log().all()
                .statusCode(201)
                .extract().path("userToken");
    }


    @Test
    @DisplayName("Добавить нового сотрудника в существующую компанию")
    public void createNewEmployee() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString()).log().all()
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Добавить несколько сотрудников в существующую компанию.Получить список сотрудников")
    public void createSomeNewEmployee() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee1 = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee2 = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee2.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee3 = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee3.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        given()
                .get(URL_EMPLOYEE + "?company=" + idCompany)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItems(idEmployee1, idEmployee2, idEmployee3));


        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Получить информацию о сотруднике по id")
    public void getEmployeeById() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        given()
                .get(URL_EMPLOYEE + "/" + idEmployee)
                .then()
                .assertThat().body(matchesJsonSchema(newEmployee.getJsonString(idCompany)));

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Изменить информацию о сотруднике по id")
    public void updateEmployeeById() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        String updatingEmployee = "{\"lastName\": \"Honor\"," +
                "\"email\": \"ninja01@mail.ru\"," +
                " \"url\": \"text\"," +
                " \"phone\": \"777-777\"," +
                "  \"isActive\": false }";

        given()
                .header("x-client-token", TOKEN)
                .body(updatingEmployee)
                .contentType(ContentType.JSON)
                .when().patch(URL_EMPLOYEE + "/" + idEmployee)
                .then().log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(updatingEmployee));

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Добавить сотрудника в сущ.компанию без авториации")
    @Tag("negative")
    public void addEmployeeWithoutAuth() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        given().log().all()
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(401);

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Изменить сотрудника без авториации")
    @Tag("negative")
    public void updateEmployeeWithoutAuth() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        String updatingEmployee = "{\"lastName\": \"Honor\"," +
                "\"email\": \"ninja01@mail.ru\"," +
                " \"url\": \"text\"," +
                " \"phone\": \"777-777\"," +
                "  \"isActive\": false }";

        given()
                .body(updatingEmployee)
                .contentType(ContentType.JSON)
                .when().patch(URL_EMPLOYEE + "/" + idEmployee)
                .then()
                .statusCode(401);

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Получить список сотрудников несуществующей компании")
    @Tag("negative")
    public void getListEmployeeInvalidCompany() {
        given()
                .get(URL_EMPLOYEE + "?company=" + Integer.MAX_VALUE)
                .then().log().all()
                .statusCode(200)
                .body(
                        "results", hasSize(0)
                );
    }

    @Test
    @DisplayName("Получить инфо о несущ.сотруднике")
    @Tag("negative")
    public void getEmployeeInfoByInvalidId() {
        given()
                .get(URL_EMPLOYEE + "/" + Integer.MAX_VALUE)
                .then()
                .statusCode(200)
                .header("Content-Length", "0");
    }

    @Test
    @DisplayName("Добавить нового сотрудника без компании")
    @Tag("negative")
    public void createNewEmployeeWithoutCompany() {
        String newEmployee = "{\"firstName\": \"April\"," +
                " \"middleName\": \"Rosa\"," +
                " \"lastName\": \"O'Nil\"," +
                " \"companyId\": " + null + "," +
                "\"email\": \"ninja@gmail.com\"," +
                " \"phone\": \"777-777\"," +
                " \"birthdate\": \"2024-03-17T20:50:57.525Z\"," +
                "  \"isActive\": true }";

        given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee)
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    @DisplayName("Добавить сотрудника в существующую компанию. Затем удалить компанию,запросить инфо сотрудника")
    @Tag("negative")
    public void getEmployeeDeletedCompany() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(newEmployee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        deleteCompany(idCompany);

        given()
                .get(URL_COMPANY + "/" + idCompany)
                .then().log().all()
                .statusCode(200);

        given()
                .get(URL_EMPLOYEE + "/" + idEmployee)
                .then().log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(newEmployee.getJsonString(idCompany)));
    }


    private void deleteCompany(int idCompany) {
        given().log().all()
                .header("x-client-token", TOKEN)
                .get(URL_COMPANY + "/" + idCompany)
                .then()
                .statusCode(200)
                .body("id", equalTo(idCompany)).log().all();
    }
}