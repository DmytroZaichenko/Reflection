package inst;

import java.lang.reflect.*;
import java.util.*;

public class Ninstance {


    public Ninstance() {

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("idCustomer",23);
        hm.put("idProduct",45);
        hm.put("count",2.0);



        List<Object> list = new ArrayList<>();
        list.add(new Integer(23));
        list.add(new Integer(45));
        list.add(new Date());
        list.add(new Double(2.4));

        Transaction t = null;
        try {
            t = new InitClass<>(Transaction.class, list, hm).getT();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(t.toString());

    }

    public static void main(String[] args) {
        new Ninstance();
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

            fillField(c, map);
        }

        private void fillField(Class<T> clazz, HashMap<String, Object> hm) {
            for (Field field : clazz.getDeclaredFields()) {
                String nameField = field.getName();
                Object o = hm.get(nameField);
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

        public InitClass(Class<T> c, List<Object> list, HashMap<String, Object> map ) throws Exception {
            t = null;
            Constructor myCons = null;
            Object[] args = new Object[list.size()];

            Constructor[] ctors = c.getDeclaredConstructors();
            for (Constructor ctor : ctors) {

                boolean match = false;
                Type[] types = ctor.getGenericParameterTypes();
                if (list.size()== types.length){


                    for (int i = 0; i < list.size() ; i++) {

                        args[i] = list.get(i);

                        Class need = list.get(i).getClass();
                        Class got = (Class)types[i];
                        if (got.isPrimitive()){

                            match = (int.class.equals(got) && Integer.class.equals(need)) ||
                               (long.class.equals(got) && Long.class.equals(need)) ||
                               (char.class.equals(got) && Character.class.equals(need)) ||
                               (short.class.equals(got) && Short.class.equals(need)) ||
                               (boolean.class.equals(got) && Boolean.class.equals(need)) ||
                               (double.class.equals(got) && Double.class.equals(need)) ||
                               (byte.class.equals(got) && Byte.class.equals(need));

                        }else{
                            match = need.equals(got);
                        }

                        if (! match){
                            args = new String[list.size()];
                            break;
                        }
                    }
                }

                if (match){
                    myCons = ctor;
                    break;
                }
            }

            try {
                t = (T) myCons.newInstance(args);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            fillField(c, map);
        }
    }

}
