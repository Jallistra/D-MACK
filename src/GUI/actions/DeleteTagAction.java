package GUI.actions;

import GUI.UI.AbstractDocumentDialog;
import GUI.model.AllTagTableModel;
import source.Data;
import source.Tag;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteTagAction extends AbstractAction {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTable table;
    private AbstractDocumentDialog parent;

    // ============================  Constructors  ===========================79

    public DeleteTagAction(JTable table, AbstractDocumentDialog parent) {
        this.table = table;
        this.parent = parent;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(parent,"Wollen Sie wirklich mit dem Löschen fortfahren?",
                "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            List<Tag> tagsToDelete = new ArrayList<>();
            for (int i = 0; i < table.getModel().getRowCount(); i++) {
                if (table.getSelectionModel().isSelectedIndex(i)) {
                    tagsToDelete.add((Tag) table.getValueAt(i, 0));
                }
            }
            for (Tag tag : tagsToDelete) {
                Data.getInstance().deleteTag(tag);
                parent.getAssignedTagTableModel().removeTag(tag);
                parent.getAssignedTagTableModel().fireTableDataChanged();
                ((AllTagTableModel)table.getModel()).removeFromFilteredList(tag);
            }
            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
