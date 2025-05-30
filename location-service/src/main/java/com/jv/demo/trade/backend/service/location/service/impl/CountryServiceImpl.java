package com.jv.demo.trade.backend.service.location.service.impl;

import com.jv.demo.trade.backend.service.location.domain.entity.CountryEntity;
import com.jv.demo.trade.backend.service.location.domain.repos.CountryRepos;
import com.jv.demo.trade.backend.service.location.service.CountryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@NoArgsConstructor
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepos countryRepos;

    @Override
    public Optional<CountryEntity> findById(String code) {
        return countryRepos.findById(code);
    }

    @Override
    public List<CountryEntity> findAll() { return StreamSupport.stream(
            countryRepos.findAll()
                    .spliterator(),
            false).collect(Collectors.toList()); }

    @Override
    public CountryEntity create(CountryEntity country) {
        if(!this.findById(country.getCode()).isEmpty()) {
            throw new EntityExistsException();
        }
        return countryRepos.save(country);
    }

    @Override
    public CountryEntity update(CountryEntity country) throws EntityNotFoundException {
        if(this.findById(country.getCode()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        return countryRepos.save(country);
    }

    @Override
    public void delete(CountryEntity country) throws EntityNotFoundException {
        if(this.findById(country.getCode()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        countryRepos.deleteById(country.getCode());
    }
}
