package GUI.actions;

import GUI.UI.UIUtility;
import source.Data;
import source.Tag;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;

public class AdjustTagAction extends AbstractAction {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JDialog parent;
    private JTable table;

    // ============================  Constructors  ===========================79

    public AdjustTagAction(JDialog parent, JTable table) {
        this.parent = parent;
        this.table = table;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        selection = table.convertRowIndexToModel(selection);
        if (selection > -1) {
           Tag tag = (Tag) table.getModel().getValueAt(selection, 0);
           String tagName = UIUtility.getTagName(parent, tag.getTagName());

           if (tagName != null) {
               if (!Data.getInstance().renameTag(tag, tagName)) {
                   JOptionPane.showMessageDialog(parent, "Das Tag kann nicht in " + tagName + " umbenannt werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
               }
           }
           ((AbstractTableModel) table.getModel()).fireTableDataChanged();
       }

    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
