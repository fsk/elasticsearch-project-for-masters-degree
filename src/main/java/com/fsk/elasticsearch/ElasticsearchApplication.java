package com.fsk.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsk.elasticsearch.document.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	public ElasticsearchApplication() {

		Vehicle vehicle = new Vehicle();

		ObjectMapper mapper = JsonMapper.builder()
				.addModule(new JavaTimeModule())
				.build();

		mapper.findAndRegisterModules();

		try {
			mapper.writeValueAsString(vehicle);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
