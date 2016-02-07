package service;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationManager {

    public <T> T newService(Class<T> c)throws InstantiationException, IllegalAccessException, InvocationTargetException{

        T service = null;

        Annotation[] annos = c.getAnnotations();
        for (Annotation an : annos) {
            if (an.annotationType().getName().equals(Service.class.getName())) {
                return (T) c.getDeclaredConstructors()[0].newInstance();
            }
        }

        return service;
    }

    private <T> void invokeMethod(T service) throws IllegalAccessException {

        Method[] methods = service.getClass().getDeclaredMethods();

        for (Method met : methods) {

            Annotation[] anMs = met.getAnnotations();

            for (Annotation anM : anMs) {
                if (anM.annotationType().getName().equals(InitService.class.getName())) {

                    boolean accessible = met.isAccessible();
                    met.setAccessible(true);
                    try {

                        met.invoke(service);
                        met.setAccessible(accessible);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

   public <T> T getService(Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException{
       return newService(clazz);
   }

}
