package com.project.market.domain.repository;

import com.project.market.persistence.entities.DomainUser;

import java.util.Optional;

public interface UserRepositoryDTO {
    Optional<DomainUser> getAUserById(int idUser);
    DomainUser save(DomainUser domainUser);
    void delete(int idUser);
}
