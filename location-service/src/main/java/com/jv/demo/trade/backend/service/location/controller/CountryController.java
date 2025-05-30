package com.jv.demo.trade.backend.service.location.controller;

import com.jv.demo.trade.backend.service.general.mapper.Mapper;
import com.jv.demo.trade.backend.service.general.util.Constants;
import com.jv.demo.trade.backend.service.location.domain.dto.CountryDto;
import com.jv.demo.trade.backend.service.location.domain.entity.CountryEntity;
import com.jv.demo.trade.backend.service.location.service.CountryService;
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
//@CacheConfig(cacheNames = "country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CreateGeneralLogProducer createGeneralLogProducer;

    @Autowired
    Mapper<CountryEntity, CountryDto> countryMapper;

    @GetMapping(path="/country/{code}")
    @Cacheable(value = "country", keyGenerator = "redisCacheKeyGenerator")
    public ResponseEntity<CountryDto> findById(
            @PathVariable String code) {

        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "CountryController.findByID called:" +code);

        Optional<CountryEntity> optionalCountry = countryService.findById(code);
        if(optionalCountry.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(countryMapper.mapTo(optionalCountry.get()), HttpStatus.FOUND);
        }
    }

    @GetMapping(path="/country")
    @Cacheable(value = "countryList", keyGenerator = "redisCacheKeyGenerator")
    public List<CountryDto> findAll() {
        createGeneralLogProducer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "CountryController.findByAll called:");

        List<CountryEntity> list = countryService.findAll();
        return list.stream()
                .map(countryMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path="/country")
    @CachePut(cacheNames = "country", keyGenerator = "redisCacheKeyGenerator")
    public ResponseEntity<CountryDto> create(@RequestBody CountryDto CountryDto) {
        CountryEntity CountryEntity = countryService.create(countryMapper.mapFrom(CountryDto));
        return new ResponseEntity<>(countryMapper.mapTo(CountryEntity), HttpStatus.CREATED);
    }

    @PutMapping(path="/country")
    public ResponseEntity<CountryDto> update(@RequestBody CountryDto CountryDto) {
        CountryEntity CountryEntity = countryService.update(countryMapper.mapFrom(CountryDto));
        return new ResponseEntity<>(countryMapper.mapTo(CountryEntity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/country")
    //@CacheEvict(cacheNames = "country", key = "#id", beforeInvocation = true)
    @Caching(
            evict = {@CacheEvict(value = "countryList", allEntries = true)},
            put = {@CachePut(value = "country", keyGenerator = "redisCacheKeyGenerator")}
    )
    public ResponseEntity<CountryDto> delete(@RequestBody CountryDto CountryDto) {
        countryService.delete(countryMapper.mapFrom(CountryDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
