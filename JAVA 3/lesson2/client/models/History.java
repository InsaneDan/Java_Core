package lesson2.client.models;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class History {

    private String login;

    public History(String login) {
        this.login = login;
    }

    private String getChatHistoryFileName() {
        return "//C:\\GeekBrains\\JAVA\\JAVA 3\\src\\main\\java\\lesson2\\client\\history\\history_" + login + ".txt";
    }

    public void saveChatHistory(String msgToSave) {
        try {
            /* // так было бы короче, но это java.nio
            Files.write(Paths.get(getChatHistoryFileName()),
                    msgToSave.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            */
//            msgToSave.concat("\n");
            FileOutputStream fileOutputStream = new FileOutputStream(getChatHistoryFileName(), true);
            fileOutputStream.write(msgToSave.concat("\n").getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читаем все строки из файла в ArrayList
    public List<String> getAllChatHistory() {

        // наверное, экономичнее было бы использовать ReversedLinesFileReader (Apache Commons IO)?
        String msg = null;
        List<String> historyList = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(getChatHistoryFileName());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                historyList.add(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return historyList;
    }

}
