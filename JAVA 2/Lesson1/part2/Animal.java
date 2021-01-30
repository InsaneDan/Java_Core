package Lesson1.part2;

public abstract class Animal {

    public static final String DEFAULT_NAME = "default name";
    protected String name;

    protected static String staticValue = "5";

    public static String getStaticValue() {
        return staticValue;
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
        name = DEFAULT_NAME;
    }

    public void animalInfo() {
        System.out.println("Animal name is " + name);
    }

    public void jump() {
        System.out.println("Animal jumped");
    }

    public String getName() {
        return name;
    }

    public abstract void voice();

}
