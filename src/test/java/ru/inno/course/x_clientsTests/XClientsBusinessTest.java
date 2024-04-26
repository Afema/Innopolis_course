package ru.inno.course.x_clientsTests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import ru.inno.course.XClient.db.EmployeeRepository;
import ru.inno.course.XClient.db.EmployeeRepositoryJDBC;
import ru.inno.course.XClient.db.model.Company;
import ru.inno.course.XClient.db.model.Employee;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class XClientsBusinessTest {
    public static final Faker faker = new Faker();
    public static final String URL_COMPANY = "https://x-clients-be.onrender.com/company";
    public static final String URL_EMPLOYEE = "https://x-clients-be.onrender.com/employee";
    public static final String URL_AUTH = "https://x-clients-be.onrender.com/auth/login";
    public static String TOKEN;
    public static final EmployeeRepository repositoryJDBC = new EmployeeRepositoryJDBC();
    public static Company newCompany = new Company();
    private int employeeIDToDelete;

    @AfterEach
    public void tearDown() throws SQLException {
        repositoryJDBC.deleteEmployeeByIdDB(employeeIDToDelete);
    }

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
    public void createNewEmployee() throws SQLException {

        int idCompany = createNewCompanyApi();

        Employee employeeAPI = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int idEmployee = createNewEmployeeApi(employeeAPI, idCompany);

        Employee employeeDb = repositoryJDBC.getEmployeeByIdDB(idEmployee);
        assertEquals(idEmployee, employeeDb.getId());
        assertEquals(employeeAPI.getFirstName(), employeeDb.getFirstName());
        assertTrue(employeeDb.isActive());

        deleteCompany(idCompany);
        employeeIDToDelete = employeeDb.getId();
    }

    @Test
    @DisplayName("Добавить несколько сотрудников в существующую компанию.Получить список сотрудников")
    public void createSomeNewEmployee() throws SQLException {

        int idCompany = createNewCompanyApi();

        Employee employeeAPI1 = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        Employee employeeAPI2 = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        Employee employeeAPI3 = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int idEmployee1 = createNewEmployeeApi(employeeAPI1, idCompany);
        int idEmployee2 = createNewEmployeeApi(employeeAPI2, idCompany);
        int idEmployee3 = createNewEmployeeApi(employeeAPI3, idCompany);


        given()
                .get(URL_EMPLOYEE + "?company=" + idCompany)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItems(idEmployee1, idEmployee2, idEmployee3));

        Employee employeeDb1 = repositoryJDBC.getEmployeeByIdDB(idEmployee1);
        Employee employeeDb2 = repositoryJDBC.getEmployeeByIdDB(idEmployee2);
        Employee employeeDb3 = repositoryJDBC.getEmployeeByIdDB(idEmployee3);
        assertEquals(idEmployee1, employeeDb1.getId());
        assertEquals(idEmployee2, employeeDb2.getId());
        assertEquals(idEmployee3, employeeDb3.getId());

        deleteCompany(idCompany);
        employeeIDToDelete = employeeDb1.getId();
        employeeIDToDelete = employeeDb2.getId();
        employeeIDToDelete = employeeDb3.getId();
    }

    @Test
    @DisplayName("Получить информацию о сотруднике по id")
    public void getEmployeeById() throws SQLException {

        int idCompany = createNewCompanyApi();

        Employee employeeDb = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(),
                idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int newEmployeeId_DB = repositoryJDBC.createEmployeeDB(employeeDb);

        var employeeResponse = given()
                .get(URL_EMPLOYEE + "/" + newEmployeeId_DB)
                .then();

        assertEquals(employeeDb.getFirstName(), employeeResponse.extract().path("firstName"));
        assertEquals(newEmployeeId_DB, (int) employeeResponse.extract().path("id"));

        deleteCompany(idCompany);
        employeeIDToDelete = newEmployeeId_DB;

    }

    @Test
    @DisplayName("Изменить информацию о сотруднике по id")
    public void updateEmployeeById() throws SQLException {

        int idCompany = createNewCompanyApi();

        Employee employeeAPI = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int idEmployee = createNewEmployeeApi(employeeAPI, idCompany);

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

        Employee employeeDb = repositoryJDBC.getEmployeeByIdDB(idEmployee);
        assertEquals(idEmployee, employeeDb.getId());
        assertEquals("Honor", employeeDb.getLastName());
        assertEquals("ninja01@mail.ru", employeeDb.getEmail());
        assertFalse(employeeDb.isActive());

        deleteCompany(idCompany);
        employeeIDToDelete = employeeDb.getId();
    }

    @Test
    @DisplayName("Добавить сотрудника в сущ.компанию без авторизации")
    @Tag("negative")
    public void addEmployeeWithoutAuth() {

        int idCompany = createNewCompanyApi();

        Employee employeeAPI = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        given().log().all()
                .body(employeeAPI.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(401);

        deleteCompany(idCompany);
    }

    @Test
    @DisplayName("Изменить сотрудника без авторизации")
    @Tag("negative")
    public void updateEmployeeWithoutAuth() {
        int idCompany = createNewCompanyApi();

        Employee employeeAPI = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int idEmployee = createNewEmployeeApi(employeeAPI, idCompany);

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

        int idCompany = createNewCompanyApi();

        Employee employeeAPI = new Employee(
                faker.name().firstName(),
                faker.name().username(),
                faker.name().lastName(), idCompany,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                true);

        int idEmployee = createNewEmployeeApi(employeeAPI, idCompany);

        deleteCompany(idCompany);

        given()
                .get(URL_COMPANY + "/" + idCompany)
                .then().log().all()
                .statusCode(200);

        given()
                .get(URL_EMPLOYEE + "/" + idEmployee)
                .then().log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(employeeAPI.getJsonString(idCompany)));
    }

    private void deleteCompany(int idCompany) {
        given().log().all()
                .header("x-client-token", TOKEN)
                .get(URL_COMPANY + "/" + idCompany)
                .then()
                .statusCode(200)
                .body("id", equalTo(idCompany)).log().all();
    }

    private int createNewCompanyApi() {
        int idCompany = given()
                .header("x-client-token", TOKEN)
                .body(newCompany.getJsonString())
                .contentType(ContentType.JSON)
                .when().post(URL_COMPANY)
                .then()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");
        return idCompany;
    }

    private int createNewEmployeeApi(Employee employee, int idCompany) {
        int idEmployee = given().log().all()
                .header("x-client-token", TOKEN)
                .body(employee.getJsonString(idCompany))
                .contentType(ContentType.JSON)
                .when().post(URL_EMPLOYEE)
                .then().log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");
        return idEmployee;
    }
}