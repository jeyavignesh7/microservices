package com.jv.demo.trade.frontend.presentation.hs.controller;

import com.jv.demo.trade.frontend.presentation.hs.dto.HsDto;
import com.jv.demo.trade.frontend.presentation.hs.service.HsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HsController {
    private HsService hsService;

    public HsController(HsService hsService) {
         this.hsService = hsService;
    }

    @GetMapping(path="/hs/{hsCode}")
    public HsDto findById(@PathVariable String hsCode, @RequestParam String effDtm, @RequestParam String expDtm) {
        return hsService.findById(hsCode, effDtm, expDtm);
    }

    @GetMapping(path="/hs")
    public List<HsDto> findAll() {
        return hsService.findAll();
    }
}
