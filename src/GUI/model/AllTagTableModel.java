package GUI.model;

import source.Data;
import source.ModelChangeListener;
import source.Tag;

import java.util.ArrayList;
import java.util.List;

public class AllTagTableModel extends AbstractFilteredTableModel implements ModelChangeListener {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private List<Tag> filteredList = new ArrayList<>();

    // ============================  Constructors  ===========================79

    public AllTagTableModel() {
        Data.getInstance().addListener(this);
    }

    // ===========================  public  Methods  =========================79

    @Override
    public String getColumnName(int column) {
        return "Alle Tags";
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
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (isFiltered()) {
            return filteredList.get(rowIndex);
        }
        else {
            return Data.getInstance().getTagList().get(rowIndex);
        }
    }

    @Override
    public void modelChanged() {
        fireTableDataChanged();     // benachrichtigt alle Listener
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

    public void removeFromFilteredList(Tag tag) {
        filteredList.remove(tag);
        fireTableDataChanged();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
