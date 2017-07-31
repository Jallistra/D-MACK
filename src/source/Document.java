package source;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cceti on 30.05.2017.
 */
public class Document {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    private String docName;
    private String docPath;
    private List<Tag> assignedTagList = new ArrayList();
    private Date createdDate;
    private Date updatedDate;

    // ============================  Constructors  ===========================79
    //todo zu testzwecken benötigt, zum Ende hin überprüfen ob benötoigt wird
    public Document() {

    }

    public Document(String name, String path, List<Tag> tags) {
        this.docName = name;
        this.docPath = path;
        assignedTagList.addAll(tags);
        createdDate = Date.now();
        updatedDate = Date.now();
    }

    // ===========================  public  Methods  =========================79

    // TODO: Joe 16.07.2017 Else hinzufügen, wenns nicht klappt
    //todo test mit scanner war erfolgreich, compiler wandelt die eingabe über intellij in richtige form um
    public void openDoc() throws IOException {
        if(Desktop.isDesktopSupported()) {
            Desktop desk = Desktop.getDesktop();
            desk.open(new File(docPath));
        }

    }

    public String getDocName() {
        return docName;
    }

    public String getDocPath() {
        return docPath;
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}

