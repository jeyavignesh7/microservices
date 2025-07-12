package com.jv.demo.trade.backend.service.authapi.mapper;

import com.jv.demo.trade.backend.service.authapi.user.domain.dto.UserAuthDto;
import com.jv.demo.trade.backend.service.authapi.user.domain.entity.UserCredEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAuthMapper {

    public UserCredEntity mapFrom(UserAuthDto userAuthDto);

    public UserAuthDto mapTo(UserCredEntity userCredEntity);
}
