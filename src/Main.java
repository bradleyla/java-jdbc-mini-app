import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Employee Dao
        EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while(flag) {
            System.out.println("*************************");
            System.out.println("Select from the options below");
            System.out.println("*************************");
            System.out.println("PRESS 1: Add Employee");
            System.out.println("PRESS 2: Update Employee");
            System.out.println("PRESS 3: Delete Employee");
            System.out.println("PRESS 4: Get All Employees");
            System.out.println("PRESS 5: Get Employee by Id");
            System.out.println("PRESS 6: Exit");

            int input = scanner.nextInt();
            switch (input) {
                case 1: {
                    // add
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    Employee employee = new Employee();
                    employee.setName(name);
                    employee.setEmail(email);
                    employeeDao.addEmployee(employee);
                    break;
                }
                case 2: {
                    // update
                    System.out.print("Enter employee id you want to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter New Email: ");
                    String newEmail = scanner.next();
                    Employee employee = new Employee();
                    employee.setId(id);
                    employee.setName(newName);
                    employee.setEmail(newEmail);
                    employeeDao.updateEmployee(employee);
                    break;
                }
                case 3: {
                    // delete
                    System.out.print("Enter employee id you want to delete: ");
                    int id = scanner.nextInt();
                    employeeDao.deleteEmployee(id);
                    break;
                }
                case 4: {
                    // get all employees
                    List<Employee> employees = employeeDao.getEmployees();
                    if(!(employees.isEmpty())) {
                        for (Employee employee : employees) {
                            System.out.println("Id: " + employee.getId() + ", Name: " + employee.getName() + ", Email: " + employee.getEmail());
                        }
                    } else
                        System.out.println("There are no employees in the database. Please add one or more employees and try again.");
                    break;
                }
                case 5: {
                    // get employee by id
                    System.out.print("Enter employee id you want to find: ");
                    int id = scanner.nextInt();
                    try {
                        Employee idEmployee = employeeDao.getEmployeeById(id);
                        System.out.println("Id: " + idEmployee.getId() + ", Name: " + idEmployee.getName() + ", Email: " + idEmployee.getEmail());
                    } catch (NullPointerException e) {
                        System.out.println("You've entered an id that is not available in the database. Please try again.");
                    }
                    break;
                }
                case 6: {
                    // exit
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Please select an option from 1 to 6.");
                }
            }
        }
    }
}