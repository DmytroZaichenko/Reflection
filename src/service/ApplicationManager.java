package service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationManager {

    public ApplicationManager(Class[] classes) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        for (Class c : classes) {
            Annotation[] annos = c.getAnnotations();
            for (Annotation an : annos) {

                if (an.annotationType().getName().equals(Service.class.getName())) {

                    Object serv = c.getDeclaredConstructors()[0].newInstance();

                    Method[] methods = serv.getClass().getDeclaredMethods();

                    for (Method met : methods ) {

                        Annotation[] anMs  = met.getAnnotations();

                        for (Annotation anM : anMs) {
                            if (anM.annotationType().getName().equals(InitService.class.getName())){

                                boolean accessible = met.isAccessible();
                                met.setAccessible(true);
                                try {
                                    met.invoke(serv);
                                    met.setAccessible(accessible);
                                }catch (InvocationTargetException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Object getService(Class c){
        return new Object();
    }
}
