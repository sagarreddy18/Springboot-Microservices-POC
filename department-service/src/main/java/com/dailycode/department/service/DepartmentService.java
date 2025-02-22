package com.dailycode.department.service;

import com.dailycode.department.entity.Department;
import com.dailycode.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
    	log.info("Department service saveDepartment method");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
    	log.info("Department service findDepartmentById method");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}
