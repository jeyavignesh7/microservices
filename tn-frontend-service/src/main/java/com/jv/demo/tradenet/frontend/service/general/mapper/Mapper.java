package com.jv.demo.tradenet.frontend.service.general.mapper;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}
