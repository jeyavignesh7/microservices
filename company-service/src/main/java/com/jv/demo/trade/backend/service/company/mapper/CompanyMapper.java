package com.jv.demo.trade.backend.service.company.mapper;

import com.jv.demo.trade.backend.service.company.domain.dto.CompanyDto;
import com.jv.demo.trade.backend.service.company.domain.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    public CompanyEntity mapFrom(CompanyDto companyDto);

    public CompanyDto mapTo(CompanyEntity companyEntity);

    public List<CompanyDto> mapTo(List<CompanyEntity> companyEntityList);
}
