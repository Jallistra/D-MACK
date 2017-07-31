package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Das Frame, auf dem die Panels abgebildet werden
 */
public class MainFrame extends JFrame {
    // =========================== Class Variables ===========================79

    static final String DOCUMENT = "DOCUMENT";
    static final String TAG = "TAG";
    static final String EXIT = "EXIT";

    // =============================  Variables  =============================79

    private JMenuBar mainBar;

    private MainPanel mainPanel;

    // ============================  Constructors  ===========================79

    MainFrame() {
        createMenuBar();
        setJMenuBar(mainBar);
        mainBar.setVisible(false);              // MenuBar ist im MainPanel nicht sichtbar

        mainPanel = new MainPanel(this);
        getContentPane().add(mainPanel);

        setTitle("D-MACK - Hauptmenü");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();      // nimmt die Aufloesung vom Bildschirm,
        d = new Dimension(d.width/2,d.height/2);          // halbiert sie und
        setSize(d);                                                     // setzt das Frame auf die Groesse
        setMinimumSize(getSize());
        setLocationRelativeTo(null);                    //Fenster soll in der Mitte erscheinen
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // ===========================  public  Methods  =========================79

    public static void main(String[] args) {
        new MainFrame();
    }

    // ruft die activateDocumentPanel Methode von MainPanel auf, um auf das DocumentPanel zu wechseln
    public void activateDocumentPanel() {
        mainPanel.activateDocumentPanel();
        mainBar.setVisible(true);
    }

    // ruft die activateTagPanel Methode von MainPanel auf, um auf das TagPanel zu wechseln
    public void activateTagPanel() {
        mainPanel.activateTagPanel();
        mainBar.setVisible(true);
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    // Erzeugt die MenuBar
    private void createMenuBar() {
        MenuListener menuListener = new MenuListener(this);

        mainBar = new JMenuBar();
        JMenu menu = new JMenu("Menü");
        menu.setMnemonic(KeyEvent.VK_M);

        JMenuItem docMenuitem = new JMenuItem("Dokumente");
        docMenuitem.setMnemonic(KeyEvent.VK_D);
        docMenuitem.setActionCommand(DOCUMENT);
        docMenuitem.addActionListener(menuListener);
        menu.add(docMenuitem);

        JMenuItem tagMenuItem = new JMenuItem("Tags");
        tagMenuItem.setMnemonic(KeyEvent.VK_T);
        tagMenuItem.setActionCommand(TAG);
        tagMenuItem.addActionListener(menuListener);
        menu.add(tagMenuItem);

        JMenuItem endMenuItem = new JMenuItem("Programm beenden");
        endMenuItem.setMnemonic(KeyEvent.VK_E);
        endMenuItem.setActionCommand(EXIT);
        endMenuItem.addActionListener(menuListener);
        menu.add(endMenuItem);

        mainBar.add(menu);
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
