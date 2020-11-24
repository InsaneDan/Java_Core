package Lesson8;

import javax.swing.*;
import java.awt.*;

public class LessonSession {

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
        MyWindow myWindow2 = new MyWindow();
    }


}

class MyWindow extends JFrame {
    public MyWindow() {
        setBounds(500, 500, 400, 300);
        setTitle("FlowLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[] jbs = new JButton[10];
        setLayout(new FlowLayout());
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton("#" + i);
            add(jbs[i]);
        }
        setVisible(true);

    }
}
