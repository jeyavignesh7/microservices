package com.jv.demo.tradenet.frontend.service.exchangerate.domain.repos;

import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.tradenet.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcRateRepos extends CrudRepository<ExcRateEntity, ExcRateIdEntity> {
}
