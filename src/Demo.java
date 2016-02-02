import javax.swing.tree.TreeNode;
import java.io.Console;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by admin on 31.01.2016.
 */


public class Demo {

    public static void main(String[] args) {

//        printClassInfo(Transaction.class);
//        printClassMethod(Transaction.class);
//        printClassFields(Transaction.class);
        Date d = getDate(0);
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("idCustomer",23);
        hm.put("idProduct",45);
        hm.put("date",d);
        hm.put("count",2.0);

        Transaction t = initClass(Transaction.class,hm);

        System.out.println(t.toString());



    }

    public static void printClassInfo(Class<?> c){

        Class<?> scl = c.getSuperclass();

        System.out.println("name class: " + c.getName());
        System.out.println("canonical name class: " + c.getCanonicalName());
        System.out.println("type nameclass: " + c.getTypeName());

        if (scl != null){
            printClassInfo(scl);
        }
    }

    public static Date getDate(int countOfDate){

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR,countOfDate);

        c.set(c.HOUR_OF_DAY,0);
        c.set(c.MINUTE,0);
        c.set(c.SECOND,0);
        c.set(c.MILLISECOND,0);

        return c.getTime();
    }

    public static void printClassMethod(Class<?> c){

        Class<?> scl = c.getSuperclass();

        Method[] methods =  c.getDeclaredMethods();
        System.out.println("Methods class " + c.getName());
        System.out.println("==================================");
        for (Method m : methods ) {
            System.out.println(m.toString());
        }
        System.out.println("==================================");
        if (scl != null){
            printClassMethod(scl);
        }

    }

    public static void printClassFields(Class<?> c){

        Class<?> scl = c.getSuperclass();

        System.out.println("Class fields " + c.getName());
        System.out.println("==================================");
        Field[] fields = c.getDeclaredFields();
        for ( Field f: fields) {
            System.out.println(f.getName());
        }
        System.out.println("==================================");

        if (scl != null){
            printClassFields(scl);
        }
    }

    public static Transaction initClass(Class c, HashMap<String, Object> map) {

        Transaction t = null;
        Constructor[] ctors = c.getDeclaredConstructors();
        Constructor ctor = ctors[0];
        try {
            t = (Transaction) ctor.newInstance();
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

        return t;
    }

}
