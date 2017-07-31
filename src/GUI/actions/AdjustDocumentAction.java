package GUI.actions;

import source.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdjustDocumentAction extends AbstractAction {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JPanel parent;
    private JTable table;
    // ============================  Constructors  ===========================79

    public AdjustDocumentAction(JPanel parent, JTable table) {
        this.parent = parent;
        this.table = table;
    }
    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        Document document = (Document) table.getModel().getValueAt(selection, 0);
       // String documentName =
    }


    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
