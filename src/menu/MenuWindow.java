package menu;

/**
 * Created by cceti on 12.05.2017.
 */
public class MenuWindow {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    String titel;
    String[] auswahl;

    // ============================  Constructors  ===========================79
    public MenuWindow(String titel, String[] auswahl) {
        this.titel = titel;
        this.auswahl = auswahl;
    }

    // ===========================  public  Methods  =========================79
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String[] getAuswahl() {
        return auswahl;
    }

    public void setAuswahl(String[] auswahl) {
        this.auswahl = auswahl;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
