package com.jv.demo.trade.frontend.presentation.hs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HsDto {
    private HsIdDto hsIdDto;

    private String description;


}
