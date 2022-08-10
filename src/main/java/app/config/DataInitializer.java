package app.config;


import app.entities.*;
import app.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;

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

    private final TicketService ticketService;
    private final SeatService seatService;

    @PostConstruct
    public void init() {
        initRoles();
        createUserForSpringSecurity();
        createDestination();
        createAircraft();
        createFlight();
        createTestPassenger();
        createTicket();
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
        Role admin = new Role(1L,"ROLE_ADMIN");
        Role manager = new Role(2L,"ROLE_MANAGER");
        Role passenger = new Role(3L,"ROLE_PASSENGER");
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
     */

    public void createAircraft() {

        aircraftService.save(new Fleet().createBoeing737());
        aircraftService.save(new Fleet().createAirbusA321());
        aircraftService.save(new Fleet().createEmbraer170());

    }

    public void createFlight() {

        Flight flight = new Flight();
        flight.setAircraft(aircraftService.getById(1L));
        flightService.save(flight);

    }

    public void createTestPassenger() {
        Passenger passenger = new Passenger();
        passenger.setEmail("pass@gmail.com");
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

    public void createTicket() {
        Passenger p1 = new Passenger("Alina", "Lisova", "89772666342", "lisova@mail.ru",
                "Evgenevna", LocalDate.of(1996, 12, 22), new Passport("34567875",
                "22.12.2040", "usa"));
        Passenger p2 =  new Passenger("Vika", "Rodina", "89772666342", "rodina@mail.ru",
                "Vladimirovna", LocalDate.of(1995, 07, 24), new Passport("8646473",
                "22.12.2040", "usa"));
        Passenger p3 =  new Passenger("Vika", "Kim", "89772666232", "rodina2323@mail.ru",
                "Vladimirovna", LocalDate.of(1992, 07, 24), new Passport("433345",
                "22.12.2030", "korea"));
        passengerService.savePassenger(p1);
        passengerService.savePassenger(p2);
        passengerService.savePassenger(p3);

        Flight flight = new Flight();
        Flight flight2 = new Flight();

        flightService.save(flight);
        flightService.save(flight2);

        Seat seat = new Seat();
        Seat seat2 = new Seat();
        Seat seat3 = new Seat();

        seatService.save(seat);
        seatService.save(seat2);
        seatService.save(seat3);

        Ticket ticket = new Ticket();

        ticket.setFlight(flight);
        ticket.setPassenger(p1);
        ticket.setSeat(seat);
        ticket.setSubcategory(Ticket.Subcategory.BASIC);

        Ticket ticket2 = new Ticket();
        ticket2.setFlight(flight2);
        ticket2.setPassenger(p2);
        ticket2.setSeat(seat2);
        ticket2.setSubcategory(Ticket.Subcategory.BASIC);

        Ticket ticket3 = new Ticket();
        ticket3.setFlight(flight);
        ticket3.setPassenger(p3);
        ticket3.setSeat(seat3);
        ticket3.setSubcategory(Ticket.Subcategory.BASIC);

        ticketService.saveTicket(ticket);
        ticketService.saveTicket(ticket2);
        ticketService.saveTicket(ticket3);
    }

}
