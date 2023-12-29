/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-02-19
 */
public class People {
    String name;
    int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSimpleName() {
        return getClass().getSimpleName();
    }

    public static People initPeople() {
        People people = new People();
        people.setAge(100);
        people.setName("default");
        return people;
    }
}
