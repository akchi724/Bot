package ru.akchi.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private  final String botName;
    private  final String botApiToken;

    public TelegramBot(String botName, String botApiToken) {
        this.botName = botName;
        this.botApiToken = botApiToken;
    }


    @Override
    public String getBotUsername() {
        return botName;
    }

//    @Override
//    public void processNonCommandUpdate(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            Long chatId = update.getMessage().getChatId();
//            String userName = update.getMessage().getChat().getFirstName();
//            String answer = "";
//            switch (messageText) {
//                case "/start":
//                    answer = startCommandReceived(chatId, userName);
//                    break;
//                default:
//                    answer = "Sorry, command wasn't recognized";
//            }
//            sendMessage(chatId, answer);
//        }
//
//    }

    @Override
    public String getBotToken() {
        return botApiToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getChat().getFirstName();
            String answer = "";
            switch (messageText) {
                case "/start":
                    answer = startCommandReceived(chatId, userName);
                    break;
                default:
                    answer = "Sorry, command wasn't recognized";
            }
            sendMessage(chatId, answer);
        }

    }

    private String startCommandReceived(Long chatId, String userName) {

        return "Hi " + userName + ", nice to meet you!";
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage(String.valueOf(chatId), textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка при попытке отправки сообщения");
        }

    }

}
