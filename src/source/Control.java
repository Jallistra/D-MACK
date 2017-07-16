package source;

import java.util.*;

/**
 * Created by cceti on 31.05.2017.
 */
public class Control {

    // =========================== Class Variables ===========================79
    private static final Control instance = new Control();


    // =============================  Variables  =============================79

    private List<Document> docList = new ArrayList<>();
    private Set<Tag> tagSet = new HashSet<>();

    // ============================  Constructors  ===========================79
    //Singleton - deshalb 
    private Control() {

    }

    // ===========================  public  Methods  =========================79
    public static Control getInstance() {
        return instance;
    }

    public void createDoc(String inputDocName, String inputDocPath) {
        docList.add(new Document(inputDocName, inputDocPath));
    }

    /**
     * 
     * @param inputTagName new tag name
     * @return false, if tag already exits. True otherwise
     */
    public boolean createTag(String inputTagName) {
        return tagSet.add(new Tag(inputTagName));
    }

    public List<Document> searchDoc(String inputString) {
        List<Document> tempDocList = new ArrayList<>(docList.size());
        for (int i=0; i < docList.size();i++) {
            if (docList.get(i).getDocName().contains(inputString)) {
                tempDocList.add(docList.get(i));
            }
        }
        //todo auf GUI einstellen
        for (Document aTempDocList : tempDocList) {
            System.out.println(aTempDocList.getDocName());
        }
        return tempDocList;
    }

    public List<Tag> searchTag(String inputString) {
        List<Tag> tempTagList = new ArrayList<>(tagSet.size());
        Iterator<Tag> it = tagSet.iterator();
        while(it.hasNext()) {
            Tag tag = it.next();
            if (tag.toString().contains(inputString)) {
                tempTagList.add(tag);
                System.out.println(tag.toString());
            }
        }
        return tempTagList;
    }

    //todo auf GUI einstellen
    public void outputContainsDoc(Tag tag) {
        for (int i = 0; i < tag.getAssignedDocList().size(); i++) {
            System.out.println(tag.getAssignedDocList().get(i).getDocName());
        }
    }

    //todo sort nach selben Methode wie in Uebungsblatt 3, index und Ausgabe noch anpassen
    public void sort(int index) {
        if (index==0) {
            Collections.sort(docList, new CompareListBy("getCreatedDate"));
        }
        if (index==1) {
            Collections.sort(docList, new CompareListBy("getUpdatedDate"));
        }
        if(index==2) {
            Collections.sort(docList, new CompareListBy("getDocName"));
        }

    }

    public void adjustDocName(Document document, String inputString) {
        document.setDocName(inputString);
    }

    public void adjustDocPath(Document document, String inputString) {
        document.setDocPath(inputString);
    }

    // TODO: Joe 16.07.2017 Testen
    public boolean adjustTagName(Tag tag, String inputString) {
        tagSet.remove(tag);
        String oldTagName = tag.getTagName();
        tag.setTagName(inputString);
        if (!tagSet.add(tag)) {
            tag.setTagName(oldTagName);
            tagSet.add(tag);
            return false;
        }
        return true;
    }

    // TODO: Joe 16.07.2017 in addTagToDocument und addDoc überprüfen, ob Tag bzw. Doc bereits vorhanden sind
    //todo addTagToDocument und addDoc sollen immer zusammen ausgeführt werden, da Zuweisung in beide Richtungen erfolgt
    //todo einem Dokument ein Tag hinzufügen, Attribute ergänzen
    //todo Attribute müssen noch angepasst werden, Set bzw. Liste mit ausgewählten Tags müssen mit übergeben werden
    public void addTagToDocument(Document document, Set<Tag> selectedTagSet) {
        for (Tag aSelectedTagSet : selectedTagSet) {
            document.getAssignedTagList().add(aSelectedTagSet);
        }
    }

    //todo einem Tag ein Dokument hinzufügen, Attribute ergänzen
    public void addDoc(Tag tag, Document document) {
        tag.getAssignedDocList().add(document);
    }

    public List<Document> getDocList() {
        return docList;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setDocList(List<Document> docList) {
        this.docList = docList;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
