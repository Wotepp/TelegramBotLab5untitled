package ru.university;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.university.config.BotConfig;

public class TextBotApplication {
    public static void main(String[] args) {
        try {
            TelegramBotsLongPollingApplication app = new TelegramBotsLongPollingApplication();
            TextProcessorBot bot = new TextProcessorBot();

            app.registerBot(BotConfig.BOT_TOKEN, bot);

            System.out.println("Бот запущен: @" + BotConfig.BOT_USERNAME);
            System.out.println("Жду сообщений... (нажми Ctrl+C для остановки)");

            Thread.currentThread().join();

        } catch (TelegramApiException e) {
            System.err.println("Ошибка Telegram API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Бот остановлен.");
        }
    }
}