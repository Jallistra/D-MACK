package GUI.UI;

import GUI.actions.AdjustTagAction;
import GUI.actions.DeleteAssignedTagAction;
import GUI.actions.DeleteTagAction;
import GUI.listener.DocumentDialogActionListener;
import GUI.listener.DocumentFilterListener;
import GUI.model.AllTagTableModel;
import GUI.model.AssignedTagTableModel;
import source.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.GridBagConstraints.*;

class CreateDocumentDialog extends AbstractDocumentDialog {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private static final String ADD_TAG_BUTTON = "AddTagButton";
    private static final String CREATE_TAG_BUTTON = "CreateTagButton";
    private static final String CREATE_BUTTON = "CreateButton";
    private static final String FILE_CHOOSER_BUTTON = "FileChooserButton";

    // ============================  Constructors  ===========================79

    CreateDocumentDialog(MainFrame mainFrame) {
        initUI(mainFrame);
    }

    // ===========================  public  Methods  =========================79
    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private void initUI(MainFrame mainFrame) {
        setTitle("Dokument erstellen");
        setModal(true);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints;

        JLabel documentNameLabel = new JLabel("Dokumentenname:");
        constraints = UIUtility.createConstraints(0, 0, 1, 1, NONE);
        constraints.anchor = WEST;
        layout.setConstraints(documentNameLabel, constraints);
        add(documentNameLabel);

        constraints = UIUtility.createConstraints(0, 1, 1, 1, HORIZONTAL);
        constraints.weightx = 100;
        layout.setConstraints(documentNameTextField, constraints);
        add(documentNameTextField);

        assignedTagTableModel = new AssignedTagTableModel(this);
        assignedTagsTable = new JTable(assignedTagTableModel);

        JTableHeader assignedTagsTableHeader = new JTableHeader(assignedTagsTable.getColumnModel());
        assignedTagsTable.setTableHeader(assignedTagsTableHeader);
        assignedTagsTable.setAutoCreateRowSorter(true);
        TableRowSorter<AssignedTagTableModel> assignedTagsSorter = new TableRowSorter<>(assignedTagTableModel);
        assignedTagsTableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int column = assignedTagsTable.columnAtPoint(e.getPoint());
                assignedTagsTable.setRowSorter(assignedTagsSorter);
            }
        });

        JPopupMenu assignedTagsPopupMenu = new JPopupMenu();
        JMenuItem assignedTagsAdjustMenuItem = new JMenuItem(new AdjustTagAction(this, assignedTagsTable));
        assignedTagsAdjustMenuItem.setText("Anpassen");
        assignedTagsPopupMenu.add(assignedTagsAdjustMenuItem);
        JMenuItem assignedTagsDeleteMenuItem = new JMenuItem(new DeleteAssignedTagAction(assignedTagsTable, assignedTagTableModel, null));
        assignedTagsDeleteMenuItem.setText("Löschen");
        assignedTagsPopupMenu.add(assignedTagsDeleteMenuItem);
        assignedTagsTable.setComponentPopupMenu(assignedTagsPopupMenu);

        JScrollPane assignedTableScrollPane = new JScrollPane(assignedTagsTable);
        constraints = UIUtility.createConstraints(0, 2, 1, 4, VERTICAL);
        constraints.anchor = NORTH;
        constraints.weighty = 100;
        layout.setConstraints(assignedTableScrollPane, constraints);
        add(assignedTableScrollPane);

        JLabel documentPathLabel = new JLabel("Dokumentenpfad:");
        constraints = UIUtility.createConstraints(1, 0, 1, 1, NONE);
        constraints.anchor = WEST;
        layout.setConstraints(documentPathLabel, constraints);
        add(documentPathLabel);

        constraints = UIUtility.createConstraints(1, 1, 1, 1, HORIZONTAL);
        constraints.weightx = 100;
        layout.setConstraints(documentPathTextField, constraints);
        add(documentPathTextField);

        DocumentDialogActionListener actionListener = new DocumentDialogActionListener(this, null);

        addTagButton.setEnabled(false);
        layout.setConstraints(addTagButton, UIUtility.createConstraints(1, 2, 1,1, NONE));
        addTagButton.addActionListener(actionListener);
        addTagButton.setActionCommand(ADD_TAG_BUTTON);
        add(addTagButton);

        JLabel searchTagLabel = new JLabel("Tag suchen:");
        constraints = UIUtility.createConstraints(1, 3, 1, 1, NONE);
        constraints.anchor = WEST;
        layout.setConstraints(searchTagLabel, constraints);
        add(searchTagLabel);

        searchTextField.getDocument().addDocumentListener(new DocumentFilterListener(searchTextField, allTagTableModel));
        constraints = UIUtility.createConstraints(1, 4, 1, 1, HORIZONTAL);
        constraints.weightx = 100;
        layout.setConstraints(searchTextField, constraints);
        add(searchTextField);

        createTagButton.addActionListener(actionListener);
        createTagButton.setActionCommand(CREATE_TAG_BUTTON);
        constraints = UIUtility.createConstraints(1, 5, 1, 1, NONE);
        constraints.anchor = NORTH;
        layout.setConstraints(createTagButton, constraints);
        add(createTagButton);

        fileChooserButton.addActionListener(actionListener);
        fileChooserButton.setActionCommand(FILE_CHOOSER_BUTTON);
        constraints = UIUtility.createConstraints(2, 1, 1, 1, NONE);
        constraints.anchor = WEST;
        layout.setConstraints(fileChooserButton, constraints);
        add(fileChooserButton);

        allTagTable = new JTable(allTagTableModel);

        allTagTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                addTagButton.setEnabled(!allTagTable.getSelectionModel().isSelectionEmpty());
            }
        });

        JTableHeader allTagsTableHeader = new JTableHeader(allTagTable.getColumnModel());
        allTagTable.setTableHeader(allTagsTableHeader);
        allTagTable.setAutoCreateRowSorter(true);
        TableRowSorter<AllTagTableModel> allTagsSorter = new TableRowSorter<AllTagTableModel>(allTagTableModel);
        allTagsTableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int column = allTagTable.columnAtPoint(e.getPoint());
                allTagTable.setRowSorter(allTagsSorter);
            }
        });

        JPopupMenu allTagsPopupMenu = new JPopupMenu();
        JMenuItem adjustMenuItem = new JMenuItem(new AdjustTagAction(this, allTagTable));
        adjustMenuItem.setText("Anpassen");
        allTagsPopupMenu.add(adjustMenuItem);
        JMenuItem deleteMenuItem = new JMenuItem(new DeleteTagAction(allTagTable, this));
        deleteMenuItem.setText("Löschen");
        allTagsPopupMenu.add(deleteMenuItem);
        allTagTable.setComponentPopupMenu(allTagsPopupMenu);
        JScrollPane allTagsScrollPane = new JScrollPane(allTagTable);
        constraints = UIUtility.createConstraints(2, 2, 1, 4, VERTICAL);
        constraints.anchor = NORTH;
        constraints.weighty = 100;
        layout.setConstraints(allTagsScrollPane, constraints);
        add(allTagsScrollPane);

        createButton.addActionListener(actionListener);
        createButton.setActionCommand(CREATE_BUTTON);
        constraints = UIUtility.createConstraints(2, 6, 0, 0, NONE);
        constraints.anchor = SOUTHEAST;
        layout.setConstraints(createButton, constraints);
        add(createButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Data.getInstance().updateModel();
                CreateDocumentDialog.this.dispose();
            }
        });
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
