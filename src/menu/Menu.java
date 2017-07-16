package menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by cceti on 10.05.2017.
 */
public class Menu {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    private ArrayList<MenuWindow> windowList = new ArrayList<MenuWindow>();
    private ArrayList<MenuAction> actionListe = new ArrayList<MenuAction>();
    private boolean finished = false;
    private String input;
    private int pos = 0;
    private int posAction;


    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79
    // Fügt der ArrayList ein MenüFenster-Objekt hinzu
    public void add(MenuWindow fenster) {
        windowList.add(fenster);
    }

    // Fügt der ArrayList ein MenüAktion-Objekt hinzu
    public void add(MenuAction aktion) {
        actionListe.add(aktion);
    }

    // Startet das Menü auf der Konsole
    public void start() {
        while(!finished) {
            navigation();
            compare();
        }

    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // Enthält die Logik der Navigation durch die Konsole
    private void navigation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("==== " + windowList.get(pos).getTitel() + " ====");
        for (int i = 0; i<windowList.get(pos).getAuswahl().length; i++) {
            System.out.println("     " + (i+1) + ". " + windowList.get(pos).getAuswahl()[i]);
        }
        System.out.println();
        System.out.println("Weitere Befehle: b = zurück     e = Programm beenden ");
        System.out.println();
        System.out.print("Auswahl: ");
        input = scan.next();
        System.out.println();

    }

    // Enthält die Logik der Auswertung der Eingaben auf der Konsole
    private void compare() {
        int posTemp = 0;
        for(int i = 0; i<windowList.get(pos).getAuswahl().length; i++) {
            if(input.equals(windowList.get(pos).getAuswahl()[i]) && !aktion(input)) {
                for(int j = 0; j<windowList.size(); j++) {
                    if(input.equals(windowList.get(j).getTitel())) {
                        posTemp = pos;
                        pos = j;
                    }
                }
            }
            if(input.equals(windowList.get(pos).getAuswahl()[i]) && aktion(input)) {
                actionListe.get(posAction).action();
            }
            if(input.equals("b") && pos != 0) {
                pos = posTemp;
            }
            if(input.equals("e")) {
                finished = true;
            }
        }
    }

    // Überprüft, ob die Eingabe ein MenüAktion-Objekt ist
    private boolean aktion(String eingabe) {
        for (int i = 0; i< actionListe.size(); i++) {
            if(eingabe.equals(actionListe.get(i).getTitel())) {
                posAction = i;
                return true;
            }
        }
        return false;
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}

