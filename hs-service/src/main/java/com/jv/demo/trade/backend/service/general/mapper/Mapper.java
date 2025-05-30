package com.jv.demo.trade.backend.service.general.mapper;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}
