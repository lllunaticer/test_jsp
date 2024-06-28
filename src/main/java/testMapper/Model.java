package testMapper;

import lombok.Getter;

/**
 * @author Xingjian LONG
 * Created on 2021-07-20
 */
@Getter
public class Model {
    private String name;
    private int age;

    public Model(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
