import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class NinstanceTests {

    private Ninstance ninstance = null;
    private HashMap<String, Object> hm;

    @Before
    public void init(){
        ninstance = new Ninstance();

        hm = new HashMap<>();
        hm.put("idCustomer",23);
        hm.put("idProduct",45);
        hm.put("count",2.0);
    }

    @Test
    public void checkReturnInstance() throws Exception{


        Object t = ninstance.new InitClass<Transaction>(Transaction.class,hm).getT();
        assertNotNull("is null ",t);

    }

    @Test
    public void checkPrivatesField(){

        Object t = ninstance.new InitClass<Transaction>(Transaction.class,hm).getT();

        for (HashMap.Entry<String,Object> entry : hm.entrySet() ) {
            String key = entry.getKey();
            Object obj = entry.getValue();
            if (obj != null){
                try {
                    Field field = t.getClass().getDeclaredField(key);
                    try {
                        boolean accessible = field.isAccessible();
                        field.setAccessible(true);
                        Object objF = field.get(key);
                        assertEquals("not equals fields "+obj.toString() +" != " + obj.toString(),objF, obj );
                        field.setAccessible(accessible);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
