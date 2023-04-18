package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.UserDetails;
import com.lhind.repository.UserDetailsRepository;
import com.lhind.util.Queries;
import com.lhind.repository.Repository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Component
public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    private final EntityManager entityManager;

    public UserDetailsRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<UserDetails> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserDetails.class, id));
    }

    @Override
    public List<UserDetails> findAll() {
        TypedQuery<UserDetails> result = entityManager.createNamedQuery("UserDetails.findAll", UserDetails.class);
        return result.getResultList();
    }

    @Override
    public void save(UserDetails userDetails) {
        entityManager.getTransaction().begin();
        if (userDetails.getId() != null) {
            findById(userDetails.getId()).ifPresent(existingUserDetails -> {
                userDetails.setFirstName(existingUserDetails.getFirstName());
                userDetails.setLastName(existingUserDetails.getLastName());
                userDetails.setPhoneNumber(existingUserDetails.getPhoneNumber());
            });
        } else {
            entityManager.persist(userDetails);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(UserDetails userDetails) {
        if (userDetails.getId() != null) {
            entityManager.getTransaction().begin();
            findById(userDetails.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<UserDetails> findByPhoneNumber(String phoneNumber) {
        TypedQuery<UserDetails> result = entityManager.createQuery("UserDetails.findByPhoneNumber", UserDetails.class);
        result.setParameter("phoneNumber", phoneNumber);
        return result.getResultList();
    }
}
