package org.compass.desafio.initializer;

import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.repository.DistributionCenterRepository;


public class DistributionCenterInitializer {
    public void initialize() {
        DistributionCenterRepository repository = new DistributionCenterRepository();

        DistributionCenter dc1 = new DistributionCenter();
        dc1.setName("Esperança");
        dc1.setAddress("Av. Boqueirão, 2450");
        dc1.setNeighborhood("Igara");
        dc1.setCity("Canoas");
        dc1.setState("Rio Grande do Sul");
        dc1.setCep("92032-420");

        repository.save(dc1);

        DistributionCenter dc2 = new DistributionCenter();
        dc2.setName("Prosperidade");
        dc2.setAddress("Av. Borges de Medeiros, 1501");
        dc2.setNeighborhood("Cidade Baixa");
        dc2.setCity("Porto Alegre");
        dc2.setState("Rio Grande do Sul");
        dc2.setCep("90119-900");

        repository.save(dc2);

        DistributionCenter dc3 = new DistributionCenter();
        dc3.setName("Reconstrução");
        dc3.setAddress("R. Dr. Décio Martins Costa, 312 ");
        dc3.setNeighborhood("Vila Eunice Nova");
        dc3.setCity("Cachoeirinha");
        dc3.setState("Rio Grande do Sul");
        dc3.setCep("94920-170");

        repository.save(dc3);


    }
}