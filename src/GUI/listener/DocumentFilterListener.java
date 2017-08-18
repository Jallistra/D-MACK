package GUI.listener;

import GUI.model.AbstractFilteredTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentFilterListener implements DocumentListener {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTextField source;
    private AbstractFilteredTableModel tableModel;

    // ============================  Constructors  ===========================79

    public DocumentFilterListener(JTextField source, AbstractFilteredTableModel tableModel) {
        this.source = source;
        this.tableModel = tableModel;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void insertUpdate(DocumentEvent e) {
        filterModel();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filterModel();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private void filterModel() {
        String searchText = source.getText();
        if (searchText == null || searchText.length() == 0) {
            tableModel.setFiltered(false);
            tableModel.fireTableDataChanged();
        }
        else {
            tableModel.setFiltered(true);
            tableModel.updateFilteredList(searchText);
            tableModel.fireTableDataChanged();
        }
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
