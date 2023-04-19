package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.Booking;
import com.lhind.repository.BookingRepository;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {

    private final EntityManager entityManager;

    public BookingRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Booking.class, id));
    }

    @Override
    public List<Booking> findAll() {
        TypedQuery<Booking> result = entityManager.createNamedQuery("Booking.findAll", Booking.class);
        return result.getResultList();
    }

    @Override
    public void save(Booking booking) {
        entityManager.getTransaction().begin();
        if (booking.getId() != null) {
            findById(booking.getId()).ifPresent(existingBooking -> {
                existingBooking.setUser(booking.getUser());
                existingBooking.setBookingDate(booking.getBookingDate());
                existingBooking.setStatus(booking.getStatus());

            });
        } else {
            entityManager.persist(booking);
        }
        entityManager.getTransaction().commit();
    }


    @Override
    public void delete(Booking booking) {
        if (booking.getId() != null) {
            entityManager.getTransaction().begin();
            findById(booking.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }
}
