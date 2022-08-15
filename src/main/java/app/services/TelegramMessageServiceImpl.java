package app.services;

import org.springframework.stereotype.Service;

/**
 * TODO * Telegram Message Service Impl
 */

@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {

    @Override
    public void sendFlightEventMessage(String message) {
        System.out.println("Телеграм отработал!\n" + message);
    }
}
