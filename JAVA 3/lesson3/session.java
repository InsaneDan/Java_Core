package lesson3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class session {

    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream("a.txt")) {
            int x = -1;
            String str = "";
            while ((x = in.read()) >= 0) {
                if (x == 13) {
                    System.out.print(" EOL");
                } else if (x == 10) {
                    System.out.println();
                } else {
                    System.out.print((char) x + " ");
                }
            }
        }
    }
}
