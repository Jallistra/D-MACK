package GUI.actions;

import GUI.model.AssignedTagTableModel;
import source.Document;
import source.Tag;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteAssignedTagAction extends AbstractAction {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTable table;
    private AssignedTagTableModel assignedTagTableModel;
    private Document document;

    // ============================  Constructors  ===========================79

    public DeleteAssignedTagAction(JTable table, AssignedTagTableModel assignedTagTableModel, Document document) {
        this.table = table;
        this.assignedTagTableModel = assignedTagTableModel;
        this.document = document;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Tag> tagsToRemove = new ArrayList<>();
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (table.getSelectionModel().isSelectedIndex(i)) {
                tagsToRemove.add((Tag) table.getValueAt(i, 0));
                ((Tag)table.getValueAt(i, 0)).removeDocument(document);
            }
        }
        assignedTagTableModel.getTags().removeAll(tagsToRemove);
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
