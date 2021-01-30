package Lesson1.part2;

import Lesson1.part3.DogV2;
import Lesson1.part3.Waterfowl;

public class TestAnimal {

    public static void main(String[] args) {
//        Animal animal = new Animal("оно");

//        System.out.println(Color.GRAY);
//
//        System.out.println(Color.valueOf("BLACK"));
//        System.out.println(Color.valueOf("BLACK").ordinal());
//////
//        for (Color value : Color.values()) {
//            System.out.println(value.getRussianColor());
//        }


        Cat.CatAttribute catAttribute = new Cat.CatAttribute();
        Cat cat = new Cat("Бааааааарсик", Color.WHITE, catAttribute);
        Cat cat2 = new Cat("Бааааааарсик2", Color.WHITE, catAttribute);
        DogV2 dog = new DogV2("Boris", "black", "ovcharka");
        System.out.println(Cat.testStatic);
        System.out.println(Cat.testStatic);
        cat2.testStatic = "newTest";
        System.out.println(cat.testStatic);
        System.out.println(cat2.testStatic);

        cat.voice();
        dog.voice();

        swim(dog);

        printInfo(cat);
        printInfo(dog);
        dog.animalInfo();

//        ((Cat)dog).getCatAttribute();

//        System.out.println(Animal.getStaticValue());
//        System.out.println(Dog.getStaticValue());
//        Cat.CatAttribute catAttribute1 = ((Cat) cat).getCatAttribute();
        System.out.println("instanceof: " + (cat instanceof Animal));
        System.out.println("instanceof: " + (cat instanceof Cat));
//        System.out.println("instanceof: " + (dog instanceof Cat));
//        System.out.println(Cat.class.cast(cat).getCatAttribute());
        System.out.println(Animal.class.cast(cat).getName());
//        System.out.println(Dog.class.cast(cat).getName());


//        animal.animalInfo();
//
//        cat.catInfo();
//        cat.animalInfo();

//
//        dog.dogInfo();
//        dog.animalInfo();
//        dog.jump();

//        infoAndJump(animal);
//        infoAndJump(cat);
//        infoAndJump(dog);
    }

    private static void printInfo(Animal animal) {
        animal.animalInfo();
    }

    private static void infoAndJump(Animal animal) {
        animal.animalInfo();
        animal.jump();
//        animal.voice();
        System.out.println();
    }

    private static void swim(Waterfowl waterfowl) {
        waterfowl.swim();
    }
}
