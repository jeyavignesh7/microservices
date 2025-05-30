package com.jv.demo.trade.backend.service.hs.service.impl;

import com.jv.demo.trade.backend.service.hs.domain.entity.HsEntity;
import com.jv.demo.trade.backend.service.hs.domain.entity.HsIdEntity;
import com.jv.demo.trade.backend.service.hs.domain.repos.HsRepos;
import com.jv.demo.trade.backend.service.hs.service.HsService;
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
public class HsServiceImpl implements HsService {
    @Autowired
    private HsRepos hsRepos;

    @Override
    public Optional<HsEntity> findById(HsIdEntity hsId) {
        return hsRepos.findById(hsId);
    }

    @Override
    public List<HsEntity> findAll() { return StreamSupport.stream(
            hsRepos.findAll()
                    .spliterator(),
            false).collect(Collectors.toList()); }

    @Override
    public HsEntity create(HsEntity Hs) {
        if(!this.findById(Hs.getHsId()).isEmpty()) {
            throw new EntityExistsException();
        }
        return hsRepos.save(Hs);
    }

    @Override
    public HsEntity update(HsEntity Hs) throws EntityNotFoundException {
        if(this.findById(Hs.getHsId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        return hsRepos.save(Hs);
    }

    @Override
    public void delete(HsEntity Hs) throws EntityNotFoundException {
        if(this.findById(Hs.getHsId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        hsRepos.deleteById(Hs.getHsId());
    }
}
