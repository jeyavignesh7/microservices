package com.jv.demo.trade.frontend.service.general.mapper;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}
