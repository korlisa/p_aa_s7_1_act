package app.repositories;

import app.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

//Репозиторий сущности "Пассажир"
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
