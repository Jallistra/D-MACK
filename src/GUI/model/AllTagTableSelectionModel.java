package GUI.model;

import source.Data;
import source.ModelChangeListener;
import source.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTagTableSelectionModel extends AbstractFilteredTableModel implements ModelChangeListener {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private List<Tag> filteredList = new ArrayList<>();

    private List<SelectionListener> selectionListener = new ArrayList<>();

    private Map<Tag, Boolean> selectionMap = new HashMap<>();

    // ============================  Constructors  ===========================79

    public AllTagTableSelectionModel() {
        Data.getInstance().addListener(this);
        for (Tag tag : Data.getInstance().getTagList()) {
            selectionMap.put(tag, false);
        }
    }

    // ===========================  public  Methods  =========================79

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "";
        }
        else {
            return "Alle Tags";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        }
        else {
            return Tag.class;
        }
    }

    @Override
    public int getRowCount() {
        if (isFiltered()) {
            return filteredList.size();
        }
        else {
            return Data.getInstance().getTagList().size();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        assert columnIndex == 0;
        Tag tag;
        if (isFiltered()) {
            tag = filteredList.get(rowIndex);
        } else {
            tag = Data.getInstance().getTagList().get(rowIndex);
        }
        selectionMap.put(tag, (Boolean) aValue);
        fireSelectionChanged();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            Tag tag;
            if (isFiltered()) {
                tag = filteredList.get(rowIndex);
            } else {
                tag = Data.getInstance().getTagList().get(rowIndex);
            }
            return selectionMap.get(tag);
        }
        else {
            if (isFiltered()) {
                return filteredList.get(rowIndex);
            } else {
                return Data.getInstance().getTagList().get(rowIndex);
            }
        }
    }

    @Override
    public void modelChanged() {
        fireTableDataChanged();
    }

    public List<Tag> getSelection() {
        List<Tag> selection = new ArrayList<>();
        for (Map.Entry<Tag, Boolean> entry : selectionMap.entrySet()) {
            if (entry.getValue()) {
                selection.add(entry.getKey());
            }
        }
        return selection;
    }

    public boolean containsSelection() {
        for (Boolean isSelected : selectionMap.values()) {
            if (isSelected) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void updateFilteredList(String searchText) {
        filteredList.clear();
        for (Tag tag : Data.getInstance().getTagList()) {
            if (tag.getTagName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(tag);
            }
        }
    }

    @Override
    public void updateFilteredList(List<Tag> tags) {
        throw new IllegalStateException();
    }

    public void addSelectionListener(SelectionListener listener) {
        selectionListener.add(listener);
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private void fireSelectionChanged() {
        for (SelectionListener listener : selectionListener) {
            listener.fireSelectionChanged();
        }
    }

    // ============================  Inner Classes  ==========================79

    public interface SelectionListener {
        void fireSelectionChanged();
    }

    // ============================  End of class  ===========================79
}
