package app.config;

import app.entities.*;
import app.repositories.DestinationRepository;
import app.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.time.LocalDateTime;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */

@AllArgsConstructor
@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final DestinationService destinationService;
    private final AircraftService aircraftService;
    private final FlightService flightService;
    private final PassengerService passengerService;
    private final SeatService seatService;
    private final TicketService ticketService;
    private final DestinationRepository destinationRepository;

    @PostConstruct
    public void init() {
        initRoles();
        createUserForSpringSecurity();
        createDestination();
        createAircraft();
        createFlight();
        createTestPassenger();
        createTestTicket();
//        createTickets();


        System.out.println("DataInitializer сработал!");
    }

    /**
     * Method for initialization destination
     */
    public void createDestination() {
        Destination destination = new Destination("New York", "United States", "America");
        Destination destination2 = new Destination("Cairo", "Egypt", "Africa");
        Destination destination3 = new Destination("Stockholm", "Sweden", "Europe");

        destinationService.saveDestination(destination);
        destinationService.saveDestination(destination2);
        destinationService.saveDestination(destination3);
    }

    /**
     * Метод для инициализации ролей
     */
    public void initRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role manager = new Role("ROLE_MANAGER");
        Role passenger = new Role("ROLE_PASSENGER");
        roleService.saveRole(admin);
        roleService.saveRole(manager);
        roleService.saveRole(passenger);
    }

    /**
     * Метод для создания юзера для SpringSecurity
     */
    public void createUserForSpringSecurity(){
        User admin = new Admin();
        admin.setEmail("admin@email.com");
        admin.setPassword("admin");
        admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
        userService.saveUser(admin);

        User manager = new AirlineManager();
        manager.setEmail("manager@email.com");
        manager.setPassword("manager");
        manager.addRoleToCollection(roleService.findRoleByName("ROLE_MANAGER"));
        userService.saveUser(manager);

    }

    /**
     * Create 3 Aircraft in DB
     *
     * @author Eugene Kolyshev
     */

    public void createAircraft() {

        aircraftService.save(new Fleet().createBoeing737());
        aircraftService.save(new Fleet().createAirbusA321());
        aircraftService.save(new Fleet().createEmbraer170());

    }

    public void createFlight() {
        Flight flight = new Flight();
        LocalDateTime localDateTimeDepart = LocalDateTime.of(2022, Month.AUGUST, 14, 4, 15, 0);
        LocalDateTime localDateTimeArrive = localDateTimeDepart.plusHours(15);

        flight.setFrom(destinationRepository.findDestinationByCity("New York").get());
        flight.setTo(destinationRepository.findDestinationByCity("Stockholm").get());
        flight.setDepartureDateTime(localDateTimeDepart);
        flight.setArrivalDateTime(localDateTimeArrive);
        flight.setAircraft(aircraftService.getById(2L));
        flight.setFlightStatus(Flight.FlightStatus.ON_TIME);
        flightService.save(flight);
    }

    public void createTestPassenger() {
        Passenger passenger = new Passenger();
        passenger.setEmail("plekhov.a.dm@gmail.com");
        passenger.setPassword("pass");
        passenger.setFirstName("Morgan");
        passenger.setLastName("Freeman");
        passenger.setMiddleName("Freemanovich");
        passenger.setPhoneNumber("+7-913-937-77-77");
        passenger.setDateOfBirth(LocalDate.of(1937, Month.JUNE, 1));
        passenger.setPassport(new Passport("1234 567890", "2022-11-22", "Russian"));
        passenger.addRoleToCollection(roleService.findRoleByName("ROLE_PASSENGER"));
        passengerService.savePassenger(passenger);
    }

    public void createTestTicket() {
        Ticket ticket = new Ticket();
        ticket.setFlight(flightService.findFlightById(1L));
        ticket.setPassenger(passengerService.getPassenger(3L));
        ticket.setSubcategory(Ticket.Subcategory.STANDARD);
        ticket.setSeat(seatService.getById(23L));
        ticketService.saveTicket(ticket);
    }
}
