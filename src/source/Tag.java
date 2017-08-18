package source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tag implements Comparable<Tag>, Serializable {

    // =========================== Class Variables ===========================79

    public static final long SerialVersionUID = 270115L;

    // =============================  Variables  =============================79

    private String tagName;
    private ArrayList<Document> assignedDocumentList = new ArrayList<Document>();

    // ============================  Constructors  ===========================79

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    // ===========================  public  Methods  =========================79

    public void addDocument(Document document) {
        assignedDocumentList.add(document);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Document> getAssignedDocumentList() {
        return Collections.unmodifiableList(assignedDocumentList);
    }

    public void removeDocument(Document document) {
        assignedDocumentList.remove(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return tagName.equalsIgnoreCase(tag.getTagName());
    }

    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public int compareTo(Tag o) {
        return this.getTagName().compareTo(o.tagName);
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
