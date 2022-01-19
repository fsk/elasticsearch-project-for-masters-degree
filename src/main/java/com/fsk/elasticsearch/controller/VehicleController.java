package com.fsk.elasticsearch.controller;

import com.fsk.elasticsearch.document.Vehicle;
import com.fsk.elasticsearch.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public void index(@RequestBody final Vehicle vehicle) {
        vehicleService.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable("id") final String id) {
        return vehicleService.getById(id);
    }
}
