package ru.geekbrains.java.java1;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Miner extends JFrame {

    private JLabel statusbar;

    public Miner() {
        initGameBoard();
    }

    private void initGameBoard() {
        // статус-бар внизу окна
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar)); // добавить объект - игровое поле со статус-баром

        setResizable(false); // не изменять размеры
        pack(); // изменить окно по размеру его содержимого

        setTitle("Сапер"); // заголовок окна
        setLocationRelativeTo(null); // не привязывать расположение окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // действие при закрытии
    }

    public static void main(String[] args) {
        Miner game = new Miner();
        game.setVisible(true);
    }
}
