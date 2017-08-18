package GUI.model;

import source.Tag;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class AbstractFilteredTableModel extends AbstractTableModel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private boolean isFiltered = false;

    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79

    public abstract void updateFilteredList(String text);

    public abstract void updateFilteredList(List<Tag> tags);

    public void setFiltered(boolean isFiltered) {
        this.isFiltered = isFiltered;
    }

    boolean isFiltered() {
        return isFiltered;
    }

    // =================  protected/package local  Methods ===================79


    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
