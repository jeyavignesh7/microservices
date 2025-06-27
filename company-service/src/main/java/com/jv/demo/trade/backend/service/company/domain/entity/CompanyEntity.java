package com.jv.demo.trade.backend.service.company.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tn_company")
@Data
public class CompanyEntity {
    @Id
    private String companyId;

    private String name;

}
