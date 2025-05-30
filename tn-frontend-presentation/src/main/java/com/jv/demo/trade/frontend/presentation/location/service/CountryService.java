package com.jv.demo.trade.frontend.presentation.location.service;

import com.jv.demo.trade.frontend.presentation.client.CountryClient;
import com.jv.demo.trade.frontend.presentation.location.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryClient countryClient;

    public CountryService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public CountryDto findById(String code) {
        return countryClient.findById(code);
    }

    public List<CountryDto> findAll() {
        return countryClient.findAll();
    }
}
