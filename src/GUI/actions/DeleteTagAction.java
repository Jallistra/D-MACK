package GUI.actions;

import source.Control;
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

    private DeleteCallback callback;

    // ============================  Constructors  ===========================79

    public DeleteTagAction(JTable table, DeleteCallback callback) {
        this.table = table;
        this.callback = callback;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Tag> tagsToDelete = new ArrayList<>();
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (table.getSelectionModel().isSelectedIndex(i)) {
                tagsToDelete.add((Tag) table.getValueAt(i, 0));
            }
        }

        for (Tag tag : tagsToDelete) {
            Control.getInstance().deleteTag(tag);
        }
        if (callback != null) {
            callback.callback(tagsToDelete);
        }
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79

    public interface DeleteCallback {
        void callback(List<Tag> removedTags);
    }

    // ============================  End of class  ===========================79
}
