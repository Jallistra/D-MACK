package source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by cceti on 31.05.2017.
 */
public class Control {

    // =========================== Class Variables ===========================79
    private static final Control instance = new Control();
    private ArrayList<Document> docList = new ArrayList<Document>();
    private HashSet<Tag> tagSet = new HashSet<Tag>();
    private ArrayList<Document> tempDocList = new ArrayList<Document>();
    private HashSet<Tag> tempTagSet = new HashSet<Tag>();

    // =============================  Variables  =============================79

    // ============================  Constructors  ===========================79
    private Control() {

    }

    // ===========================  public  Methods  =========================79
    public static Control getInstance() {
        return instance;
    }

    public void createDoc(String inputDocName, String inputDocPath) {
        docList.add(new Document(inputDocName, inputDocPath));
    }

    public void createTag(String inputTagName) {
        tagSet.add(new Tag(inputTagName));
    }

    //todo addTag und addDoc sollen immer zusammen ausgeführt werden, da Zuweisung in beide Richtungen erfolgt
    //todo multiple Zuweisung vielleicht durch for-Schleife mit der Länge der Anzahl von Docs oder Tags, die zugewiesen werden sollen
    //todo einem Dokument ein Tag hinzufügen, Attribute ergänzen
    public void addTag(Document document, Tag tag) {
        document.getAssignedTagSet().add(tag);
    }

    //todo einem Tag ein Dokument hinzufügen, Attribute ergänzen
    public void addDoc(Tag tag, Document document) {
        tag.getAssignedDocList().add(document);
    }


    public void searchDoc(String inputString) {
        for (int i=0; i<docList.size();i++) {
            if (docList.get(i).getDocName().contains(inputString)) {
                tempDocList.add(docList.get(i));
            }
        }
        //todo auf GUI einstellen
        for (Document aTempDocList : tempDocList) {
            System.out.println(aTempDocList.getDocName());
        }
        //todo falls mit tempDocList weitergearbeitet wird, sollte hier nicht gecleart werden, sondern am/an jeweiligen ende/enden
        tempDocList.clear();
    }

    public void searchTag(String inputString) {
        Iterator it = tagSet.iterator();
        while(it.hasNext()) {
            if (it.next().toString().contains(inputString)) {
                System.out.println(it.toString());
            }
        }
    }

    //todo auf GUI einstellen
    public void outputContainedDoc(Tag tag) {
        if(tagSet.contains(tag)) {
            for (int i = 0; i < tag.getAssignedDocList().size(); i++) {
                System.out.println(tag.getAssignedDocList().get(i).getDocName());
            }
        } else {
            System.out.println("Tag ist nicht im System angelegt!");
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

    public void adjustTagName(Tag tag, String inputString) {
        tag.setTagName(inputString);
    }

    //todo Methode bereits vorhanden (addTag)
    //todo Attribute müssen noch angepasst werden, Set bzw. Liste mit ausgewählten Tags müssen mit übergeben werden
    // todo Vorschlag von IntelliJ anstelle von "while" "foreach", wird dabei vorhandenes Set des Dokuments ersetzt?
    public void addTagToDocument(Document document, HashSet<Tag> selectedTagSet) {
        Iterator<Tag> it = selectedTagSet.iterator();
        while(it.hasNext()) {
            document.getAssignedTagSet().add(it.next());
        }
//todo Vorschlag von IntelliJ, kann mich nicht ganz an die eigentliche "while"-Schleife erinnern
//        for (Object aSelectedTagSet : selectedTagSet) {
//            document.getAssignedTagSet().add((Tag) aSelectedTagSet);
//        }
    }

    public ArrayList<Document> getDocList() {
        return docList;
    }

    public HashSet<Tag> getTagSet() {
        return tagSet;
    }

    public ArrayList<Document> getTempDocList() {
        return tempDocList;
    }

    public HashSet<Tag> getTempTagSet() {
        return tempTagSet;
    }

    public void setDocList(ArrayList<Document> docList) {
        this.docList = docList;
    }

    public void setTagSet(HashSet<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    public void setTempDocList(ArrayList<Document> tempDocList) {
        this.tempDocList = tempDocList;
    }

    public void setTempTagSet(HashSet<Tag> tempTagSet) {
        this.tempTagSet = tempTagSet;
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
