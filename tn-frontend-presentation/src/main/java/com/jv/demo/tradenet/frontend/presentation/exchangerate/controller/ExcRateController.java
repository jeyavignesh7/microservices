package com.jv.demo.tradenet.frontend.presentation.exchangerate.controller;

import com.jv.demo.tradenet.frontend.presentation.exchangerate.service.ExcRateService;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.dto.ExcRateDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExcRateController {
    private ExcRateService excRateService;

    public ExcRateController(ExcRateService excRateService) {
         this.excRateService = excRateService;
    }

    @GetMapping(path="/excrate/{currCode}")
    public ExcRateDto findById(@PathVariable String currCode, @RequestParam String effDtm, @RequestParam String expDtm) {
        return excRateService.findById(currCode, effDtm, expDtm);
    }

    @GetMapping(path="/excrate")
    public List<ExcRateDto> findAll() {
        return excRateService.findAll();
    }
}
