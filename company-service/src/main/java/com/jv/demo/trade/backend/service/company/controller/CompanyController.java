package com.jv.demo.trade.backend.service.company.controller;

import com.jv.demo.trade.backend.service.company.domain.dto.CompanyDto;
import com.jv.demo.trade.backend.service.company.general.Constants;
import com.jv.demo.trade.backend.service.company.mapper.CompanyMapper;
import com.jv.demo.trade.backend.service.company.msgbroker.producer.CreateGeneralLogProducer;
import com.jv.demo.trade.backend.service.company.service.CompanyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    private CompanyMapper companyMapper;

    @Autowired
    private CreateGeneralLogProducer producer;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    /*
    @GetMapping(path="/{id}")
    @Cacheable(cacheNames = "company", key="#id")
    public ResponseEntity<CompanyDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(companyMapper.mapTo(companyService.findById(id).get()), HttpStatus.OK);
    }
    */

    @GetMapping("/{id}")
    @Cacheable(cacheNames = "company", key="#id")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto findById(@PathVariable String id) {
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company findById()");
        return companyMapper.mapTo(companyService.findById(id).get());
    }

    @GetMapping("")
    @Cacheable(cacheNames = "companyList")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> findAll() {
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company findAll()");
        return companyMapper.mapTo(companyService.findAll());
    }

    @PostMapping("")
    @Caching(
            //cacheable = @Cacheable(cacheNames = "company", key="#companyDto.companyId"), //It prevents the duplicate validation
            evict = @CacheEvict(cacheNames="companyList", allEntries = true),
            put = @CachePut(cacheNames="company", key="#companyDto.companyId")
    )
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto create(@Valid @RequestBody CompanyDto companyDto) {
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company create()");
        return companyMapper.mapTo(companyService.create(companyMapper.mapFrom(companyDto)));
    }

    @PutMapping("")
    @Caching(
            evict = {@CacheEvict(cacheNames="companyList", allEntries = true)/*,
                    @CacheEvict(cacheNames="company", key="#companyDto.companyId")*/}, //CachePut will be called upon successful update, so no evict required
            put = @CachePut(cacheNames="company", key="#companyDto.companyId")
    )
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto update(@Valid @RequestBody CompanyDto companyDto) {
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company update()");
        return companyMapper.mapTo(companyService.update(companyMapper.mapFrom(companyDto)));
    }

    @DeleteMapping("")
    @Caching(
            evict = {@CacheEvict(cacheNames="companyList", allEntries=true),
            @CacheEvict(cacheNames="company", key="#companyDto.companyId")}
    )
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestBody CompanyDto companyDto){
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company deleteById()");
        companyService.deleteById(companyDto.getCompanyId());
        return "Record Deleted Successfully";
    }

    @GetMapping("/{pageNo}/{pageSize}/{sortBy}/{sortDirection}")
    public List<CompanyDto> findAllWithPagination(
            @PathVariable int pageNo,
            @PathVariable @Max(5) int pageSize,
            @PathVariable String sortBy,
            @PathVariable @Pattern(regexp="asc|desc|ASC|DESC") String sortDirection){
        return companyMapper.mapTo(companyService.findAllWithPagination(pageNo, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/search/{pageNo}/{pageSize}/{sortBy}/{sortDirection}")
    public List<CompanyDto> findByNameWithPagination(
            @RequestParam String name,
            @PathVariable int pageNo,
            @PathVariable @Max(5) int pageSize,
            @PathVariable String sortBy,
            @PathVariable @Pattern(regexp="asc|desc|ASC|DESC") String sortDirection){
        return companyMapper.mapTo(companyService.findByNameWithPagination(name, pageNo, pageSize, sortBy, sortDirection));
    }

    @GetMapping("circuitbreaker")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="countryCircuitBreaker", fallbackMethod="cbFallbackMethod")
    public String verifyCircuitBreaker() {
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company verifyCircuitBreaker()");
        throw new RuntimeException("Time out (Circuit Breaker testing)");
    }

    public String cbFallbackMethod(Throwable t){
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company cbFallbackMethod()");
        return "CircuitBreaker fallbackMethod called: " + t.getMessage();
    }

    @GetMapping("retry")
    @ResponseStatus(HttpStatus.OK)
    @Retry(name="countryRetry", fallbackMethod = "retryMethod")
    public String verifyRetry(){
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company verifyRetry()");
        throw new RuntimeException("Retry failed (Retry testing)");
    }

    public String retryMethod(Throwable t){
        producer.sendMessage(Constants.TOPIC_MSG_FROM_TRADER, "Company retryMethod()");
        return "Retry Method called: " + t.getMessage();
    }

}
