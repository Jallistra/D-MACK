package GUI;

import GUI.actions.AdjustTagAction;
import GUI.actions.DeleteTagAction;
import source.Control;
import source.ModelChangeListener;
import source.Tag;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.awt.GridBagConstraints.*;

class DocumentDialogCreate extends JDialog {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private boolean create;

    private JTextField documentNameTextField = new JTextField();
    private JTextField documentPathTextField = new JTextField();

    private JButton addTagButton = new JButton("Tag hinzufügen");
    private JTextField searchTextField = new JTextField();
    private JButton createTagButton = new JButton("Tag erstellen");
    private JButton fileChooserButton = new JButton("...");
    private JButton createButton = new JButton("Erstellen");

    private AssignedTagsModel assignedTagsModel = new AssignedTagsModel();
    private JTable assignedTagsTable = new JTable(assignedTagsModel);
    private AllTagTableModel allTagTableModel = new AllTagTableModel();
    private JTable allTagTable = new JTable(allTagTableModel);

    // ============================  Constructors  ===========================79

    DocumentDialogCreate(boolean create, MainFrame mainFrame) {
        this.create = create;

        initUI(mainFrame);
    }

    // ===========================  public  Methods  =========================79
    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private void initUI(MainFrame mainFrame) {
        setTitle(create ? "Dokument erstellen" : "Dokument bearbeiten");
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

        addTagButton.setEnabled(false);
        layout.setConstraints(addTagButton, UIUtility.createConstraints(1, 2, 1,1, NONE));
        addTagButton.addActionListener(actionListener);
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
        constraints = UIUtility.createConstraints(1, 5, 1, 1, NONE);
        constraints.anchor = NORTH;
        layout.setConstraints(createTagButton, constraints);
        add(createTagButton);

        fileChooserButton.addActionListener(actionListener);
        constraints = UIUtility.createConstraints(2, 1, 1, 1, NONE);
        constraints.anchor = WEST;
        layout.setConstraints(fileChooserButton, constraints);
        add(fileChooserButton);

        allTagTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                addTagButton.setEnabled(!allTagTable.getSelectionModel().isSelectionEmpty());
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem adjustMenuItem = new JMenuItem(new AdjustTagAction(this, allTagTable));
        adjustMenuItem.setText("Anpassen");
        popupMenu.add(adjustMenuItem);
        JMenuItem deleteMenuItem = new JMenuItem(new DeleteTagAction(allTagTable, new DeleteTagAction.DeleteCallback() {
            @Override
            public void callback(List<Tag> removedTags) {
                for (Tag tag : removedTags) {
                    assignedTagsModel.removeTag(tag);
                }
                assignedTagsModel.fireTableDataChanged();
            }
        }));
        deleteMenuItem.setText("Löschen");
        popupMenu.add(deleteMenuItem);
        allTagTable.setComponentPopupMenu(popupMenu);
        JScrollPane allTagsScrollPane = new JScrollPane(allTagTable);
        constraints = UIUtility.createConstraints(2, 2, 1, 4, VERTICAL);
        constraints.anchor = NORTH;
        constraints.weighty = 100;
        layout.setConstraints(allTagsScrollPane, constraints);
        add(allTagsScrollPane);

        createButton.addActionListener(actionListener);
        constraints = UIUtility.createConstraints(2, 6, 0, 0, NONE);
        constraints.anchor = SOUTHEAST;
        layout.setConstraints(createButton, constraints);
        add(createButton);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(mainFrame);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void validateInput() {

    }

    // ============================  Inner Classes  ==========================79

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createTagButton) {
                    UIUtility.createTag(DocumentDialogCreate.this, searchTextField.getText());
                    searchTextField.setText("");
            }
            // TODO Joe: 29.07.2017  Document muss auch noch dem Tag zugewiesen werden, vielleicht beim Klicken des createButton?
            else if (e.getSource() == addTagButton) {
                int minSelectionIndex = allTagTable.getSelectionModel().getMinSelectionIndex();
                int maxSelectionIndex = allTagTable.getSelectionModel().getMaxSelectionIndex();

                List<Tag> tagsToAdd = new LinkedList<>();
                for (int i = maxSelectionIndex; i >= minSelectionIndex; i--) {
                    tagsToAdd.add((Tag) allTagTable.getValueAt(i, 0));
                }
                assignedTagsModel.addTags(tagsToAdd);
            }
            else if (e.getSource() == fileChooserButton) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(DocumentDialogCreate.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    documentPathTextField.setText(fileChooser.getSelectedFile().toString());
                }
            }
            else if (e.getSource() == createButton) {

                validateInput();

                if (documentNameTextField != null && documentNameTextField.getText().length() > 0) {
                    // ruft createDoc auf und uebergibt Dokumentennamen, -pfad und Liste der zugewiesenen Tags
                    Control.getInstance().createDoc(documentNameTextField.getText(), documentPathTextField.getText(), assignedTagsModel.getTags());
                    // ruft removeListener auf, um den Listener wieder abzumelden
                    Control.getInstance().removeListener((AllTagTableModel) allTagTable.getModel());

                    DocumentDialogCreate.this.dispose();
                    DocumentDialogCreate.this.setVisible(false);
                } else JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Dokumentennamen ein!", "Fehler", JOptionPane.ERROR_MESSAGE);

            }
        }
    };

    private final class AssignedTagsModel extends AbstractTableModel {

        private List<Tag> tags = new ArrayList<>();

        @Override
        public String getColumnName(int column) {
            return "Zugewiesene Tags";
        }

        @Override
        public int getRowCount() {
            return tags.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (tags.isEmpty()) {
                return "";
            }
            return tags.get(rowIndex).getTagName();
        }

        List<Tag> getTags() {
            return tags;
        }

        void removeTag(Tag tag) {
            tags.remove(tag);
        }

        void addTags(List<Tag> newTags) {
            List<Tag> wrongTags = new ArrayList<>();
            for (Tag tag : newTags) {
                if (!tags.contains(tag)) {
                    tags.add(tag);
                }
                else {
                    wrongTags.add(tag);
                }
            }
            if (!wrongTags.isEmpty()) {
                String errorMessage;
                if (wrongTags.size() == 1) {
                    errorMessage = "Das Tag " + wrongTags.get(0).getTagName() + " ist bereits zugewiesen.";
                }
                else {
                    errorMessage = "Die Tags " + wrongTags + " sind bereits zugewiesen";
                }
                JOptionPane.showMessageDialog(DocumentDialogCreate.this, errorMessage);
            }
            fireTableDataChanged();
        }
    }

    private final class AllTagTableModel extends FilteredTableModel implements ModelChangeListener {

        // Liste, die die Tags anzeigt, welche der Suchanfrage enstsprechen
        private List<Tag> filteredList = new ArrayList<>();

        // Meldet sich selbst beim Listener an (fuegt der Liste hinzu)
        private AllTagTableModel() {
            Control.getInstance().addListener(this);
        }

        @Override
        public String getColumnName(int column) {
            return "Alle Tags";
        }

        // je nachdem ob isfiltered true oder false ergibt, wird mit der filteredList oder mit der tagList (Control) gearbeitet
        @Override
        public int getRowCount() {
            if (isFiltered()) {
                return filteredList.size();
            }
            else {
                return Control.getInstance().getTagList().size();
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
                return Control.getInstance().getTagList().get(rowIndex);
            }
        }

        @Override
        public void modelChanged() {
            fireTableDataChanged();     // benachrichtigt alle Listener
        }

        void updateFilteredList(String searchText) {
            filteredList.clear();
            List<Tag> tagList = Control.getInstance().getTagList();
            for (Tag tag : tagList) {
                if (tag.getTagName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(tag);
                }
            }
        }
    }

    // ============================  End of class  ===========================79
}
