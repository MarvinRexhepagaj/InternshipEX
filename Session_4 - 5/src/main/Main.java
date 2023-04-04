package main;


import main.java.model.entity.Employee;
import main.java.repository.Repository;
import main.java.repository.impl.EmployeeRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Repository<Employee, Integer> repository = new EmployeeRepository();
        Scanner scanner = new Scanner(System.in);

        // find all employees
        List<Employee> employees = repository.findAll();
        for (Employee employee : employees) {
            System.out.println(employeeToString(employee));
        }

        // find an employee by ID
        System.out.println("Enter the ID of the employee to find: ");
        int id = scanner.nextInt();
        Employee employee = repository.findById(id);
        if (employee != null) {
            System.out.println(employeeToString(employee));
        } else {
            System.out.println("Employee not found.");
        }

        // check if an employee exists by ID
        System.out.println("Enter the ID of the employee to check: ");
        id = scanner.nextInt();
        boolean exists = repository.exists(id);
        if (exists) {
            System.out.println("Employee exists.");
        } else {
            System.out.println("Employee does not exist.");
        }

        // save an employee
        System.out.println("Enter the details of the employee to save:");
        Employee newEmployee = new Employee();
        System.out.println("ID: ");
        newEmployee.setId(scanner.nextInt());
        System.out.println("Last Name: ");
        newEmployee.setLastName(scanner.next());
        System.out.println("First Name: ");
        newEmployee.setFirstName(scanner.next());
        System.out.println("Extension: ");
        newEmployee.setExtension(scanner.next());
        System.out.println("Email: ");
        newEmployee.setEmail(scanner.next());
        System.out.println("Office Code: ");
        newEmployee.setOfficeCode(scanner.next());
        System.out.println("Reports To: ");
        newEmployee.setReportsTo(scanner.nextInt());
        System.out.println("Job Title: ");
        newEmployee.setJobTitle(scanner.next());


        boolean saved = repository.save(newEmployee);
        if (saved) {
            System.out.println("Employee saved successfully.");
        } else {
            System.out.println("Error saving employee.");
        }

        // update an employee
        System.out.println("Enter the ID of the employee to update:");
        id = scanner.nextInt();
        Employee existingEmployee = repository.findById(id);
        if (existingEmployee != null) {
            System.out.println("Enter the details of the employee to update:");
            System.out.println("Last Name: ");
            existingEmployee.setLastName(scanner.next());
            System.out.println("First Name: ");
            existingEmployee.setFirstName(scanner.next());
            System.out.println("Extension: ");
            existingEmployee.setExtension(scanner.next());
            System.out.println("Email: ");
            existingEmployee.setEmail(scanner.next());
            System.out.println("Office Code: ");
            existingEmployee.setOfficeCode(scanner.next());
            System.out.println("Job Title: ");
            existingEmployee.setJobTitle(scanner.next());
            System.out.println("Reports To: ");
            existingEmployee.setReportsTo(scanner.nextInt());


            int updated = repository.update(existingEmployee);
            if (updated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Error updating employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }

        scanner.close();
    }


    public static String employeeToString(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(employee.getId()).append("\n")
                .append("Last Name: ").append(employee.getLastName()).append("\n")
                .append("First Name: ").append(employee.getFirstName()).append("\n")
                .append("Extension: ").append(employee.getExtension()).append("\n")
                .append("Email: ").append(employee.getEmail()).append("\n")
                .append("Office Code: ").append(employee.getOfficeCode()).append("\n")
                .append("Reports To: ").append(employee.getReportsTo()).append("\n")
                .append("Job Title: ").append(employee.getJobTitle()).append("\n");
        return sb.toString();
    }
}