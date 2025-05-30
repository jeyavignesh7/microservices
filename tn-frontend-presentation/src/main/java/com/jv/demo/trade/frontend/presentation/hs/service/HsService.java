package com.jv.demo.trade.frontend.presentation.hs.service;

import com.jv.demo.trade.frontend.presentation.client.HsClient;
import com.jv.demo.trade.frontend.presentation.hs.dto.HsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HsService {
    private HsClient hsClient;

    public HsService(HsClient hsClient) {
        this.hsClient = hsClient;
    }

    public HsDto findById(String hsCode, String effDtm, String expDtm) {
        return hsClient.findById(hsCode, effDtm, expDtm);
    }

    public List<HsDto> findAll() {
        return hsClient.findAll();
    }
}
