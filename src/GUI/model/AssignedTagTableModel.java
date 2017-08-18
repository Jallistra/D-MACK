package GUI.model;

import GUI.UI.UIUtility;
import source.Data;
import source.Document;
import source.ModelChangeListener;
import source.Tag;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AssignedTagTableModel extends AbstractTableModel implements ModelChangeListener{

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private List<Tag> tags = new ArrayList<>();
    private JDialog dialog;

    // ============================  Constructors  ===========================79

    public AssignedTagTableModel(JDialog dialog) {
        Data.getInstance().addListener(this);
        this.dialog = dialog;
    }

    public AssignedTagTableModel(JDialog dialog, Document document) {
        Data.getInstance().addListener(this);
        this.dialog = dialog;
        tags.addAll(document.getAssignedTagList());
    }

    // ===========================  public  Methods  =========================79

    @Override
    public String getColumnName(int column) {
        return "Zugewiesene Tags";
    }

    @Override
    public int getRowCount() {
        return tags.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tags.get(rowIndex);
    }

    @Override
    public void modelChanged() {
        fireTableDataChanged();
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public void addTags(List<Tag> newTags) {
        List<Tag> wrongTags = new ArrayList<>();
        for (Tag tag : newTags) {
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
            else {
                wrongTags.add(tag);
            }
        }
        UIUtility.validateWrongTags(dialog, wrongTags);
        fireTableDataChanged();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
