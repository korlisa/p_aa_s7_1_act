package app.services;

import app.entities.Booking;
import app.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BaggageService baggageService;

    public BookingServiceImpl(BookingRepository bookingRepository, BaggageService baggageService) {
        this.bookingRepository = bookingRepository;
        this.baggageService = baggageService;
    }

    @Override
    public void save(Booking booking) {
        baggageService.save(booking);
        bookingRepository.save(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.getById(id);
    }
}
