package source;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by cceti on 30.05.2017.
 */

public class Date implements Comparable<Date> {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private int dDay;
    private int dMonth;
    private int dYear;
    private int dHour;
    private int dMinute;
    private int dSecond;

    // ============================  Constructors  ===========================79

    public Date() {
    }

    public Date(int day, int month, int year, int hour, int minute, int second) {
        this.dDay = day;
        this.dMonth = month;
        this.dYear = year;
        this.dHour = hour;
        this.dMinute = minute;
        this.dSecond = second;
    }

    // ===========================  public  Methods  =========================79

    public String print() {
        String output;
        if(dDay >31 || dMonth >12)
            return output = "Ungültige Eingabe!";
        else
            return output = dHour + ":" + dMinute + ":" + dSecond + "  " + dDay + "." + dMonth + "." + dYear;
    }

    public void init() {
        GregorianCalendar cal = new GregorianCalendar();
        dDay = cal.get(Calendar.DATE);
        dMonth = cal.get(Calendar.MONTH)+1;
        dYear = cal.get(Calendar.YEAR);
        dHour = cal.get(Calendar.HOUR_OF_DAY);
        dMinute = cal.get(Calendar.MINUTE);
        dSecond = cal.get(Calendar.SECOND);
        //System.out.println(dDay + "." + dMonth + "." + dYear);
    }

    @Override
    public int compareTo(Date d) {
        if(dYear > d.dYear) return 1;
        if(dYear < d.dYear) return -1;
        if(dMonth > d.dMonth) return 1;
        if(dMonth < d.dMonth) return -1;
        if(dDay > d.dDay) return 1;
        if(dDay < d.dDay) return -1;
        if(dHour > d.dHour) return 1;
        if(dHour < d.dHour) return -1;
        if(dMinute > d.dMinute) return 1;
        if(dMinute < d.dMinute) return -1;
        if(dSecond > d.dSecond) return 1;
        if(dSecond < d.dSecond) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return dHour + ":" + dMinute + ":" + dSecond + "  " + dDay + "." + dMonth + "." + dYear;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
