package com.jv.demo.trade.backend.service.authapi.user.domain.repos;

import com.jv.demo.trade.backend.service.authapi.user.domain.entity.UserCredEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredRepos extends CrudRepository<UserCredEntity, String> {
    public Optional<UserCredEntity> findByEmailId(String emailId);
}
