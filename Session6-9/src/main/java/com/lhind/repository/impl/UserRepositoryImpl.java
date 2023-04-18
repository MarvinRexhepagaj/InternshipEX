package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.User;
import com.lhind.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Component
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> result = entityManager.createNamedQuery("User.findAll", User.class);
        return result.getResultList();
    }
    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        if (user.getId() != null) {
            findById(user.getId()).ifPresent(existingUser -> {
                user.setUsername(user.getUsername());
                user.setPassword(user.getPassword());
                user.setRole(user.getRole());
            });
        } else {
            entityManager.persist(user);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null) {
            entityManager.getTransaction().begin();
            findById(user.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> result = entityManager.createNamedQuery("User.findByUsername", User.class);
        result.setParameter("username", username);
        try {
            return Optional.of(result.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByRole(String role) {
        TypedQuery<User> result = entityManager.createNamedQuery("User.findByRole", User.class);
        result.setParameter("role", role);
        return result.getResultList();
    }
    @Override
    public List<Object[]> findUserAndDetailsById(Long userId) {
        TypedQuery<Object[]> query = entityManager.createNamedQuery("User.findUserAndDetailsById", Object[].class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

}