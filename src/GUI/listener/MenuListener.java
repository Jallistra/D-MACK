package GUI.listener;

import GUI.UI.MainFrame;
import source.Data;
import source.DMACK;
import source.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuListener implements ActionListener {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    public MenuListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainFrame.DOCUMENT)) {
            mainFrame.activateDocumentPanel();
        }
        else if (e.getActionCommand().equals(MainFrame.TAG)) {
            mainFrame.activateTagPanel();
        }
        else if (e.getActionCommand().equals(MainFrame.EXIT)) {
            int result = JOptionPane.showConfirmDialog(mainFrame, "Wollen Sie Ihre Änderungen speichern?",
                    "Speichern", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    Serializer.save(Data.getInstance(), DMACK.SAVE_PATH);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(mainFrame, "Leider konnte nicht gespeichert werden.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (result != JOptionPane.CLOSED_OPTION) {
                mainFrame.dispose();
                System.exit(0);
            }
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
