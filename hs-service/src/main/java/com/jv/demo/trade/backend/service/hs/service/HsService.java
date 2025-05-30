package com.jv.demo.trade.backend.service.hs.service;

import com.jv.demo.trade.backend.service.hs.domain.entity.HsEntity;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsIdEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface HsService {
    public Optional<HsEntity> findById(HsIdEntity HsId);

    public List<HsEntity> findAll();

    public HsEntity create(HsEntity Hs) throws EntityExistsException;

    public HsEntity update(HsEntity Hs) throws EntityNotFoundException;

    public void delete(HsEntity Hs) throws EntityNotFoundException;
}
