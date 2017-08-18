package GUI.UI;

import GUI.model.AllTagTableModel;
import GUI.model.AssignedTagTableModel;

import javax.swing.*;

public abstract class AbstractDocumentDialog extends JDialog {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    JTextField documentNameTextField = new JTextField();
    JTextField documentPathTextField = new JTextField();

    JButton addTagButton = new JButton("Tag hinzufügen");
    JTextField searchTextField = new JTextField();
    JButton createTagButton = new JButton("Tag erstellen");
    JButton fileChooserButton = new JButton("...");
    JButton createButton = new JButton("Erstellen");
    JButton adjustButton = new JButton("Ändern");

    AssignedTagTableModel assignedTagTableModel;
    JTable assignedTagsTable;
    AllTagTableModel allTagTableModel = new AllTagTableModel();
    JTable allTagTable;

    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79

    public JTextField getDocumentNameTextField() {
        return documentNameTextField;
    }

    public JTextField getDocumentPathTextField() {
        return documentPathTextField;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }


    public AssignedTagTableModel getAssignedTagTableModel() {
        return assignedTagTableModel;
    }

    public AllTagTableModel getAllTagTableModel() {
        return allTagTableModel;
    }

    public JTable getAllTagTable() {
        return allTagTable;
    }


    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
