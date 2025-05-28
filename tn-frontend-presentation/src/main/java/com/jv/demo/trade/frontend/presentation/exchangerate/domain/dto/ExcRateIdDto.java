package com.jv.demo.trade.frontend.presentation.exchangerate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcRateIdDto {
    private String currCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;
}
