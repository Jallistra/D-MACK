package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Das MainPanel, auf dem das CardLayout verwendet wird, um zwischen den Cards zu wechseln
 */
class MainPanel extends JPanel {
    // =========================== Class Variables ===========================79

    private static final String START_PANEL_NAME = "StartPanel";
    private static final String DOCUMENT_PANEL_NAME = "DocumentPanel";
    private static final String TAG_PANEL_NAME = "TagPanel";

    // =============================  Variables  =============================79

    private MainFrame mainFrame;

    private CardLayout layout;

    // ============================  Constructors  ===========================79
    MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        layout = new CardLayout();
        setLayout(layout);

        add(new StartPanel(mainFrame), START_PANEL_NAME);
        add(new DocumentPanel(mainFrame), DOCUMENT_PANEL_NAME);
        add(new TagPanel(), TAG_PANEL_NAME);

        layout.show(this, START_PANEL_NAME);
    }

    // ===========================  public  Methods  =========================79

    void activateDocumentPanel() {
        layout.show(this, DOCUMENT_PANEL_NAME);
        mainFrame.setTitle("D-MACK - Dokumente");
    }

    void activateTagPanel() {
        layout.show(this, TAG_PANEL_NAME);
        mainFrame.setTitle("D-MACK - Tags");
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
