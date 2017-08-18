package GUI.listener;

import GUI.UI.AbstractDocumentDialog;
import GUI.UI.UIUtility;
import GUI.model.AllTagTableModel;
import source.Data;
import source.Document;
import source.Tag;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class DocumentDialogActionListener implements ActionListener {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private static final String ADD_TAG_BUTTON = "AddTagButton";
    private static final String CREATE_TAG_BUTTON = "CreateTagButton";
    private static final String CREATE_BUTTON = "CreateButton";
    private static final String FILE_CHOOSER_BUTTON = "FileChooserButton";
    private static final String ADJUST_BUTTON = "AdjustButton";

    private AbstractDocumentDialog dialog;
    private Document document;

    // ============================  Constructors  ===========================79

    public DocumentDialogActionListener(AbstractDocumentDialog dialog, Document document) {
        this.dialog = dialog;
        this.document = document;
    }

    // ===========================  public  Methods  =========================79

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CREATE_TAG_BUTTON)) {
            UIUtility.createTag(dialog, dialog.getSearchTextField().getText());
            dialog.getSearchTextField().setText("");
        }
        else if (e.getActionCommand().equals(ADD_TAG_BUTTON)) {
            List<Tag> tagsToAdd = new LinkedList<>();
            for (int i = 0; i < dialog.getAllTagTable().getModel().getRowCount(); i++) {
                if (dialog.getAllTagTable().getSelectionModel().isSelectedIndex(i)) {
                    tagsToAdd.add((Tag) dialog.getAllTagTable().getValueAt(i, 0));
                }
            }
            if (Data.getInstance().getDocList().contains(document)) {
                dialog.getAssignedTagTableModel().addTags(tagsToAdd);
            } else {
                dialog.getAssignedTagTableModel().addTags(tagsToAdd);
            }
        }
        else if (e.getActionCommand().equals(FILE_CHOOSER_BUTTON) ) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(dialog);
            if (option == JFileChooser.APPROVE_OPTION) {
                dialog.getDocumentPathTextField().setText(fileChooser.getSelectedFile().toString());
            }
        }
        else if (e.getActionCommand().equals(CREATE_BUTTON)) {
            if (validateInput()) {
                Data.getInstance().createDocument(dialog.getDocumentNameTextField().getText(),
                        dialog.getDocumentPathTextField().getText(), dialog.getAssignedTagTableModel().getTags());
                Data.getInstance().removeListener(dialog.getAllTagTableModel());
                Data.getInstance().removeListener(dialog.getAssignedTagTableModel());
                dialog.dispose();
                dialog.setVisible(false);
            }
        }
        else if (e.getActionCommand().equals(ADJUST_BUTTON)) {
            if (validateInput()) {
                Data.getInstance().adjustDocument(document, dialog.getDocumentNameTextField().getText(),
                        dialog.getDocumentPathTextField().getText(), dialog.getAssignedTagTableModel().getTags());
                Data.getInstance().removeListener((AllTagTableModel) dialog.getAllTagTable().getModel());
                dialog.dispose();
                dialog.setVisible(false);
            }
            // TODO Joe: 14.08.2017 zu Testzwecken
            for (int i = 0; i < document.getAssignedTagList().size(); i++) {
                System.out.println(document.getAssignedTagList().get(i));
                for (int j = 0; j < document.getAssignedTagList().get(i).getAssignedDocumentList().size(); j++) {
                    System.out.println(document.getAssignedTagList().get(i).getAssignedDocumentList().get(j).getDocumentName());
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private boolean validateInput() {
        if (dialog.getDocumentNameTextField() != null && dialog.getDocumentNameTextField().getText().length() > 0 &&
                dialog.getDocumentPathTextField() != null && dialog.getDocumentPathTextField().getText().length() > 0) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(dialog, "Bitte geben Sie einen Dokumentennamen bzw. " +
                    "Dokumentenpfad ein!","Fehler",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
