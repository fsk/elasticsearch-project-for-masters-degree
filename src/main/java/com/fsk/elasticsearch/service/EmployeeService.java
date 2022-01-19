package com.fsk.elasticsearch.service;

import com.fsk.elasticsearch.document.Employee;
import com.fsk.elasticsearch.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(final Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> findById(final String id) {
        return Optional.of(employeeRepository.findById(id).get());

    }
}
