package com.jv.demo.trade.frontend.service.exchangerate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcRateDto implements Serializable {
    private ExcRateIdDto excRateIdDto;

    private Double currUnit;

    private Double excRate;

}
