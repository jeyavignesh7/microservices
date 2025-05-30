package com.jv.demo.trade.frontend.presentation.client;

import com.jv.demo.trade.frontend.presentation.hs.dto.HsDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value="hs-client", url="http://tn-frontend-service-inst1:2222/frontend-service")
//@FeignClient(name="${tn.frontend.service.hs.host}", path="${tn.frontend.service.hs.path}")
@FeignClient(name="HS-SERVICE", path="/hs-service")
@LoadBalancerClient
public interface HsClient {

    @GetMapping(path="/hs/{hsCode}")
    public HsDto findById(@PathVariable String hsCode, @RequestParam String effDtm, @RequestParam String expDtm);

    @GetMapping(path="/hs")
    public List<HsDto> findAll();
}
