package source;

import java.io.*;
import java.util.*;

/**
 * Created by cceti on 31.05.2017.
 */
public class Data implements Serializable {
    // =========================== Class Variables ===========================79

    private static final Data instance = new Data();

    // =============================  Variables  =============================79

    private List<Document> docList = new ArrayList<>();
    private List<Tag> tagList = new LinkedList<>();

    private transient List<ModelChangeListener> listenerList = new ArrayList<>();

    // ============================  Constructors  ===========================79

    private Data() {

    }

    // ===========================  public  Methods  =========================79

    public static Data getInstance() {
        return instance;
    }

    public void createDocument(String inputDocName, String inputDocPath, List<Tag> tags) {
        Document document = new Document(inputDocName, inputDocPath, tags);
        docList.add(document);
        for (Tag tag : tags) {
            tag.addDocument(document);
        }
        fireModelChanged();
    }

    public void adjustDocument(Document document, String documentName, String documentPath, List<Tag> tags) {
        document.setDocumentName(documentName);
        document.setDocumentPath(documentPath);
        document.replaceAssignedTagList(tags);
        //document.setModifiedDate(Date.now());
        for (Tag tag : tags) {
            if (!tag.getAssignedDocumentList().contains(document)) {
                tag.addDocument(document);
            }
        }
        fireModelChanged();
    }

    public void deleteDocument(Document document) {
        docList.remove(document);

        for ( Tag tag : tagList) {
            if (tag.getAssignedDocumentList().contains(document)) {
                tag.removeDocument(document);
            }
        }
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
        fireModelChanged();
        return true;
    }

    public void deleteTag(Tag tag) {
        tagList.remove(tag);

        for (Document document : docList) {
            if (document.getAssignedTagList().contains(tag)) {
                document.removeTag(tag);
            }
        }
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

    public void updateModel() {
        fireModelChanged();
    }

    // =================  protected/package local  Methods ===================79

    void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(DMACK.SAVE_PATH))) {
            Data load = (Data) input.readObject();

            this.docList.clear();
            this.docList.addAll(load.docList);
            this.tagList.clear();
            this.tagList.addAll(load.tagList);
        }
    }

    // ===========================  private  Methods  ========================79

    private void fireModelChanged() {
        for (ModelChangeListener listener : listenerList) {
            listener.modelChanged();
        }
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
