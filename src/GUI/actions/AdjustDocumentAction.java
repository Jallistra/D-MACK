package GUI.actions;

import GUI.UI.AdjustDocumentDialog;
import GUI.model.DocumentTableModel;
import GUI.UI.MainFrame;
import source.Document;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;

public class AdjustDocumentAction extends AbstractAction {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTable table;
    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    public AdjustDocumentAction(JTable table, MainFrame mainFrame) {
        this.table = table;
        this.mainFrame = mainFrame;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        selection = table.convertRowIndexToModel(selection);
        if (selection > -1) {
            Document document = ((DocumentTableModel) table.getModel()).getDocument(selection);
            AdjustDocumentDialog dialog = new AdjustDocumentDialog(document, mainFrame);
            dialog.setVisible(true);
            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
        }

    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
