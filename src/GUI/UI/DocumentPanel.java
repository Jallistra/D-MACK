package GUI.UI;

import GUI.actions.AdjustDocumentAction;
import GUI.actions.DeleteDocumentAction;
import GUI.actions.OpenDocumentAction;
import GUI.listener.DocumentFilterListener;
import GUI.listener.TableHeaderMouseListener;
import GUI.model.DocumentTableModel;
import source.Data;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.GridBagConstraints.*;

public class DocumentPanel extends JPanel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;
    private JTable documentTable;

    // ============================  Constructors  ===========================79

    DocumentPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constrains;

        JLabel inputDocSearchLabel = new JLabel("Bitte geben Sie einen Suchbegriff ein: ");
        constrains = UIUtility.createConstraints(0,0,1,1, NONE);
        layout.setConstraints(inputDocSearchLabel,constrains);
        add(inputDocSearchLabel);

        JTextField inputDocSearchTextField = new JTextField();
        constrains = UIUtility.createConstraints(0,1,1,1, HORIZONTAL);
        layout.setConstraints(inputDocSearchTextField, constrains);
        add(inputDocSearchTextField);

        JButton addDocButton = new JButton("Dokument hinzufügen");
        addDocButton.addActionListener(addDocumentListener);
        constrains = UIUtility.createConstraints(0,2,1,1, NONE);
        constrains.anchor = NORTH;
        layout.setConstraints(addDocButton,constrains);
        add(addDocButton);

        DocumentTableModel model = new DocumentTableModel();
        inputDocSearchTextField.getDocument().addDocumentListener(new DocumentFilterListener(inputDocSearchTextField, model));
        documentTable = new JTable(model, null);

        // Zum Oeffnen von Dokumenten mit einem Doppelklick
        documentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if (e.getClickCount() == 2) {
                    try {
                        Data.getInstance().getDocList().get(row).openDoc(DocumentPanel.this);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Das Dokument konnte nicht geöffnet werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JTableHeader header = new JTableHeader(documentTable.getColumnModel());
        documentTable.setTableHeader(header);
        documentTable.setAutoCreateRowSorter(true);
        TableRowSorter<DocumentTableModel> documentColumnSorter = new TableRowSorter<>(model);
        header.addMouseListener(new TableHeaderMouseListener(documentTable, documentColumnSorter));

        JScrollPane scrollPane = new JScrollPane(documentTable);
        constrains = UIUtility.createConstraints(2, 0, 4, 4, BOTH);
        constrains.weightx = 100;
        constrains.weighty = 100;
        layout.setConstraints(scrollPane, constrains);
        add(scrollPane);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openMenuItem = new JMenuItem(new OpenDocumentAction(this,documentTable));
        openMenuItem.setText("Öffnen");
        popupMenu.add(openMenuItem);
        JMenuItem adjustMenuItem = new JMenuItem(new AdjustDocumentAction(documentTable, mainFrame));
        adjustMenuItem.setText("Anpassen");
        popupMenu.add(adjustMenuItem);
        JMenuItem deleteMenuItem = new JMenuItem(new DeleteDocumentAction(documentTable, mainFrame));
        deleteMenuItem.setText("Löschen");
        popupMenu.add(deleteMenuItem);
        documentTable.setComponentPopupMenu(popupMenu);
        documentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    // ===========================  public  Methods  =========================79

    public JTable getDocumentTable() {
        return documentTable;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private ActionListener addDocumentListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreateDocumentDialog dialog = new CreateDocumentDialog(mainFrame);
            dialog.setVisible(true);
        }
    };

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
