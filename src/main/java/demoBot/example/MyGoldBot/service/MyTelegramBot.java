package demoBot.example.MyGoldBot.service;


import demoBot.example.MyGoldBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public MyTelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    try {
                        startComandReseived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                default:
                    try {
                        sendMessage(chatId, "SORRY, command was not!!!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }
        }

    }

    private void startComandReseived(long chatId, String name) throws TelegramApiException {

        String answer = "HI " + name + ", NICE TO MEET YOU!";

        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend) throws TelegramApiException{
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e){

        }
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken(){
        return config.getToken();
    }
}
