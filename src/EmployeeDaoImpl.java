import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDaoImpl implements EmployeeDao{

    Connection connection = ConnectionFactory.getConnection();

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee saved.");
        else
            System.out.println("Oops! Something went wrong, please try again.");
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "update employee set name=?, email=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getId());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee updated.");
        else
            System.out.println("Oops! Something went wrong, please try again.");

    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee deleted.");
        else
            System.out.println("Oops! Something went wrong, please try again.");
    }


    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select * from employee";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "select * from employee where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        else
            return null;
    }
}
