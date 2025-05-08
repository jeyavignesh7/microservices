package com.jv.demo.tradenet.frontend.service.exchangerate.mapper;

import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.dto.ExcRateIdDto;
import com.jv.demo.tradenet.frontend.service.general.mapper.Mapper;
import org.modelmapper.ModelMapper;

public class ExcRateIdMapperImpl implements Mapper<ExcRateIdEntity, ExcRateIdDto> {
    private ModelMapper modelMapper;

    public ExcRateIdMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public ExcRateIdDto mapTo(ExcRateIdEntity excRateId) {
        return modelMapper.map(excRateId, ExcRateIdDto.class);
    }

    @Override
    public ExcRateIdEntity mapFrom(ExcRateIdDto excRateIdDto) {
        return modelMapper.map(excRateIdDto, ExcRateIdEntity.class);
    }
}
