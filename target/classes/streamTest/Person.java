package streamTest;

import java.util.List;

/**
 * @author Xingjian LONG
 * Created on 2021-06-08
 */
public class Person {
    private List<Long> id;
    private String name;
    private int age;

    public Person(List<Long> id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(List<Long> id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Long> getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
