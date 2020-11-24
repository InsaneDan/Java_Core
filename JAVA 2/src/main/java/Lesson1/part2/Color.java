package Lesson1.part2;

public enum Color {
//    BLACK,
//    WHITE,
//    RED,
//    GRAY



    WHITE("Белый"), // Color WHITE = new Color("Белый");
    RED("Рыжий"),
    BLACK("Черный"),
    GRAY("Серый");

    public static void test() {
        System.out.println("dsfs");
    }

    private String russianColor;

    Color(String russianColor) {
        this.russianColor = russianColor;
    }

    public String getRussianColor() {
        return russianColor;
    }

    @Override
    public String toString() {
        return "Color{" +
                "russianColor='" + russianColor + '\'' +
                '}';
    }
}
