/*
 * Person class, contains name ond surname of the person
 */
package stockmanager;

/**
 *
 * @author Mateusz
 */
public class Person {
    String name;
    String surname;
    public Person(String n, String s){
        name = n;
        surname=s;
    }
    
    public String getName(){
        return name + " " + surname;
    }
}
