package GUI.UI;

import source.Data;
import source.Tag;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class UIUtility {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79
    // ============================  Constructors  ===========================79

    private UIUtility() {
        throw new IllegalStateException();
    }

    // ===========================  public  Methods  =========================79

    public static GridBagConstraints createConstraints(int x, int y, int width, int height, int gridBagConstraint) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = gridBagConstraint;
        return constraints;
    }

    public static String getTagName(Component parent, String startingInput) {
        return JOptionPane.showInputDialog(parent, "Bitte geben Sie einen Tag-Namen ein", startingInput);
    }

    public static void createTag(Component parent, String startingInput) {
        String tagName = getTagName(parent, startingInput);
        if (tagName != null && tagName.length() > 0) {
            Tag tag = new Tag(tagName);
            boolean wasAdded = Data.getInstance().addTag(tag);
            if (!wasAdded) {
                JOptionPane.showMessageDialog(parent, "Das Tag ist bereits vorhanden.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void validateWrongTags(JDialog dialog, List<Tag> wrongTags) {
        if (!wrongTags.isEmpty()) {
            String errorMessage;
            if (wrongTags.size() == 1) {
                errorMessage = "Das Tag " + wrongTags.get(0).getTagName() + " ist bereits zugewiesen.";
            }
            else {
                Collections.sort(wrongTags);
                errorMessage = "Die Tags " + wrongTags + " sind bereits zugewiesen.";
            }
            JOptionPane.showMessageDialog(dialog, errorMessage);
        }
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79
    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
