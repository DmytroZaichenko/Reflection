package service;

import java.util.Objects;

public class Demo  {

    public static void main(String[] args) throws Exception {

        Class[] classes = new Class[2];
        classes[0] = ClassNoService.class;
        classes[1] = ClassService.class;

        ApplicationManager am = new ApplicationManager();
        am.getService(ClassNoService.class);
        am.getService(ClassService.class);

    }

}
