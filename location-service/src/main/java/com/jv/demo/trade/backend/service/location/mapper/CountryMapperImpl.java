package com.jv.demo.trade.backend.service.location.mapper;

import com.jv.demo.trade.backend.service.general.mapper.Mapper;
import com.jv.demo.trade.backend.service.location.domain.dto.CountryDto;
import com.jv.demo.trade.backend.service.location.domain.entity.CountryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements Mapper<CountryEntity, CountryDto> {

    private ModelMapper modelMapper;

    public CountryMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public CountryDto mapTo(CountryEntity Hs) {
        return modelMapper.map(Hs, CountryDto.class);
    }

    @Override
    public CountryEntity mapFrom(CountryDto CountryDto) {
        return modelMapper.map(CountryDto, CountryEntity.class);
    }
}
