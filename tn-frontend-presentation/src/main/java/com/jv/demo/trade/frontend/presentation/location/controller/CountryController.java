package com.jv.demo.trade.frontend.presentation.location.controller;

import com.jv.demo.trade.frontend.presentation.location.dto.CountryDto;
import com.jv.demo.trade.frontend.presentation.location.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
         this.countryService = countryService;
    }

    @GetMapping(path="/country/{code}")
    public CountryDto findById(@PathVariable String code) {
        return countryService.findById(code);
    }

    @GetMapping(path="/country")
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }
}
