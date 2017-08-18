package GUI.listener;

import GUI.UI.MainFrame;
import source.Data;
import source.DMACK;
import source.Serializer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShutDownListener extends WindowAdapter {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    public ShutDownListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(mainFrame, "Wollen Sie Ihre Änderungen speichern?",
                "Speichern", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                Serializer.save(Data.getInstance(), DMACK.SAVE_PATH);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(mainFrame, "Leider konnte nicht gespeichert werden.",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (result != JOptionPane.CLOSED_OPTION) {
            mainFrame.dispose();
            System.exit(0);
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
