package com.fsk.elasticsearch.controller;

import com.fsk.elasticsearch.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;


    @PostMapping("/recreate")
    public void recreateAllIndicies() {
        indexService.recreateIndicies(true);
    }
}
