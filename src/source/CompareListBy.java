package source;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Created by avabe on 08.07.2017.
 */
public class CompareListBy implements Comparator<Object> {

    // =========================== Class Variables ===========================79
    private String s;
    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79
    public CompareListBy(String s) {
        this.s = s;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public int compare(Object o1, Object o2) {
        try {
            Class c1 = o1.getClass();
            Method m1 = c1.getMethod(s);
            Date d1 = (Date) m1.invoke(o1);
            Class c2 = o2.getClass();
            Method m2 = c2.getMethod(s);
            Date d2 = (Date) m2.invoke(o2);
            return d1.compareTo(d2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
