package com.wavemaker.employee.repository.Impl;

import com.wavemaker.employee.model.Employee;
import com.wavemaker.employee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryInMemoryImpl implements EmployeeRepository {
    private static Map<Integer, Employee> employeeMap=new HashMap<Integer, Employee>();


    @Override
    public Employee getEmployeeById(int empId) {
        return employeeMap.get(empId);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employeeMap.containsKey(employee.getEmpId())) {
            return false; // Employee with this ID already exists
        }
        employeeMap.put(employee.getEmpId(), employee); // Add new employee
        return true;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());


    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (!employeeMap.containsKey(employee.getEmpId())) {
            return false; 
        }
        employeeMap.put(employee.getEmpId(), employee); // Update existing employee
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return employeeMap.remove(employee.getEmpId()) != null;
    }

}
