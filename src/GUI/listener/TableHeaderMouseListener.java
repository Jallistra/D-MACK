package GUI.listener;

import GUI.model.DocumentTableModel;
import source.DateComparator;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// TODO Joe: 17.08.2017 Im TagPanel fuer die Tags TableRowSorter implementieren
public class TableHeaderMouseListener extends MouseAdapter {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private JTable table;
    private TableRowSorter<DocumentTableModel> columnSorter;

    // ============================  Constructors  ===========================79

    public TableHeaderMouseListener(JTable table, TableRowSorter<DocumentTableModel> columnSorter) {
        this.table = table;
        this.columnSorter = columnSorter;
    }


    // ===========================  public  Methods  =========================79

    @Override
    public void mouseReleased(MouseEvent e) {
        int column = table.columnAtPoint(e.getPoint());
        table.setRowSorter(columnSorter);
        if (column != 0) {
            columnSorter.setComparator(column, new DateComparator());
            columnSorter.sort();
        }
    }


    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
