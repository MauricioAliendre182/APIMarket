package com.project.market.domain.service;

import com.project.market.domain.repository.UserRepositoryDTO;
import com.project.market.persistence.entities.DomainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepositoryDTO userRepositoryDTO;

    public Optional<DomainUser> getUser(int idUser) {
        return userRepositoryDTO.getAUserById(idUser);
    }

    public DomainUser save(DomainUser domainUser) {
        return userRepositoryDTO.save(domainUser);
    }

    public boolean delete(int idUser) {
        return getUser(idUser).map(product -> {
            userRepositoryDTO.delete(idUser);
            return true;
        }).orElse(false);
    }
}
