package GUI.model;

import source.Data;
import source.Document;
import source.ModelChangeListener;
import source.Tag;

import java.util.ArrayList;
import java.util.List;

public class DocumentTableModel extends AbstractFilteredTableModel implements ModelChangeListener {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private List<Document> filteredList = new ArrayList<>();

    // ============================  Constructors  ===========================79

    public DocumentTableModel() {
        Data.getInstance().addListener(this);
    }

    // ===========================  public  Methods  =========================79

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Erstellungsdatum";
            case 2:
                return "Änderungsdatum";
            default:
                throw new IllegalStateException("not CCE approved!!!");
        }
    }

    @Override
    public int getRowCount() {
        if (isFiltered()) {
            return filteredList.size();
        }
        else {
            return Data.getInstance().getDocList().size();
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Document> documents;
        if (isFiltered()) {
            documents = filteredList;
        }
        else {
            documents = Data.getInstance().getDocList();
        }
        if (documents.isEmpty()) {
            return "";
        }
        Document document = documents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return document.getDocumentName();
            case 1:
                return document.getCreatedDate();
            case 2:
                return document.getModifiedDate();
            default:
                throw new IllegalStateException("kapott");
        }
    }

    @Override
    public void modelChanged() {
        fireTableDataChanged();
    }

    @Override
    public void updateFilteredList(String text) {
        filteredList.clear();
        for (Document document : Data.getInstance().getDocList()) {
            if (document.getDocumentName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(document);
            }
        }
    }

    @Override
    public void updateFilteredList(List<Tag> tags) {
        filteredList.clear();
        for (int i = 0; i < tags.size(); i++) {
            if (i == 0) {
                filteredList.addAll(tags.get(i).getAssignedDocumentList());
            }
            else if (filteredList.isEmpty()) {
                break;
            }
            else {
                filteredList.retainAll(tags.get(i).getAssignedDocumentList());
            }
        }
        fireTableDataChanged();
    }

    public Document getDocument(int rowIndex) {
        if (isFiltered()) {
            return filteredList.get(rowIndex);
        }
        else {
            return Data.getInstance().getDocList().get(rowIndex);
        }
    }

    public void removeFromFilteredList(Document document) {
        filteredList.remove(document);
        fireTableDataChanged();
    }
    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
