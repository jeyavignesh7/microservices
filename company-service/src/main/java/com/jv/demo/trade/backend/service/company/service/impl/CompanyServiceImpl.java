package com.jv.demo.trade.backend.service.company.service.impl;

import com.jv.demo.trade.backend.service.company.domain.entity.CompanyEntity;
import com.jv.demo.trade.backend.service.company.domain.repos.CompanyRepos;
import com.jv.demo.trade.backend.service.company.service.CompanyService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepos companyRepos;

    public CompanyServiceImpl(CompanyRepos companyRepos) {
        this.companyRepos = companyRepos;
    }

    @Override
    public Optional<CompanyEntity> findById(String id) {
        return companyRepos.findById(id);
    }

    @Override
    public List<CompanyEntity> findAll() {
        return companyRepos.findAll();
    }

    @Override
    public CompanyEntity create(CompanyEntity company) throws EntityExistsException {
        if(!this.findById(company.getCompanyId()).isEmpty()){
            throw new EntityExistsException("Company Record already exists:" + company);
        }
        return companyRepos.save(company);
    }

    @Override
    public CompanyEntity update(CompanyEntity company) throws EntityNotFoundException {
        if(this.findById(company.getCompanyId()).isEmpty()){
            throw new EntityNotFoundException("Company Record does not exist for update:" + company.getCompanyId());
        }
        return companyRepos.save(company);
    }

    @Override
    public void deleteById(String id) throws EntityNotFoundException {
        if(this.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Company Record does not exist for deletion:" + id);
        }
        companyRepos.deleteById(id);
    }

    @Override
    public List<CompanyEntity> findAllWithPagination(int pageNo, int pageSize, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy);
        if("desc".equalsIgnoreCase(direction)) {
            sort = sort.descending();
        }else{
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return companyRepos.findAllWithPagination(pageable);
    }

    @Override
    public List<CompanyEntity> findByNameWithPagination(String name, int pageNo, int pageSize, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy);
        if("desc".equalsIgnoreCase(direction)) {
            sort = sort.descending();
        }else{
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return companyRepos.findByNameWithPagination(name, pageable);
    }
}
