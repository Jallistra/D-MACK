package GUI.UI;

import GUI.listener.MenuListener;
import GUI.listener.ShutDownListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Das Frame, auf dem die Panels abgebildet werden
 */
public class MainFrame extends JFrame {
    // =========================== Class Variables ===========================79

    public static final String DOCUMENT = "DOCUMENT";
    public static final String TAG = "TAG";
    public static final String EXIT = "EXIT";

    // =============================  Variables  =============================79

    private JMenuBar mainBar;
    private DocumentPanel documentPanel;
    private TagPanel tagPanel;
    private MainPanel mainPanel;

    // ============================  Constructors  ===========================79

    public MainFrame() {
        createMenuBar();
        setJMenuBar(mainBar);
        mainBar.setVisible(false);

        mainPanel = new MainPanel(this);
        getContentPane().add(mainPanel);

        addWindowListener(new ShutDownListener(this));
        setTitle("D-MACK - Hauptmenü");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        d = new Dimension((int) (d.width/1.5), (int) (d.height/1.5));
        setSize(d);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    // ===========================  public  Methods  =========================79

    public void activateDocumentPanel() {
        mainPanel.activateDocumentPanel();
        mainBar.setVisible(true);
    }

    public void activateTagPanel() {
        mainPanel.activateTagPanel();
        mainBar.setVisible(true);
    }


    public DocumentPanel getDocumentPanel() {
        return documentPanel;
    }

    public void setDocumentPanel(DocumentPanel documentPanel) {
        this.documentPanel = documentPanel;
    }

    public TagPanel getTagPanel() {
        return tagPanel;
    }

    public void setTagPanel(TagPanel tagPanel) {
        this.tagPanel = tagPanel;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

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
