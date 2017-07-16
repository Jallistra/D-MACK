package source;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cceti on 14.06.2017.
 */
public class HashSetTest {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79
    public static void main(String args[]) {
        // create a hash set
        Set<Tag> hs = new HashSet<>();


        // add elements to the hash set
        Tag tag = new Tag("2017");
        hs.add(tag);
        tag.setTagName("blablaldkf");
        System.out.println(hs.contains(tag));

        System.out.println("Sinus".contains("n"));
        System.out.println("Sinus".equals("n"));

        System.out.println("Sinus".equals("Sinu"));
        System.out.println("Sinus".equals("Sinus"));
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
