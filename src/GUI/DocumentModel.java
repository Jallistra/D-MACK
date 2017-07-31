package GUI;

import source.Control;
import source.Date;
import source.Document;
import source.ModelChangeListener;

import java.util.ArrayList;
import java.util.List;

public class DocumentModel extends FilteredTableModel implements ModelChangeListener {
    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    private List<Document> filteredList = new ArrayList<>();

    // ============================  Constructors  ===========================79
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
            return Control.getInstance().getDocList().size();
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
            documents = Control.getInstance().getDocList();
        }
        if (documents.isEmpty()) {
            return "";
        }
        Document document = documents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return document.getDocName();
            case 1:
                return document.getCreatedDate();
            case 2:
                return document.getUpdatedDate();
            default:
                throw new IllegalStateException("kapott");
        }
    }

    @Override
    public void modelChanged() {
        fireTableDataChanged();     // benachrichtigt alle listener
    }

    // docList wird auf den String hin ueberprueft und wenns matched, wird es filteredList hinzugefuegt
    @Override
    void updateFilteredList(String text) {
        filteredList.clear();
        List<Document> tagList = Control.getInstance().getDocList();
        for (Document document : tagList) {
            if (document.getDocName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(document);
            }
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
