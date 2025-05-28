package com.jv.demo.trade.frontend.service.exchangerate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcRateIdDto implements Serializable {
    private String currCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;
}
