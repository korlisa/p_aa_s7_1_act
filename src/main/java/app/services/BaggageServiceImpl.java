package app.services;

import app.entities.Baggage;
import app.entities.Booking;
import app.repositories.BaggageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageServiceImpl implements BaggageService {

    private final BaggageRepository baggageRepository;

    public BaggageServiceImpl(BaggageRepository baggageRepository) {
        this.baggageRepository = baggageRepository;
    }


    @Override
    public Baggage save(Booking booking) {
        return baggageRepository.save(new Baggage(123L, 32.1, booking));
    }

    @Override
    public void update(Baggage baggage) {
        baggageRepository.save(baggage);
    }

    @Override
    public void delete(Long id) {
        baggageRepository.deleteById(id);
    }

    @Override
    public List<Baggage> findAll() {
        return baggageRepository.findAll();
    }

    @Override
    public Baggage findById(Long id) {
        return baggageRepository.getById(id);
    }
}
