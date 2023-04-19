package com.lhind.repository.impl;

import com.lhind.model.entity.Flight;
import com.lhind.repository.FlightRepository;
import com.lhind.util.Queries;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public class FlightRepositoryImpl implements FlightRepository {

    private final EntityManager entityManager;

    public FlightRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Flight.class, id));
    }

    @Override
    public List<Flight> findAll() {
        TypedQuery<Flight> result = entityManager.createNamedQuery("Flight.findAll", Flight.class);
        return result.getResultList();
    }

    @Override
    public void save(Flight flight) {
        entityManager.getTransaction().begin();
        if (flight.getId() != null) {
            findById(flight.getId()).ifPresent(existingFlight -> {
                existingFlight.setOrigin(flight.getOrigin());
                existingFlight.setDestination(flight.getDestination());
                existingFlight.setAirline(flight.getAirline());
                existingFlight.setFlightNumber(flight.getFlightNumber());
                existingFlight.setDepartureDate(flight.getDepartureDate());
                existingFlight.setArrivalDate(flight.getArrivalDate());
                existingFlight.setStatus(flight.getStatus());
            });
        } else {
            entityManager.persist(flight);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Flight flight) {
        if (flight.getId() != null) {
            entityManager.getTransaction().begin();
            findById(flight.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

  /*  @Override
    public List<Flight> findByDepartureAndArrivalAirports(String departureAirport, String arrivalAirport) {
        TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_AIRPORTS, Flight.class);
        result.setParameter("departureAirport", departureAirport);
        result.setParameter("arrivalAirport", arrivalAirport);
        return result.getResultList();
    }*/
}