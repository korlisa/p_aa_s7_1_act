package app.services;

import app.entities.Passenger;
import app.entities.Passport;
import app.repositories.PassportRepository;
import org.springframework.stereotype.Service;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Passport save(Passenger passenger) {
        return passportRepository.save(new Passport(
                "1234554321",
                "Mixail",
                "Ivanov",
                "Ivanovich",
                "15.11.2000",
                "20.03.2025",
                passenger
        ));
    }

    @Override
    public void update(Passport passport) {
        passportRepository.save(passport);
    }

    @Override
    public void delete(Long id) {
        passportRepository.deleteById(id);
    }
}
