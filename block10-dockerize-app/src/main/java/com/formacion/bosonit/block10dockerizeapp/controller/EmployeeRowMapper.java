package com.formacion.bosonit.block10dockerizeapp.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.formacion.bosonit.block10dockerizeapp.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getString("employeeId"));
        emp.setEmployeeName(rs.getString("employeeName"));
        emp.setEmployeeEmail(rs.getString("employeeEmail"));
        emp.setEmployeeAddress(rs.getString("employeeAddress"));

        return emp;
    }
}
