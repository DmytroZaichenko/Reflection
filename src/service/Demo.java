package service;

import java.util.Objects;

public class Demo {

    public static void main(String[] args) {
        Class[] classes = new Class[2];
        classes[0] = ClassNoService.class;
        classes[1] = ClassService.class;
        new ApplicationManager(classes);
    }

}
