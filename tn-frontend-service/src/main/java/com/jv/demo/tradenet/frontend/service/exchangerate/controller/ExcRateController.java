package com.jv.demo.tradenet.frontend.service.exchangerate.controller;

import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.dto.ExcRateDto;
import com.jv.demo.tradenet.frontend.service.exchangerate.service.ExcRateService;
import com.jv.demo.tradenet.frontend.service.general.mapper.Mapper;
import com.jv.demo.tradenet.frontend.service.general.util.DateUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CommonsLog
public class ExcRateController {

    @Autowired
    private ExcRateService excRateService;

    @Autowired
    Mapper<ExcRateEntity, ExcRateDto> excRateMapper;

    @GetMapping(path="/excrate/{currCode}")
    public ResponseEntity<ExcRateDto> findById(
            @PathVariable String currCode,
            @RequestParam(value = "effDtm") String effDtm,
            @RequestParam(value = "expDtm") String expDtm) {

        ExcRateIdEntity excRateId = ExcRateIdEntity.builder()
                .currCode(currCode.toUpperCase())
                .effDtm(DateUtil.convertToLocalDtm(effDtm))
                .expDtm(DateUtil.convertToLocalDtm(expDtm))
                .build();
        log.info(String.format("%s %s", effDtm, expDtm));
        log.info(String.format("%s %s", DateUtil.convertToLocalDtm(effDtm), DateUtil.convertToLocalDtm(expDtm)));

        Optional<ExcRateEntity> optionalExcRate = excRateService.findById(excRateId);
        if(optionalExcRate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(excRateMapper.mapTo(optionalExcRate.get()), HttpStatus.FOUND);
        }
    }

    @GetMapping(path="/excrate")
    public List<ExcRateDto> findAll() {
        List<ExcRateEntity> list = excRateService.findAll();
        return list.stream()
                .map(excRateMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path="/excrate")
    public ResponseEntity<ExcRateDto> create(@RequestBody ExcRateDto excRateDto) {
        ExcRateEntity excRateEntity = excRateService.create(excRateMapper.mapFrom(excRateDto));
        return new ResponseEntity<>(excRateMapper.mapTo(excRateEntity), HttpStatus.CREATED);
    }

    @PutMapping(path="/excrate")
    public ResponseEntity<ExcRateDto> update(@RequestBody ExcRateDto excRateDto) {
        ExcRateEntity excRateEntity = excRateService.update(excRateMapper.mapFrom(excRateDto));
        return new ResponseEntity<>(excRateMapper.mapTo(excRateEntity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/excrate")
    public ResponseEntity<ExcRateDto> delete(@RequestBody ExcRateDto excRateDto) {
        excRateService.delete(excRateMapper.mapFrom(excRateDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
