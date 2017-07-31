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
    private List<Tag> tagList = new LinkedList<>();

    // Liste mit allen angemeldeten Listenern (observer pattern)
    private List<ModelChangeListener> listenerList = new ArrayList<>();

    // ============================  Constructors  ===========================79
    //Singleton
    private Control() {

    }

    // ===========================  public  Methods  =========================79
    public static Control getInstance() {
        return instance;
    }

    public void createDoc(String inputDocName, String inputDocPath, List<Tag> tags) {
        docList.add(new Document(inputDocName, inputDocPath, tags));
        fireModelChanged();
    }

    // TODO Joe: 16.07.2017 überlegen, ob gefilterte DocListe über GUI oder über Control verwaltet werden soll
    //todo auf GUI einstellen
    public void outputContainsDoc(Tag tag) {
        for (int i = 0; i < tag.getAssignedDocList().size(); i++) {
            System.out.println(tag.getAssignedDocList().get(i).getDocName());
        }
    }

    public void adjustDocName(Document document, String inputString) {
        document.setDocName(inputString);
    }

    public void adjustDocPath(Document document, String inputString) {
        document.setDocPath(inputString);
    }

    public boolean addTag(Tag tag) {
        if (tagList.contains(tag)) {
            return false;
        }
        else {
            tagList.add(tag);
            fireModelChanged();
            return true;
        }
    }

    public void deleteTag(Tag tag) {
        tagList.remove(tag);

        for (Document document : docList) {
            if (document.getAssignedTagList().contains(tag)) {
                document.removeTag(tag);
            }
        }
    }

    public boolean renameTag(Tag tagToRename, String tagName) {
        if (tagName.isEmpty() || tagToRename.getTagName().equals(tagName)) {
            return false;
        }
        for (Tag tag : tagList) {
            if (tagToRename == tag) {
                continue;
            }
            if (tag.getTagName().equalsIgnoreCase(tagName)) {
                return false;
            }
        }
        tagToRename.setTagName(tagName);
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

    // TODO Joe: 16.07.2017 multiple Zuweisung einfügen
    //todo einem Tag ein Dokument hinzufügen, Attribute ergänzen
    public void addDoc(Tag tag, Document document) {
        tag.getAssignedDocList().add(document);
    }

    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tagList);
    }

    public List<Document> getDocList() {
        return Collections.unmodifiableList(docList);
    }

    public void addListener(ModelChangeListener listener) {
        listenerList.add(listener);
    }

    public void removeListener(ModelChangeListener listener) {
        listenerList.remove(listener);
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
// TODO Joe: 29.07.2017 modelChanged macht genau was?
    private void fireModelChanged() {
        for (ModelChangeListener listener : listenerList) {
            listener.modelChanged();
        }
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
