package com.jv.demo.trade.frontend.presentation.client;

import com.jv.demo.trade.frontend.presentation.location.dto.CountryDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value="country-client", url="http://tn-frontend-service-inst1:2222/frontend-service")
//@FeignClient(name="${tn.frontend.service.country.host}", path="${tn.frontend.service.country.path}")
@FeignClient(name="LOCATION-SERVICE", path="/location-service")
@LoadBalancerClient
public interface CountryClient {

    @GetMapping(path="/country/{code}")
    public CountryDto findById(@PathVariable String code);

    @GetMapping(path="/country")
    public List<CountryDto> findAll();
}
