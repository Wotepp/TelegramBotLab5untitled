package ru.university;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.university.config.BotConfig;
import ru.university.processor.TextProcessor;

public class TextProcessorBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;

    public TextProcessorBot() {
        telegramClient = new OkHttpTelegramClient(BotConfig.BOT_TOKEN);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String response;
            if (messageText.equals("/start")) {
                response = "Привет! Я бот, который разбивает текст на слоги по гласным. Просто отправь мне любое сообщение.";
            } else if (messageText.equals("/help")) {
                response = "Отправь мне фразу, и я разделю её на слоги так, чтобы каждый слог начинался с гласной. Пример:\nпрограммирование → про-гра-мми-ро-ва-ни-е";
            } else {
                String processed = TextProcessor.splitByVowels(messageText);
                response = "🔹 Результат:\n" + processed;
            }

            SendMessage reply = SendMessage.builder()
                    .chatId(chatId)
                    .text(response)
                    .build();

            try {
                telegramClient.execute(reply);
            } catch (TelegramApiException e) {
                System.err.println("Ошибка отправки: " + e.getMessage());
            }
        }
    }
}