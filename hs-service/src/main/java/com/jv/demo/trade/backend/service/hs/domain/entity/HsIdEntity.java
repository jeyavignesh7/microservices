package com.jv.demo.trade.backend.service.hs.domain.entity;

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
public class HsIdEntity implements Serializable {

    private String hsCode;

    private LocalDateTime effDtm;

    private LocalDateTime expDtm;

}
