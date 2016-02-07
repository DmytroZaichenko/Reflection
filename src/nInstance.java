import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

public class Ninstance {


    public Ninstance() {

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("idCustomer",23);
        hm.put("idProduct",45);
        hm.put("count",2.0);
        Object t =  new InitClass<Transaction>(Transaction.class, hm);

    }

    public static void main(String[] args) {

    }



    public class InitClass <T>{

        private T t;

        public InitClass(Class<T> c, HashMap<String, Object> map) {
            t  = null;
            Constructor[] ctors = c.getDeclaredConstructors();
            Constructor ctor = ctors[0];
            try {
                t = (T) ctor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            for (Field field : c.getDeclaredFields()) {
                String nameField = field.getName();
                Object o = map.get(nameField);
                if (o != null){

                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    try {
                        field.set(t , o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    field.setAccessible(accessible);
                }
            }
        }

        public T getT(){
            return t;
        }

    }

}
