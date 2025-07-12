package com.jv.demo.trade.backend.service.company.mapper;

import com.jv.demo.trade.backend.service.company.domain.dto.CompanyDto;
import com.jv.demo.trade.backend.service.company.domain.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    //CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    public CompanyEntity mapFrom(CompanyDto companyDto);

    public CompanyDto mapTo(CompanyEntity companyEntity);

    public List<CompanyDto> mapToList(List<CompanyEntity> companyEntityList);
}
