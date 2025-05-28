package com.jv.demo.trade.frontend.presentation.exchangerate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcRateDto {
    private ExcRateIdDto excRateIdDto;

    private Double currUnit;

    private Double excRate;

}
