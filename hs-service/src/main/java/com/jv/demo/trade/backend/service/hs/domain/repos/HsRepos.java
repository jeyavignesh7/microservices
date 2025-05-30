package com.jv.demo.trade.backend.service.hs.domain.repos;

import com.jv.demo.trade.backend.service.hs.domain.entity.HsEntity;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsIdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsRepos extends CrudRepository<HsEntity, HsIdEntity> {
}
