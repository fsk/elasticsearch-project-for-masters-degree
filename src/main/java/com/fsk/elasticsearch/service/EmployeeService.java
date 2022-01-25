package com.fsk.elasticsearch.service;

import com.fsk.elasticsearch.document.Employee;
import com.fsk.elasticsearch.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(final Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findById(final String id) {

        return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().getContent();
    }
}
