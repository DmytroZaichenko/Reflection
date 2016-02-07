package service;

import java.lang.annotation.Annotation;

public class ApplicationManager {

    public ApplicationManager(Class[] classes) {

        for (Class c:classes) {
            Annotation[] annos =  c.getAnnotations();
            for (Annotation an : annos ) {
                if (an.annotationType().getName().equals(Service.class.getName())){
                    System.out.println("Class " + c.getName() + " is service");
                } else {
                    System.out.println("Class " + c.getName() + " is not service");
                }

            }

        }
    }
}
