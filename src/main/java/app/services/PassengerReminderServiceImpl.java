package app.services;

import app.events.FlightEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Passenger Reminder Service Implementation
 *
 * @author Alexander Plekhov
 */

@Service
public class PassengerReminderServiceImpl implements PassengerReminderService {
    private final String zoneMoscow = "Europe/Moscow";
    private final String EVERY_FIVE_MINUTES = "0 */5 * * * *";
    private final FlightEventPublisher flightEventPublisher;

    @Autowired
    public PassengerReminderServiceImpl(FlightEventPublisher flightEventPublisher) {
        this.flightEventPublisher = flightEventPublisher;
    }

    /**
     * An event-reminder is launched every 5 minutes
     */
    @Override
    @Async
    @Scheduled(cron = EVERY_FIVE_MINUTES, zone = zoneMoscow)
    public void remindAboutFlight() {
        LocalDateTime currentDateTime = LocalDateTime.now().withNano(0);
        flightEventPublisher.startRemindAboutFlightEvent(currentDateTime);
    }
}
