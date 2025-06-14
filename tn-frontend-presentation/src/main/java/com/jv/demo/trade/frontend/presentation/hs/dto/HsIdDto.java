package com.jv.demo.trade.frontend.presentation.hs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HsIdDto {
    private String hsCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;
}
