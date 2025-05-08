package com.jv.demo.tradenet.frontend.service.exchangerate.service;

import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ExcRateService {
    public Optional<ExcRateEntity> findById(ExcRateIdEntity excRateId);

    public List<ExcRateEntity> findAll();

    public ExcRateEntity create(ExcRateEntity excRate) throws EntityExistsException;

    public ExcRateEntity update(ExcRateEntity excRate) throws EntityNotFoundException;

    public void delete(ExcRateEntity excRate) throws EntityNotFoundException;
}
