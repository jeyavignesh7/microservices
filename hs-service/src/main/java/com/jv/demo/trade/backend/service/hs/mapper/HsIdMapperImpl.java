package com.jv.demo.trade.backend.service.hs.mapper;

import com.jv.demo.trade.backend.service.hs.domain.dto.HsIdDto;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsIdEntity;
import com.jv.demo.trade.backend.service.general.mapper.Mapper;
import org.modelmapper.ModelMapper;

public class HsIdMapperImpl implements Mapper<HsIdEntity, HsIdDto> {
    private ModelMapper modelMapper;

    public HsIdMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public HsIdDto mapTo(HsIdEntity HsId) {
        return modelMapper.map(HsId, HsIdDto.class);
    }

    @Override
    public HsIdEntity mapFrom(HsIdDto HsIdDto) {
        return modelMapper.map(HsIdDto, HsIdEntity.class);
    }
}
