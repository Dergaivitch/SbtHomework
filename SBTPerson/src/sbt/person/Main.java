package sbt.person;

/**
 * Created by Dergai on 26.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        Person Alex = new Person(true, "Alex");
        Person Alice = new Person(false, "Alice");
        Person Lisa = new Person(false, "Lisa");

        System.out.println("Lisa.marry(Alice):" + Lisa.marry(Alice));
        System.out.println("Lisa.getSpouse:" + Lisa.getSpouse() + " Lisa.marry(Alex):"
                + Lisa.marry(Alex) + " Lisa.getSpouse():" + Lisa.getSpouse());
        System.out.println("Alice.getSpouse():" + Alice.getSpouse() + " " + "Alice.divorce():" + Alice.divorce());
        System.out.print("Alice.marry(Alex): ");
        System.out.print(Alice.marry(Alex) + " Lisa.getSpouse():" + Lisa.getSpouse());
    }
}
