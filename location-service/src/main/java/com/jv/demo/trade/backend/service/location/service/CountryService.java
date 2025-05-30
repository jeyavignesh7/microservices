package com.jv.demo.trade.backend.service.location.service;

import com.jv.demo.trade.backend.service.location.domain.entity.CountryEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Optional<CountryEntity> findById(String code);

    public List<CountryEntity> findAll();

    public CountryEntity create(CountryEntity Hs) throws EntityExistsException;

    public CountryEntity update(CountryEntity Hs) throws EntityNotFoundException;

    public void delete(CountryEntity Hs) throws EntityNotFoundException;
}
