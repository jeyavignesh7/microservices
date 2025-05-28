package com.jv.demo.trade.frontend.service.exchangerate.mapper;

import com.jv.demo.trade.frontend.service.exchangerate.domain.dto.ExcRateDto;
import com.jv.demo.trade.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.trade.frontend.service.general.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExcRateMapperImpl implements Mapper<ExcRateEntity, ExcRateDto> {

    private ModelMapper modelMapper;

    public ExcRateMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public ExcRateDto mapTo(ExcRateEntity excRate) {
        return modelMapper.map(excRate, ExcRateDto.class);
    }

    @Override
    public ExcRateEntity mapFrom(ExcRateDto excRateDto) {
        return modelMapper.map(excRateDto, ExcRateEntity.class);
    }
}
