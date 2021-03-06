package com.fsk.elasticsearch.controller;

import com.fsk.elasticsearch.document.Employee;
import com.fsk.elasticsearch.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public void save(@RequestBody final Employee employee) {
        employeeService.save(employee);
    }


    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") final String id) {
        return employeeService.findById(id);
    }


    @GetMapping("/get-all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
