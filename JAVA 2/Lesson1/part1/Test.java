package Lesson1.part1;

public class Test {

    public static void main(String[] args) {
//        System.out.println("Hello, World!");

        DocumentEx1 document = new DocumentEx1();
        document.title = "My First part1";
        document.content = "Content of my first part1";

        System.out.println(document.title);
        System.out.println(document.content);
    }
}
