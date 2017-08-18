package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuListener implements ActionListener {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    MenuListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // ===========================  public  Methods  =========================79

    // Legt fest, was beim druecken auf DOCUMENT, TAG und EXIT passieren soll
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainFrame.DOCUMENT)) {
            mainFrame.activateDocumentPanel();
        }
        else if (e.getActionCommand().equals(MainFrame.TAG)) {
            mainFrame.activateTagPanel();
        }
        else if (e.getActionCommand().equals(MainFrame.EXIT)) {
//            TODO Joe: 23.07.2017 speichern muss noch ergaenzt werden
            mainFrame.dispose();
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
