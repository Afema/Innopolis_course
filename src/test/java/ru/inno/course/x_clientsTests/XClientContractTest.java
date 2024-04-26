package ru.inno.course.x_clientsTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.course.XClient.db.model.Company;
import ru.inno.course.XClient.db.model.Employee;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class XClientContractTest {
    public static final String URL_COMPANY = "https://x-clients-be.onrender.com/company";
    public static final String URL_EMPLOYEE = "https://x-clients-be.onrender.com/employee";
    public static final String URL_AUTH = "https://x-clients-be.onrender.com/auth/login";
    public static String TOKEN;
    public static Company newCompany = new Company();
    public static Employee newEmployee = new Employee();

    @BeforeAll
    @DisplayName("Авторизация пользователя в роли администратора")
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
    @DisplayName("Проверить что создание компании доступно")
    public void createNewCompany() {

        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");

        deleteCompany(idCompany);

    }

    @Test
    @DisplayName("Добавить нового сотрудника в компанию")
    public void createNewEmployee() {

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
    }

    @Test
    @DisplayName("Получить список сотрудников")
    public void getListEmployee() {

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
                .get(URL_EMPLOYEE + "?company=" + idCompany)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON);

        deleteCompany(idCompany);

    }


    @Test
    @DisplayName("Получить сотрудника по id")
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
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(idEmployee));

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
                .contentType(ContentType.JSON)
                .body("email", equalTo("ninja01@mail.ru"));

        deleteCompany(idCompany);
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
