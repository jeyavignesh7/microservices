package com.jv.demo.trade.backend.service.hs.mapper;

import com.jv.demo.trade.backend.service.hs.domain.dto.HsDto;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsEntity;
import com.jv.demo.trade.backend.service.general.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HsMapperImpl implements Mapper<HsEntity, HsDto> {

    private ModelMapper modelMapper;

    public HsMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public HsDto mapTo(HsEntity Hs) {
        return modelMapper.map(Hs, HsDto.class);
    }

    @Override
    public HsEntity mapFrom(HsDto HsDto) {
        return modelMapper.map(HsDto, HsEntity.class);
    }
}
