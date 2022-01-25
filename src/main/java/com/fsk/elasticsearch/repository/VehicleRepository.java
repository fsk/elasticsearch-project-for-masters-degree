package com.fsk.elasticsearch.repository;

import com.fsk.elasticsearch.document.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface VehicleRepository extends ElasticsearchRepository<Vehicle, String> {

    Page<Vehicle> findAll();

}
