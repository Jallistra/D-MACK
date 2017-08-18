package GUI.actions;

;

import source.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OpenDocumentAction extends AbstractAction {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JPanel parent;
    private JTable table;

    // ============================  Constructors  ===========================79

    public OpenDocumentAction(JPanel parent, JTable table) {
        this.parent = parent;
        this.table = table;
    }
    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = table.getSelectionModel().getMinSelectionIndex();
        selected = table.convertRowIndexToModel(selected);
        Data.getInstance().getDocList().get(selected).openDoc(parent);
    }


    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
