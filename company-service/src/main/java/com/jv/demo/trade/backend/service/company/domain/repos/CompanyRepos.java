package com.jv.demo.trade.backend.service.company.domain.repos;

import com.jv.demo.trade.backend.service.company.domain.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepos extends JpaRepository<CompanyEntity, String> {

    @Query("SELECT c FROM CompanyEntity c ")
    public List<CompanyEntity> findAllWithPagination(Pageable pageable);

    @Query("SELECT c FROM CompanyEntity c WHERE c.name LIKE '%:name%' ")
    public Page<CompanyEntity> findByNameWithPagination(@Param("name") String name, Pageable pageable);
}
