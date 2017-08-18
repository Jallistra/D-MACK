package GUI.UI;

import GUI.actions.AdjustDocumentAction;
import GUI.actions.DeleteDocumentAction;
import GUI.actions.OpenDocumentAction;
import GUI.listener.DocumentFilterListener;
import GUI.listener.TableHeaderMouseListener;
import GUI.model.AllTagTableSelectionModel;
import GUI.model.DocumentTableModel;
import source.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.*;

public class TagPanel extends JPanel {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private MainFrame mainFrame;
    private JTable documentTable;
    private static final String CREATE_TAG_BUTTON = "CreateTagButton";

    // ============================  Constructors  ===========================79

    public TagPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints;

        JLabel inputTagSearchLabel = new JLabel("Tag suchen:");
        constraints = UIUtility.createConstraints(0,0,1,1,NONE);
        constraints.anchor = WEST;
        layout.setConstraints(inputTagSearchLabel, constraints);
        add(inputTagSearchLabel);

        JTextField inputTagSearchTextField = new JTextField();
        constraints = UIUtility.createConstraints(0,1,1,1,HORIZONTAL);
        layout.setConstraints(inputTagSearchTextField,constraints);
        add(inputTagSearchTextField);

        JButton createTagButton = new JButton("Tag erstellen");
        createTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIUtility.createTag(TagPanel.this,inputTagSearchTextField.getText());
                inputTagSearchTextField.setText("");
            }
        });
        createTagButton.setActionCommand(CREATE_TAG_BUTTON);
        constraints = UIUtility.createConstraints(0,2,1,1,NONE);
        constraints.anchor = WEST;
        layout.setConstraints(createTagButton, constraints);
        add(createTagButton);

        AllTagTableSelectionModel allTagTableSelectionModel = new AllTagTableSelectionModel();
        inputTagSearchTextField.getDocument().addDocumentListener(new DocumentFilterListener(inputTagSearchTextField, allTagTableSelectionModel));
        JTable allTagTable = new JTable(allTagTableSelectionModel);
        allTagTable.getColumnModel().getColumn(0).setMinWidth(30);
        allTagTable.getColumnModel().getColumn(0).setMaxWidth(30);
        JScrollPane allTagScrollPane = new JScrollPane(allTagTable);
        constraints = UIUtility.createConstraints(0,3,1,2,VERTICAL);
        constraints.anchor = NORTH;
        constraints.weighty = 100;
        layout.setConstraints(allTagScrollPane, constraints);
        add(allTagScrollPane);

        JPopupMenu allTagsPopupMenu = new JPopupMenu();
        JMenuItem adjustTagMenuItem = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = allTagTable.getSelectionModel().getMinSelectionIndex();
                Tag tag = (Tag) allTagTable.getModel().getValueAt(selection,1);
                String tagName = UIUtility.getTagName(TagPanel.this, tag.getTagName());

                if (tagName != null) {
                    if (!Data.getInstance().renameTag(tag, tagName)) {
                        JOptionPane.showMessageDialog(TagPanel.this, "Das Tag kann nicht in " + tagName + " umbenannt werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
                ((AbstractTableModel) allTagTable.getModel()).fireTableDataChanged();
            }
        });
        adjustTagMenuItem.setText("Anpassen");
        allTagsPopupMenu.add(adjustTagMenuItem);
        JMenuItem deleteTagMenuItem = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(TagPanel.this,"Wollen Sie wirklich mit dem Löschen fortfahren?","Löschen bestätigen", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                List<Tag> tagsToDelete = new ArrayList<>();
                for (int i = 0; i < allTagTable.getModel().getRowCount(); i++) {
                    if (allTagTable.getSelectionModel().isSelectedIndex(i)) {
                        tagsToDelete.add((Tag) allTagTable.getValueAt(i,1));
                    }
                }

                for (Tag tag : tagsToDelete) {
                    Data.getInstance().deleteTag(tag);
                }
                for (int i = 0; i < Data.getInstance().getDocList().size(); i++) {
                    for (Tag tag : tagsToDelete) {
                        Data.getInstance().getDocList().get(i).removeTag(tag);
                    }
                }
                ((AbstractTableModel) allTagTable.getModel()).fireTableDataChanged();
            }
        }});
        deleteTagMenuItem.setText("Löschen");
        allTagsPopupMenu.add(deleteTagMenuItem);
        allTagTable.setComponentPopupMenu(allTagsPopupMenu);

        DocumentTableModel documentTableModel = new DocumentTableModel();
        Data.getInstance().addListener(documentTableModel);
        documentTable = new JTable(documentTableModel,null);

        allTagTableSelectionModel.addSelectionListener(new AllTagTableSelectionModel.SelectionListener() {
            @Override
            public void fireSelectionChanged() {
                if (allTagTableSelectionModel.containsSelection()) {
                    documentTableModel.setFiltered(true);
                    documentTableModel.updateFilteredList(allTagTableSelectionModel.getSelection());
                    documentTableModel.fireTableDataChanged();
                }
                else {
                    documentTableModel.setFiltered(false);
                    documentTableModel.fireTableDataChanged();
                }
            }
        });

        documentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if (e.getClickCount() == 2) {
                    try {
                        Data.getInstance().getDocList().get(row).openDoc(TagPanel.this);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Das Dokument konnte nicht geöffnet werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JTableHeader header = new JTableHeader(documentTable.getColumnModel());
        documentTable.setTableHeader(header);
        documentTable.setAutoCreateRowSorter(true);
        TableRowSorter<DocumentTableModel> documentColumnSorter = new TableRowSorter<>(documentTableModel);
        header.addMouseListener(new TableHeaderMouseListener(documentTable, documentColumnSorter));

        JScrollPane scrollPane = new JScrollPane(documentTable);
        constraints = UIUtility.createConstraints(3,0,4,5,BOTH);
        constraints.weightx = 100;
        constraints.weighty = 100;
        layout.setConstraints(scrollPane, constraints);
        add(scrollPane);

        JPopupMenu documentTablePopupMenu = new JPopupMenu();
        JMenuItem openDocumentMenuItem = new JMenuItem(new OpenDocumentAction(this,documentTable));
        openDocumentMenuItem.setText("Öffnen");
        documentTablePopupMenu.add(openDocumentMenuItem);
        JMenuItem adjustDocumentMenuItem = new JMenuItem(new AdjustDocumentAction(documentTable, mainFrame));
        adjustDocumentMenuItem.setText("Anpassen");
        documentTablePopupMenu.add(adjustDocumentMenuItem);
        JMenuItem deleteDocumentMenuItem = new JMenuItem(new DeleteDocumentAction(documentTable, mainFrame));
        deleteDocumentMenuItem.setText("Löschen");
        documentTablePopupMenu.add(deleteDocumentMenuItem);
        documentTable.setComponentPopupMenu(documentTablePopupMenu);
        documentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // ===========================  public  Methods  =========================79

    public JTable getDocumentTable() {
        return documentTable;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
