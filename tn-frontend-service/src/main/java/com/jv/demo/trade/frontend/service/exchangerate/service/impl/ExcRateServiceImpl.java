package com.jv.demo.trade.frontend.service.exchangerate.service.impl;

import com.jv.demo.trade.frontend.service.exchangerate.domain.entity.ExcRateEntity;
import com.jv.demo.trade.frontend.service.exchangerate.service.ExcRateService;
import com.jv.demo.trade.frontend.service.exchangerate.domain.entity.ExcRateIdEntity;
import com.jv.demo.trade.frontend.service.exchangerate.domain.repos.ExcRateRepos;
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
public class ExcRateServiceImpl implements ExcRateService {
    @Autowired
    private ExcRateRepos excRateRepos;

    @Override
    public Optional<ExcRateEntity> findById(ExcRateIdEntity excRateId) {
        return excRateRepos.findById(excRateId);
    }

    @Override
    public List<ExcRateEntity> findAll() { return StreamSupport.stream(
            excRateRepos.findAll()
                    .spliterator(),
            false).collect(Collectors.toList()); }

    @Override
    public ExcRateEntity create(ExcRateEntity excRate) {
        if(!this.findById(excRate.getExcRateId()).isEmpty()) {
            throw new EntityExistsException();
        }
        return excRateRepos.save(excRate);
    }

    @Override
    public ExcRateEntity update(ExcRateEntity excRate) throws EntityNotFoundException {
        if(this.findById(excRate.getExcRateId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        return excRateRepos.save(excRate);
    }

    @Override
    public void delete(ExcRateEntity excRate) throws EntityNotFoundException {
        if(this.findById(excRate.getExcRateId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        excRateRepos.deleteById(excRate.getExcRateId());
    }
}
