package c4uCarSharing.gurami.services;

import c4uCarSharing.gurami.domain.Trip;

public interface TripService {
    Iterable<Trip> listAllTrips();

    Trip getTripById(Integer id);

    Trip saveTrip(Trip trip);

    void deleteTrip(Integer id);
}