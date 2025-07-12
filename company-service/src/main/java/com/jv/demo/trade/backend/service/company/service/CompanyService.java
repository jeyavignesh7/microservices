package com.jv.demo.trade.backend.service.company.service;

import com.jv.demo.trade.backend.service.company.domain.entity.CompanyEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    public Optional<CompanyEntity> findById(String id);
    public List<CompanyEntity> findAll();
    public CompanyEntity create(CompanyEntity company) throws EntityExistsException;
    public CompanyEntity update(CompanyEntity company) throws EntityNotFoundException;
    public void deleteById(String id) throws EntityNotFoundException;
    public List<CompanyEntity> findAllWithPagination(int pageNo, int pageSize, String sortBy, String direction);
    public Page<CompanyEntity> findByNameWithPagination(String name, int pageNo, int pageSize, String sortBy, String direction);
}
