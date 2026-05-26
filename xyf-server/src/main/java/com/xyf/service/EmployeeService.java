package com.xyf.service;

import com.xyf.dto.EmployeeDTO;
import com.xyf.dto.EmployeeLoginDTO;
import com.xyf.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
}
