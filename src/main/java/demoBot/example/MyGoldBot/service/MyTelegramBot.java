package demoBot.example.MyGoldBot.service;


import demoBot.example.MyGoldBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramBot;

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
                    startComandReseived(chatId, update.getMessage().getChat().getFirstName());
            }
        }

    }

    private void startComandReseived(long chatId, String name){

        String answer = "HI " + name + ", NICE TO MEET YOU!";

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
