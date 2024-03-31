package ru.inno.course.x_clientsTests.db;

import ru.inno.course.x_clientsTests.model.Employee;

import java.sql.*;

public class EmployeeRepositoryJDBC implements EmployeeRepository {

    private static String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    private static String user = "x_clients_user";
    private static String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";
    String SQL_INSERT_EMPLOYEE = "INSERT INTO employee (\"first_name\",\"last_name\", \"phone\",\"email\", \"company_id\") VALUES(?,?,?,?,?)";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM employee where id = ?";
    String SQL_DELETE_BY_ID = "DELETE FROM employee where id = ?";


    @Override
    public int createEmployeeDB(Employee employee) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString, user, pass);
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getPhone());
        statement.setString(4, employee.getEmail());
        statement.setInt(5, employee.getCompanyId());
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt("id");
    }

    @Override
    public Employee getEmployeeByIdDB(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString, user, pass);
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        Employee employee = new Employee(resultSet.getString("first_name"),
                resultSet.getString("middle_name"),
                resultSet.getString("last_name"),
                resultSet.getInt("company_id"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getBoolean("is_active"));
        employee.setId(resultSet.getInt("id"));
        return employee;
    }

    @Override
    public void deleteEmployeeByIdDB(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString, user, pass);
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }
}
