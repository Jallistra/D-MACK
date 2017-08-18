package GUI.actions;

import GUI.UI.MainFrame;
import GUI.model.DocumentTableModel;
import source.Data;
import source.Document;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;

public class DeleteDocumentAction extends AbstractAction {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTable table;
    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    public DeleteDocumentAction(JTable table, MainFrame mainFrame) {
        this.table = table;
        this.mainFrame = mainFrame;

    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        selection = table.convertRowIndexToModel(selection);
        if (selection > -1) {
            int result = JOptionPane.showConfirmDialog(mainFrame, "Wollen Sie wirklich mit dem Löschen fortfahren?", "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                Document document = ((DocumentTableModel) table.getModel()).getDocument(selection);
                Data.getInstance().deleteDocument(document);
                ((DocumentTableModel) table.getModel()).removeFromFilteredList(document);
            }
            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
            ((AbstractTableModel) mainFrame.getDocumentPanel().getDocumentTable().getModel()).fireTableStructureChanged();
            ((AbstractTableModel) mainFrame.getTagPanel().getDocumentTable().getModel()).fireTableStructureChanged();
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
