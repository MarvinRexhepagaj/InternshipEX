package main.java.repository.impl;


import main.java.mapper.EmployeeMapper;
import main.java.model.entity.Employee;
import main.java.util.JdbcConnection;
import main.java.util.Queries;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.err.println("Error");
            return false;
        }
    }

    @Override
    public Boolean save(Employee employee) {
        try (Connection connection = JdbcConnection.connect()) {
            PreparedStatement statement;
            if (exists(employee.getId())) {
                statement = connection.prepareStatement(Queries.UPDATE_EMPLOYEE);
                statement.setInt(8, employee.getId());
                statement.setString(1, employee.getLastName());
                statement.setString(2, employee.getFirstName());
                statement.setString(3, employee.getExtension());
                statement.setString(4, employee.getEmail());
                statement.setString(5, employee.getOfficeCode());
                statement.setInt(6, employee.getReportsTo());
                statement.setString(7, employee.getJobTitle());
            } else {
                statement = connection.prepareStatement(Queries.INSERT_EMPLOYEE);
                statement.setInt(1, employee.getId());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getExtension());
                statement.setString(5, employee.getEmail());
                statement.setString(6, employee.getOfficeCode());
                statement.setInt(7, employee.getReportsTo());
                statement.setString(8, employee.getJobTitle());
            }
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Integer update(Employee employee) {
        int recordsUpdated = 0;
        try (Connection connection = JdbcConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_EMPLOYEE);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getExtension());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getOfficeCode());
            statement.setInt(7, employee.getReportsTo());
            statement.setString(8, employee.getJobTitle());
            recordsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return recordsUpdated;
    }
    //


}
