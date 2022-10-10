package ru.akchi.bot;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.akchi.bot.service.TelegramBot;

public class BotApplication {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(
                    "MyJava19890Bot",
                    "5613951667:AAHC4i1uU5QsrWC2vFO9ellI0ToPHI8Z_c0"));
        } catch (TelegramApiException e) {
//            e.printStackTrace();
        }
    }

}
