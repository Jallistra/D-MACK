package source;

import java.util.ArrayList;

/**
 * Created by cceti on 31.05.2017.
 */
public class Control {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private ArrayList<Document> docList;
    private ArrayList<Tag> tagList;
    private ArrayList<Document> tempDocList;
    private ArrayList<Tag> tempTagList;

    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79

    public void search(String inputString) {

    }

    public void filter(Tag tag) {

    }

    public void sort(int index) {

    }

    public void adjust(Document document) {

    }

    public void adjust(Tag tag) {

    }

    public ArrayList<Document> getDocList() {
        return docList;
    }

    public void setDocList(ArrayList<Document> docList) {
        this.docList = docList;
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<Document> getTempDocList() {
        return tempDocList;
    }

    public void setTempDocList(ArrayList<Document> tempDocList) {
        this.tempDocList = tempDocList;
    }

    public ArrayList<Tag> getTempTagList() {
        return tempTagList;
    }

    public void setTempTagList(ArrayList<Tag> tempTagList) {
        this.tempTagList = tempTagList;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
