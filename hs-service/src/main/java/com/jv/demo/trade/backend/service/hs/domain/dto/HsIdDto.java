package com.jv.demo.trade.backend.service.hs.domain.dto;

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
public class HsIdDto implements Serializable {
    private String hsCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;
}
