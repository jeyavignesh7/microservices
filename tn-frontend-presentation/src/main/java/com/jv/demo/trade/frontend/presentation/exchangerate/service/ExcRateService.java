package com.jv.demo.trade.frontend.presentation.exchangerate.service;

import com.jv.demo.trade.frontend.presentation.client.ExcRateClient;
import com.jv.demo.trade.frontend.presentation.exchangerate.domain.dto.ExcRateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcRateService {
    private ExcRateClient excRateClient;

    public ExcRateService(ExcRateClient excRateClient) {
        this.excRateClient = excRateClient;
    }

    public ExcRateDto findById(String currCode, String effDtm, String expDtm) {
        return excRateClient.findById(currCode, effDtm, expDtm);
    }

    public List<ExcRateDto> findAll() {
        return excRateClient.findAll();
    }
}
