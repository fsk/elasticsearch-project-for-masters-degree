package com.fsk.elasticsearch.controller;

import com.fsk.elasticsearch.document.Vehicle;
import com.fsk.elasticsearch.helper.VehicleFakeDataService;
import com.fsk.elasticsearch.search.SearchRequestDTO;
import com.fsk.elasticsearch.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleFakeDataService vehicleFakeDataService;

    @GetMapping("/vehicle-list")
    public List<Vehicle> getVehicleList() {
        return vehicleService.getVehicleList();
    }

    @PostMapping
    public void index(@RequestBody final Vehicle vehicle) {
        vehicleService.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable("id") final String id) {
        return vehicleService.getById(id);
    }

    @PostMapping("/search")
    public List<Vehicle> search(@RequestBody final SearchRequestDTO dto) {
        return vehicleService.search(dto);
    }


    @GetMapping("/search/{date}")
    public List<Vehicle> getAllVehicleCreatedSince(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date
    ) {
        return vehicleService.getAllVehicleCreatedSince(date);
    }



    @PostMapping("/search-created-since/{date}")
    public List<Vehicle> searchCreatedSince(@RequestBody final SearchRequestDTO dto,
                                            @PathVariable
                                            @DateTimeFormat(pattern = "yyyy-MM-dd")
                                            final Date date) {

        return vehicleService.searchCreatedSince(dto, date);

    }


    @PostMapping("/insert-fake-data")
    public void insertFakeData() {
        vehicleFakeDataService.insertFakeData();
    }


}
