import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * PARTIE 2
 * Le code prenne en entrée le chemin d'accès d'un dossier qui contient du code java et produise
 * deux fichiers au format CSV (« comma separated values», valeurs séparées par des virgules).
 */
public class CSVWriter {

    public static void recordClasses(ArrayList<JavaFile> javaFiles)  {
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

            javaFiles.forEach(javaFile -> {
                javaFile.getClasses().forEach(classe ->
                {
                    try {
                        fileWriter.append(String.join(",", Arrays.asList(classe.getPath(),
                                classe.getClassName(), String.valueOf(classe.getClasse_LOC()) ,
                                String.valueOf(classe.getClasse_CLOC()), String.valueOf(classe.getClasse_DC()))));
                        fileWriter.append("\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recordMethods(ArrayList<JavaFile> javaFiles)  {
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
