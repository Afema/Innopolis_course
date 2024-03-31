package ru.inno.course.dbSevice;

import java.sql.*;

public class DBService {
    public static void main(String[] args) throws SQLException {


        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";
        int employeeId = 0;
        String SQL_SELECT_ALL = "SELECT * FROM employee";
        String SQL_COUNT = "SELECT COUNT(*) FROM employee where last_name = 'Иванова'";
        String SQL_SELECT_BY_ID = "SELECT * FROM employee where id = ?";
        String SQL_INSERT = """
                INSERT INTO employee (is_active, create_timestamp, change_timestamp, first_name, last_name, middle_name, phone, email, birthdate, avatar_url, company_id)
                VALUES(true, now(), now(), 'Галина', 'Иванова', 'Петровна', '20021-111-8963', 'sova@gmail.com', '2000-11-05', null, 2500)
                """;
        String SQL_UPDATE_BY_ID = "UPDATE employee SET is_active = true where id = " + employeeId;
        ;
        String SQL_DELETE_BY_ID = "DELETE FROM employee where id = " + employeeId;
        Connection connection = DriverManager.getConnection(connectionString, user, pass);

        // Добавить сотрудников
        int addedCount = connection.createStatement().executeUpdate(SQL_INSERT);
        System.out.println(addedCount);


        //вывести в консоль всех сотрудников

        ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            System.out.println(id + " - " + first_name);
        }

        //вывести в консоль количество всех сотрудников с фамилией Иванова

        ResultSet dbSize = connection.createStatement().executeQuery(SQL_COUNT);
        dbSize.next();
        System.out.println("всего " + dbSize.getInt("count") + " сотрудников с фамилией Иванова");


        // выбираем сотрудника по id
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
        statement.setInt(1, employeeId);
        ResultSet employeeById = statement.executeQuery();
        while (employeeById.next()) {
            int id = employeeById.getInt("id");
            String first_name = employeeById.getString("first_name");
            boolean hasActive = employeeById.getBoolean("is_active");
            int companyId1 = employeeById.getInt("company_id");
            System.out.println(id + " - " + first_name + " " + hasActive + " " + companyId1);
        }

        // меняем значение 'is_active' на false ля сотрудника с id = 1989
        int updatedCount = connection.createStatement().executeUpdate(SQL_UPDATE_BY_ID);
        System.out.println(updatedCount);

        // удаляем сотрудника с id = 1989
        int deletedCount = connection.createStatement().executeUpdate(SQL_DELETE_BY_ID);
        System.out.println(deletedCount);

        connection.close();
    }
}
//List<Employee> listAPI = given().get("/employee").extract().list(Employee.class);
//assertEquals(dbSize, listAPI.size());