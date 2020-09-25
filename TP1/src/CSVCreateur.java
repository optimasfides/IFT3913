import java.io.FileWriter;
import java.io.IOException;

/**
 * PARTIE 2
 * Le code prenne en entrée le chemin d'accès d'un dossier qui contient du code java et produise
 * deux fichiers au format CSV (« comma separated values», valeurs séparées par des virgules).
 */
public class CSVCreateur {

    public static void creerCSV_classes()  {
        try {
            FileWriter fileWriter = new FileWriter("classes.csv");
            fileWriter.append("chemin");
            fileWriter.append(",");
            fileWriter.append("class");
            fileWriter.append(",");
            fileWriter.append("classe_LOC");
            fileWriter.append(",");
            fileWriter.append("classe_CLOC");
            fileWriter.append(",");
            fileWriter.append("classe_DC");
            fileWriter.append("\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creerCSV_methodes()  {
        try {
            FileWriter fileWriter = new FileWriter("methodes.csv");
            fileWriter.append("chemin");
            fileWriter.append(",");
            fileWriter.append("class");
            fileWriter.append(",");
            fileWriter.append("methode");
            fileWriter.append(",");
            fileWriter.append("methode_LOC");
            fileWriter.append(",");
            fileWriter.append("methode_CLOC");
            fileWriter.append(",");
            fileWriter.append("methode_DC");
            fileWriter.append("\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
