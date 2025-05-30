package com.jv.demo.trade.backend.service.hs.controller;

import com.jv.demo.trade.backend.service.hs.domain.dto.HsDto;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsEntity;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsIdEntity;
import com.jv.demo.trade.backend.service.hs.service.HsService;
import com.jv.demo.trade.backend.service.general.mapper.Mapper;
import com.jv.demo.trade.backend.service.general.util.Constants;
import com.jv.demo.trade.backend.service.general.util.DateUtil;
import com.jv.demo.trade.backend.service.msgbroker.producer.CreateGeneralLogProducer;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CommonsLog
//@CacheConfig(cacheNames = "hs")
public class HsController {

    @Autowired
    private HsService hsService;

    @Autowired
    private CreateGeneralLogProducer createGeneralLogProducer;

    @Autowired
    Mapper<HsEntity, HsDto> hsMapper;

    @GetMapping(path="/hs/{currCode}")
    @Cacheable(value = "hs", keyGenerator = "redisCacheKeyGenerator")
    public ResponseEntity<HsDto> findById(
            @PathVariable String currCode,
            @RequestParam(value = "effDtm") String effDtm,
            @RequestParam(value = "expDtm") String expDtm) {

        HsIdEntity hsId = HsIdEntity.builder()
                .hsCode(currCode.toUpperCase())
                .effDtm(DateUtil.convertToLocalDtm(effDtm))
                .expDtm(DateUtil.convertToLocalDtm(expDtm))
                .build();

        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "HsController.findByID called:" +hsId);

        log.info(String.format("%s %s", effDtm, expDtm));
        log.info(String.format("%s %s", DateUtil.convertToLocalDtm(effDtm), DateUtil.convertToLocalDtm(expDtm)));

        Optional<HsEntity> optionalHs = hsService.findById(hsId);
        if(optionalHs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hsMapper.mapTo(optionalHs.get()), HttpStatus.FOUND);
        }
    }

    @GetMapping(path="/hs")
    @Cacheable(value = "hsList", keyGenerator = "redisCacheKeyGenerator")
    public List<HsDto> findAll() {
        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "HsController.findByAll called:");

        List<HsEntity> list = hsService.findAll();
        return list.stream()
                .map(hsMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path="/hs")
    @CachePut(cacheNames = "hs", keyGenerator = "redisCacheKeyGenerator")
    public ResponseEntity<HsDto> create(@RequestBody HsDto HsDto) {
        HsEntity HsEntity = hsService.create(hsMapper.mapFrom(HsDto));
        return new ResponseEntity<>(hsMapper.mapTo(HsEntity), HttpStatus.CREATED);
    }

    @PutMapping(path="/hs")
    public ResponseEntity<HsDto> update(@RequestBody HsDto HsDto) {
        HsEntity HsEntity = hsService.update(hsMapper.mapFrom(HsDto));
        return new ResponseEntity<>(hsMapper.mapTo(HsEntity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/hs")
    //@CacheEvict(cacheNames = "hs", key = "#id", beforeInvocation = true)
    @Caching(
            evict = {@CacheEvict(value = "hsList", allEntries = true)},
            put = {@CachePut(value = "hs", keyGenerator = "redisCacheKeyGenerator")}
    )
    public ResponseEntity<HsDto> delete(@RequestBody HsDto HsDto) {
        hsService.delete(hsMapper.mapFrom(HsDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
