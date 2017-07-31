package source;

/**
 * Was genau macht der ModelChangeListener bzw. wieso benoetigen wir ihn nochmal als Extra Interface?
 * Wir ueberschreiben doch nirgendwo die modelChanged Methode
 */
public interface ModelChangeListener {

    void modelChanged();

}
