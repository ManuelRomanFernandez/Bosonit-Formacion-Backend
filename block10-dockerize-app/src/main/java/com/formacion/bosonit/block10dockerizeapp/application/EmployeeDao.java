package com.formacion.bosonit.block10dockerizeapp.application;

import com.formacion.bosonit.block10dockerizeapp.domain.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    void insertEmployee(Employee emp);
    void updateEmployee(Employee emp);
    void executeUpdateEmployee(Employee emp);
    public void deleteEmployee(Employee emp);
}
