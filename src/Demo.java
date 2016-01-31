import javax.swing.tree.TreeNode;
import java.io.Console;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by admin on 31.01.2016.
 */
public class Demo {

    public static void main(String[] args) {

        printClassInfo(Transaction.class);
        printClassMethod(Transaction.class);
        printClassFields(Transaction.class);
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

    public static void initClass(Class<?> clazz, Map<String, Objects> map) {

        Constructor[] ctors = clazz.getDeclaredConstructors();
        



    }

}
