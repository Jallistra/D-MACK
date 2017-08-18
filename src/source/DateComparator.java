package source;

import java.util.Comparator;

public class DateComparator implements Comparator<Date> {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79

    @Override
    public int compare(Date d1, Date d2) {
        return d1.compareTo(d2);
    }


    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
