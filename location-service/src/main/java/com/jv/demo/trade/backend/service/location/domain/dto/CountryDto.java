package com.jv.demo.trade.backend.service.location.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto implements Serializable {
    private String code;

    private String name;

}
