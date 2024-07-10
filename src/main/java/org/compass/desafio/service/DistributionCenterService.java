package org.compass.desafio.service;

import org.compass.desafio.model.*;
import org.compass.desafio.repository.DistributionCenterRepository;

import java.util.List;

public class DistributionCenterService {

    private final DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();

    public DistributionCenter save(DistributionCenter distributionCenter) {
        return distributionCenterRepository.save(distributionCenter);
    }

    public DistributionCenter update(DistributionCenter distributionCenter) {
        return distributionCenterRepository.update(distributionCenter);
    }

    public DistributionCenter findById(Long id) {
        return distributionCenterRepository.findById(id);
    }

    public List<DistributionCenter> findAll() {
        return distributionCenterRepository.findAll();
    }

    public boolean delete(Long id) {
        return distributionCenterRepository.delete(id);
    }

}

