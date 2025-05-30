package com.jv.demo.trade.backend.service.hs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HsDto implements Serializable {
    private HsIdDto hsIdDto;

    private String description;

}
