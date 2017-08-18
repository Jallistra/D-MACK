package source;

import GUI.UI.MainFrame;

import java.io.*;

public class DMACK {

    // =========================== Class Variables ===========================79
    // =============================  Variables  =============================79

    public static File SAVE_PATH;

    // ============================  Constructors  ===========================79
    // ===========================  public  Methods  =========================79

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String userHome = System.getProperty("user.home");
        File saveFolderFile = new File(userHome + File.separator + "D-MACK saveData");
        File saveDataFile = new File(userHome + File.separator + "D-MACK saveData" + File.separator + "saveData");

        if (!saveFolderFile.exists()) {
            saveFolderFile.mkdir();
        }

        if (!saveDataFile.exists()) {
            saveDataFile.createNewFile();
        }

        SAVE_PATH = saveDataFile;

        if (hasSave()) {
            Data.getInstance().load();
        }

        new MainFrame();
    }

    // =================  protected/package local  Methods ===================79
    // ===========================  private  Methods  ========================79

    private static boolean hasSave() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))){
            return reader.readLine() != null;
        }
    }

    // ============================  Inner Classes  ==========================79
    // ============================  End of class  ===========================79
}
