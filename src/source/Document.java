package source;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cceti on 30.05.2017.
 */
public class Document implements Comparable<Document>, Serializable {
    // =========================== Class Variables ===========================79

    public static final long SerialVersionUID = 51190L;

    // =============================  Variables  =============================79

    private String documentName;
    private String documentPath;
    private List<Tag> assignedTagList = new ArrayList<>();
    private Date createdDate;
    private Date modifiedDate;

    // ============================  Constructors  ===========================79

    Document(String name, String path, List<Tag> tags) {
        this.documentName = name;
        this.documentPath = path;
        assignedTagList.addAll(tags);
        createdDate = Date.now();
        modifiedDate = Date.now();
    }

    // ===========================  public  Methods  =========================79

    public void openDoc(JPanel parent)  {
        if(Desktop.isDesktopSupported()) {
            Desktop desk = Desktop.getDesktop();
            try {
                desk.open(new File(documentPath));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Das Dokument konnte nicht geöffnet werden!", "Fehler", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void removeTag(Tag tag) {
        assignedTagList.remove(tag);
    }

    public List<Tag> getAssignedTagList() {
        return Collections.unmodifiableList(assignedTagList);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    void setDocumentName(String documentName) {
        if (this.documentName.equals(documentName)) {
            return;
        }
        this.documentName = documentName;
        modifiedDate = Date.now();
    }

    void setDocumentPath(String documentPath) {
        if (this.documentPath.equals(documentPath)) {
            return;
        }
        this.documentPath = documentPath;
        modifiedDate = Date.now();
    }


    void replaceAssignedTagList(List<Tag> assignedTagList) {
        if (this.assignedTagList.equals(assignedTagList)) {
            return;
        }
        this.assignedTagList.clear();
        this.assignedTagList.addAll(assignedTagList);
        modifiedDate = Date.now();
    }

    @Override
    public int compareTo(Document o) {
        return this.getDocumentName().compareTo(o.documentName);
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}

