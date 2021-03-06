package com.fsk.elasticsearch.repository;

import com.fsk.elasticsearch.document.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

    Page<Employee> findAll();
}
