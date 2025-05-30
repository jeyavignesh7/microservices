package com.jv.demo.trade.backend.service.hs.domain.entity;

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
@Table(name = "tn_hs")
public class HsEntity {

    @EmbeddedId
    private HsIdEntity hsId;

    private String description;

}
