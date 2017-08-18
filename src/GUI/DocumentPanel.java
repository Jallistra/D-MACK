package GUI;

import GUI.actions.AdjustDocumentAction;
import source.Control;
import source.DateComparator;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DocumentPanel extends JPanel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;

    // ============================  Constructors  ===========================79

    DocumentPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constrains;

        JLabel inputDocSearchLabel = new JLabel("Bitte geben Sie einen Suchbegriff ein: ");
        constrains = UIUtility.createConstraints(0,0,1,1, GridBagConstraints.NONE);
        layout.setConstraints(inputDocSearchLabel,constrains);
        add(inputDocSearchLabel);

        JTextField inputDocSearchTextField = new JTextField();
        constrains = UIUtility.createConstraints(0,1,1,1, GridBagConstraints.HORIZONTAL);
        layout.setConstraints(inputDocSearchTextField, constrains);
        add(inputDocSearchTextField);

        JButton addDocButton = new JButton("Dokument hinzufügen");
        addDocButton.addActionListener(addDocumentListener);
        constrains = UIUtility.createConstraints(0,2,1,1, GridBagConstraints.NONE);
        constrains.anchor = GridBagConstraints.NORTH;
        layout.setConstraints(addDocButton,constrains);
        add(addDocButton);

        DocumentModel model = new DocumentModel();
        inputDocSearchTextField.getDocument().addDocumentListener(new DocumentFilterListener(inputDocSearchTextField, model));
        Control.getInstance().addListener(model);
        JTable docTable = new JTable(model, null);

        JTableHeader header = new JTableHeader(docTable.getColumnModel());
        docTable.setTableHeader(header);
        docTable.setAutoCreateRowSorter(true);
        TableRowSorter<DocumentModel> sorter = new TableRowSorter<>(model);     // Zum sortieren nach Spalten
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                sortColumns(e, docTable, sorter);
            }
        });

        JScrollPane scrollPane = new JScrollPane(docTable);
        constrains = UIUtility.createConstraints(2, 0, 4, 4, GridBagConstraints.BOTH);
        constrains.weightx = 100;
        constrains.weighty = 100;
        layout.setConstraints(scrollPane, constrains);
        add(scrollPane);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem adjustMenuItem = new JMenuItem(new AdjustDocumentAction( this, docTable));
        adjustMenuItem.setText("Anpassen");
        popupMenu.add(adjustMenuItem);
    }

    // ===========================  public  Methods  =========================79
    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private void sortColumns(MouseEvent e, JTable docTable, TableRowSorter<DocumentModel> sorter) {
        int column = docTable.columnAtPoint(e.getPoint());
        docTable.setRowSorter(sorter);
        if (column != 0) {
            sorter.setComparator(column, new DateComparator());
            sorter.sort();
        }
    }

    // ============================  Inner Classes  ==========================79

    private ActionListener addDocumentListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DocumentDialogCreate(true, mainFrame);
        }
    };

    private ActionListener searchDocumentListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    // ============================  End of class  ===========================79
}
