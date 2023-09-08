package com.project.market.domain.repository;

import com.project.market.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryDTO {
    List<Client> getAll();
    Optional<Client> getClient(String clientId);
    Client save(Client client);
    void delete(String clientId);
}
