package Lesson1.part2;

public class Cat extends Animal {

    public static String testStatic = "test";

    private Color color = Color.RED;
    private CatAttribute catAttribute;

    public static class CatAttribute {
        private String meal;
        private Color colorEyes;
        private int weight;
    }

    public Cat(String name, Color color, CatAttribute catAttribute) {
        super(name);
        this.color = color;
        this.catAttribute = catAttribute;
//        CatAttribute cat1 = new CatAttribute();
//        CatAttribute cat2 = new CatAttribute();
//        CatAttribute cat3 = new CatAttribute();
//        CatAttribute cat4 = new CatAttribute();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void animalInfo() {
        System.out.println("Cat name is " + super.getName() + "; color - " + color);
    }

    public CatAttribute getCatAttribute() {
        return catAttribute;
    }

    @Override
    public void jump() {
        System.out.println("Кот прыгнул!");
    }

    @Override
    public void voice() {
        System.out.println("Мяу!");
    }

}
