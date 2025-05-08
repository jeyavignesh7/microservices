package com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcRateIdEntity implements Serializable {

    private String currCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;

}
