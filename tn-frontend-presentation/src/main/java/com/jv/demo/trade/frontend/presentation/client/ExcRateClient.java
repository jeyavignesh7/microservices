package com.jv.demo.trade.frontend.presentation.client;

import com.jv.demo.trade.frontend.presentation.exchangerate.domain.dto.ExcRateDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value="excrate-client", url="http://tn-frontend-service-inst1:2222/frontend-service")
//@FeignClient(name="${tn.frontend.service.excrate.host}", path="${tn.frontend.service.excrate.path}")
@FeignClient(name="FRONTEND-SERVICE", path="/frontend-service")
@LoadBalancerClient
public interface ExcRateClient {

    @GetMapping(path="/excrate/{currCode}")
    public ExcRateDto findById(@PathVariable String currCode, @RequestParam String effDtm, @RequestParam String expDtm);

    @GetMapping(path="/excrate")
    public List<ExcRateDto> findAll();
}
