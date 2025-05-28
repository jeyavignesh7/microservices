package com.jv.demo.trade.frontend.service.exchangerate.controller;

import com.jv.demo.trade.frontend.service.exchangerate.domain.dto.ExcRateDto;
import com.jv.demo.trade.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.trade.frontend.service.exchangerate.service.ExcRateService;
import com.jv.demo.trade.frontend.service.general.util.Constants;
import com.jv.demo.trade.frontend.service.general.util.DateUtil;
import com.jv.demo.trade.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import com.jv.demo.trade.frontend.service.general.mapper.Mapper;
import com.jv.demo.trade.frontend.service.msgbroker.producer.CreateGeneralLogProducer;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CommonsLog
//@CacheConfig(cacheNames = "excrate")
public class ExcRateController {

    @Autowired
    private ExcRateService excRateService;

    @Autowired
    private CreateGeneralLogProducer createGeneralLogProducer;

    @Autowired
    Mapper<ExcRateEntity, ExcRateDto> excRateMapper;

    @GetMapping(path="/excrate/{currCode}")
    @Cacheable(value = "excrate", keyGenerator = "redisCacheKeyGenerator")
    public ResponseEntity<ExcRateDto> findById(
            @PathVariable String currCode,
            @RequestParam(value = "effDtm") String effDtm,
            @RequestParam(value = "expDtm") String expDtm) {

        ExcRateIdEntity excRateId = ExcRateIdEntity.builder()
                .currCode(currCode.toUpperCase())
                .effDtm(DateUtil.convertToLocalDtm(effDtm))
                .expDtm(DateUtil.convertToLocalDtm(expDtm))
                .build();

        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "ExcRateController.findByID called:" +excRateId);

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
    @Cacheable(value = "excrateList", keyGenerator = "redisCacheKeyGenerator")
    public List<ExcRateDto> findAll() {
        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "ExcRateController.findByAll called:");

        List<ExcRateEntity> list = excRateService.findAll();
        return list.stream()
                .map(excRateMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path="/excrate")
    @CachePut(cacheNames = "excrate", keyGenerator = "redisCacheKeyGenerator")
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
    //@CacheEvict(cacheNames = "excrate", key = "#id", beforeInvocation = true)
    @Caching(
            evict = {@CacheEvict(value = "excrateList", allEntries = true)},
            put = {@CachePut(value = "excrate", keyGenerator = "redisCacheKeyGenerator")}
    )
    public ResponseEntity<ExcRateDto> delete(@RequestBody ExcRateDto excRateDto) {
        excRateService.delete(excRateMapper.mapFrom(excRateDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
