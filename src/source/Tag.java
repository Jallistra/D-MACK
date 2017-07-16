package source;

import java.util.ArrayList;

/**
 * Created by cceti on 30.05.2017.
 */
public class Tag {

    // =========================== Class Variables ===========================79
    private String tagName;
    private ArrayList<Document> assignedDocList = new ArrayList<Document>();

    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    // ===========================  public  Methods  =========================79

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<Document> getAssignedDocList() {
        return assignedDocList;
    }

    public void setAssignedDocList(ArrayList<Document> assignedDocList) {
        this.assignedDocList = assignedDocList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return tagName.equalsIgnoreCase(tag.getTagName());
    }

    @Override
    public int hashCode() {
        return tagName.toLowerCase().hashCode();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
