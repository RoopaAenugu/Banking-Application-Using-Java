package com.wavemaker.employee.main;

import com.wavemaker.employee.model.Address;
import com.wavemaker.employee.model.Department;
import com.wavemaker.employee.model.Employee;
import com.wavemaker.employee.service.EmployeeService;
import com.wavemaker.employee.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static EmployeeService employeeService;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. In Memory Storage\n2. In File Storage");
        System.out.print("Enter your option to storage: ");
        int storageOption = scanner.nextInt();
        employeeService = new EmployeeServiceImpl(storageOption);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Get Employee by ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    getEmployeeById(scanner);
                    break;
                case 3:
                    getAllEmployees();
                    break;
                case 4:
                    updateEmployee(scanner);
                    break;
                case 5:
                    deleteEmployee(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Gender ");
        String gender = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter Department ID: ");
        int deptId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();

        System.out.print("Enter Address ID: ");
        int addressId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Address State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Address City: ");
        String city = scanner.nextLine();
        System.out.print("Enter Address Pincode: ");
        int pincode = scanner.nextInt();
        Employee employee = new Employee();
        employee.setEmpId(id);
        employee.setEmpName(name);
        employee.setAge(age);
        employee.setGender(gender);
        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(deptName);
        Address address = new Address();
        address.setAddressId(addressId);
        address.setState(state);
        address.setCity(city);
        address.setPincode(pincode);
        employee.setDepartment(department);
        employee.setAddress(address);


        boolean added = employeeService.addEmployee(employee);
        if (added) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void getEmployeeById(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            System.out.println("Employee Details: " + employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("All Employees:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Gender: ");
            String gender = scanner.nextLine();

            scanner.nextLine();
            System.out.print("Enter New Department Name: ");
            String deptName = scanner.nextLine();

            scanner.nextLine();
            System.out.print("Enter New Address State: ");
            String state = scanner.nextLine();
            System.out.print("Enter New Address City: ");
            String city = scanner.nextLine();
            System.out.print("Enter New Address Pincode: ");
            int pincode = scanner.nextInt();

            employee = new Employee();
            employee.setEmpId(id);
            employee.setEmpName(name);
            employee.setAge(age);
            employee.setGender(gender);
            Department department = new Department();
            department.setDeptName(deptName);
            Address address = new Address();
            address.setState(state);
            address.setCity(city);
            address.setPincode(pincode);
            employee.setDepartment(department);
            employee.setAddress(address);

            boolean updated = employeeService.updateEmployee(employee);
            if (updated) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Failed to update employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            boolean deleted = employeeService.deleteEmployee(employee);
            if (deleted) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Failed to delete employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}
