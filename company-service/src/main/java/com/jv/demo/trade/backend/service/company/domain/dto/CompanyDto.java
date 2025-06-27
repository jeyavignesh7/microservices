package com.jv.demo.trade.backend.service.company.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto implements Serializable {

    @NotBlank(message="Company ID cannot be Empty")
    @Size(min=1, max=40, message="Company ID must be between {min} to {max} characters")
    private String companyId;

    @NotNull(message="Company name cannot be Empty")
    @Size(min=1, max=400, message="Company Name must be between {min} to {max} characters")
    private String name;
}
