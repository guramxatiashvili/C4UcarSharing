package c4uCarSharing.gurami.services;

import c4uCarSharing.gurami.domain.Trip;
import c4uCarSharing.gurami.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository productRepository;

    @Autowired
    public void setProductRepository(TripRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Trip> listAllTrips() {
        return productRepository.findAll();
    }

    @Override
    public Trip getTripById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Trip saveTrip(Trip trip) {
        return productRepository.save(trip);
    }

    @Override
    public void deleteTrip(Integer id) {
        productRepository.delete(id);
    }
}