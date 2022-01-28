package com.fsk.elasticsearch.helper;

import com.fsk.elasticsearch.document.Vehicle;
import com.fsk.elasticsearch.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class VehicleFakeDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleFakeDataService.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final VehicleService vehicleService;

    //id - number - name - date
    public void insertFakeData() {
        vehicleService.index(buildVehicle("1", "AAA-A01", "Audi A4", LocalDate.parse("2013-07-12")));
        vehicleService.index(buildVehicle("2", "AAA-A02", "Audi A5", LocalDate.parse("2021-01-10")));
        vehicleService.index(buildVehicle("3", "AAA-A03", "Audi A6", LocalDate.parse("2020-10-12")));


        vehicleService.index(buildVehicle("4", "VVV-S4", "Volvo S40", LocalDate.parse("2021-10-06")));
        vehicleService.index(buildVehicle("5", "VVV-S6", "Volvo S60", LocalDate.parse("2022-01-20")));
        vehicleService.index(buildVehicle("6", "VVV-S9", "Volvo S90", LocalDate.parse("2019-08-01")));
        vehicleService.index(buildVehicle("7", "VVV-V04", "Volvo V40", LocalDate.parse("2017-04-12")));


        vehicleService.index(buildVehicle("8", "BBB-0M3", "BMW M3", LocalDate.parse("2022-10-12")));
        vehicleService.index(buildVehicle("9", "BBB-0X5", "BMW X5", LocalDate.parse("2022-10-12")));


        vehicleService.index(buildVehicle("10", "MMM-E25", "Mercedes E250", LocalDate.parse("2010-07-03")));
        vehicleService.index(buildVehicle("11", "MMM-C18", "Mercedes C180", LocalDate.parse("2008-05-19")));


        vehicleService.index(buildVehicle("12", "VW-G01", "VW Golf", LocalDate.parse("2007-03-14")));
        vehicleService.index(buildVehicle("13", "VW-P02", "VW Passat", LocalDate.parse("2011-12-01")));
        vehicleService.index(buildVehicle("14", "VW-P03", "VW Polo", LocalDate.parse("2009-01-04")));


        vehicleService.index(buildVehicle("15", "SSB-01", "Skoda SuperB", LocalDate.parse("2022-01-10")));


        vehicleService.index(buildVehicle("16", "RC-01", "Renault Clio", LocalDate.parse("1991-04-08")));
        vehicleService.index(buildVehicle("17", "RM-02", "Renault Megane", LocalDate.parse("2021-04-08")));
        vehicleService.index(buildVehicle("18", "RT-03", "Renault Talisman", LocalDate.parse("2020-01-04")));
    }


    private static Vehicle buildVehicle(final  String id, final String number, final String name, final LocalDate date) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setNumber(number);
        vehicle.setName(name);
        try {
            vehicle.setCreated(date);
        }catch (final Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

        return vehicle;
    }

}
