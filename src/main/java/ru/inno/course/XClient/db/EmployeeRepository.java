package ru.inno.course.XClient.db;


import ru.inno.course.XClient.db.model.Employee;

import java.sql.SQLException;

public interface EmployeeRepository {
    int createEmployeeDB(Employee employee) throws SQLException;

    Employee getEmployeeByIdDB(int id) throws SQLException;

    void deleteEmployeeByIdDB(int id) throws SQLException;
}
