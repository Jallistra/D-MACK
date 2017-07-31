package GUI;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

abstract class FilteredTableModel extends AbstractTableModel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private boolean isFiltered = false;

    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79
    // =================  protected/package local  Methods ===================79

    abstract void updateFilteredList(String text);

    void setFiltered(boolean isFiltered) {
        this.isFiltered = isFiltered;
    }

    protected boolean isFiltered() {
        return isFiltered;
    }

    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
