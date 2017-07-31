package GUI;

import javax.swing.*;
import java.awt.*;

class StartPanel extends JPanel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79

    StartPanel(MainFrame mainFrame) {

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        MenuListener menuListener = new MenuListener(mainFrame);

        JButton documentButton = new JButton("Dokumente");
        layout.setConstraints(documentButton, constraints);
        add(documentButton);
        documentButton.setActionCommand(MainFrame.DOCUMENT);
        documentButton.addActionListener(menuListener);

        JButton tagButton = new JButton("Tag");
        layout.setConstraints(tagButton, constraints);
        add(tagButton);
        tagButton.setActionCommand(MainFrame.TAG);
        tagButton.addActionListener(menuListener);

        JButton endButton = new JButton("Programm Beenden");
        layout.setConstraints(endButton, constraints);
        add(endButton);
        tagButton.setActionCommand(MainFrame.EXIT);
        endButton.addActionListener(menuListener);
    }

    // ===========================  public  Methods  =========================79
    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
