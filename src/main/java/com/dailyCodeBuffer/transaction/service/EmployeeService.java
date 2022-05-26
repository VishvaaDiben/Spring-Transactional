package com.dailyCodeBuffer.transaction.service;

import com.dailyCodeBuffer.transaction.entity.Department;
import com.dailyCodeBuffer.transaction.entity.Employee;
import com.dailyCodeBuffer.transaction.repository.DepartmentRepository;
import com.dailyCodeBuffer.transaction.repository.EmployeeRepository;
import com.dailyCodeBuffer.transaction.vo.EmployeeRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public String saveEmployee(EmployeeRequestVO employeeRequestVO) {

        Department department = new Department();
        department.setDepartmentName(employeeRequestVO.getDepartmentName());
        department.setDepartmentCode(employeeRequestVO.getDepartmentName());

        Long departmentId = departmentRepository.save(department)
                .getDepartmentId();

        Employee employee = null;

        employee.setEmpName(employeeRequestVO.getEmpName());
        employee.setEmail(employeeRequestVO.getEmail());
        employee.setDepartmentId(departmentId);

        employeeRepository.save(employee);

        return  "Employee is saved with Employee ID :" + employee.getEmployeeId();

    }
}
