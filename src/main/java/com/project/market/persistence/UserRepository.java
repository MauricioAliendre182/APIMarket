package com.project.market.persistence;

import com.project.market.domain.repository.UserRepositoryDTO;
import com.project.market.persistence.crud.UserCrudRepository;
import com.project.market.persistence.entities.DomainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryDTO {

    @Autowired
    private UserCrudRepository userCrudRepository;
    @Override
    public Optional<DomainUser> getAUserById(int idUser) {
        return userCrudRepository.findById(idUser);
    }

    @Override
    public DomainUser save(DomainUser domainUser) {
        return userCrudRepository.save(domainUser);
    }

    @Override
    public void delete(int idUser) {
        userCrudRepository.deleteById(idUser);
    }
}
