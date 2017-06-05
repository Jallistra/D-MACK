package source;

import java.util.ArrayList;

/**
 * Created by cceti on 30.05.2017.
 */
public class Document {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    private String docName;
    private String docPath;
    private ArrayList<Tag> assignedTagList;
    private Date createdDate;
    private Date updatedDate;

    // ============================  Constructors  ===========================79
    public Document() {
    }

    //todo Ist dieser Konstruktor nötig?
    public Document(ArrayList<Tag> tagList) {
        this.assignedTagList = tagList;
    }


    // ===========================  public  Methods  =========================79

    public void openDoc() {

    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public ArrayList<Tag> getAssignedTagList() {
        return assignedTagList;
    }

    public void setAssignedTagList(ArrayList<Tag> assignedTagList) {
        this.assignedTagList = assignedTagList;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}

