package com.jv.demo.trade.backend.service.location.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "tn_country")
public class CountryEntity {

    @Id
    private String code;

    private String name;

}
