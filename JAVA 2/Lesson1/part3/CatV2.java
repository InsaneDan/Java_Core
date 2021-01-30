package Lesson1.part3;


import Lesson1.part2.Animal;

public class CatV2 extends Animal implements Pet, Waterfowl {

    private final String color;

    public CatV2(String name, String color) {
        super(name);
        this.color = color;
    }

    @Override
    public void animalInfo() {
        super.animalInfo();
        System.out.println("Cat name is " + super.getName() + "; color - " + color);
    }

    @Override
    public void voice() {

    }

//    @Override
//    public void voice() {
//        System.out.println("Мяу!");
//    }

    @Override
    public void loveMaster() {
        System.out.println("Мур");
    }

    @Override
    public int swim() {
        return 0;
    }

    @Override
    public boolean isUseful() {
        return false;
    }
}
