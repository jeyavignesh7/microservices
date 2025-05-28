package com.jv.demo.trade.frontend.service.exchangerate.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log
@Table(name = "tn_exchange_rate")
public class ExcRateEntity {

    @EmbeddedId
    private ExcRateIdEntity excRateId;

    private Double currUnit;

    private Double excRate;

}
