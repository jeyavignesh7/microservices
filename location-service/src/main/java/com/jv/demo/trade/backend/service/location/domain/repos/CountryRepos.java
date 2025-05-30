package com.jv.demo.trade.backend.service.location.domain.repos;

import com.jv.demo.trade.backend.service.location.domain.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepos extends CrudRepository<CountryEntity, String> {
}
