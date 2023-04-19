package com.lhind.mapper;

import com.lhind.model.dto.FlightDTO;
import com.lhind.model.entity.Flight;
import com.lhind.model.enums.FlightStatus;

import java.util.HashSet;
import java.util.Set;

public class FlightMapper extends AbstractMapper<Flight, FlightDTO> {

    @Override
    public Flight toEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setAirline(flightDTO.getAirline());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setStatus(flightDTO.getStatus());
        flight.setBookings(new HashSet<>());
        return flight;
    }

    @Override
    public FlightDTO toDto(Flight flight) {
        if (flight == null) {
            return null;
        }
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setAirline(flight.getAirline());
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setArrivalDate(flight.getArrivalDate());
        flightDTO.setStatus(flight.getStatus());
        Set<Long> bookingIds = new HashSet<>();
        if (flight.getBookings() != null) {
            flight.getBookings().forEach(booking -> bookingIds.add(booking.getId()));
        }
        flightDTO.setBookingIds(bookingIds);
        return flightDTO;
    }
}