package lesson2.server;


import lesson2.server.chat.MyServer;
import lesson2.server.chat.auth.BaseAuthService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerApp {

    private static final int DEFAULT_PORT = 8189;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        // настройка глобального логгера
        try {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }

        Logger logger = Logger.getLogger(ServerApp.class.getName());

        try {
            new MyServer().start(port);
            logger.info("Сервер успешно запущен");
        } catch (IOException e) {
            System.err.println("Не удалось запустить сервер");
            logger.severe("Не удалось запустить сервер");
            e.printStackTrace();
        }
    }

}
