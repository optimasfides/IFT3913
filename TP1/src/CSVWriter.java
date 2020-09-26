import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PARTIE 2
 * Le code prenne en entrée le chemin d'accès d'un dossier qui contient du code java et produise
 * deux fichiers au format CSV (« comma separated values», valeurs séparées par des virgules).
 */
public class CSVWriter {

    /**
     * Cree le fichier classes.csv
     * @param javaFiles
     */
    public static void recordClasses(ArrayList<JavaFile> javaFiles)  {
        List<String> header = Arrays.asList("chemin","class", "classe_LOC",
                "classe_CLOC", "classe_DC", "WMC", "classe_BC");

        try {
            FileWriter fileWriter = new FileWriter("classes.csv");
            fileWriter.append(String.join(",", header));
            fileWriter.append("\n");

            javaFiles.forEach(javaFile -> {
                javaFile.getClasses().forEach(classe ->
                {
                    try {
                        List<String> data = Arrays.asList(classe.getPath(),
                                classe.getClassName(),
                                String.valueOf(classe.getClasse_LOC()),
                                String.valueOf(classe.getClasse_CLOC()),
                                String.valueOf(classe.getClasse_DC()),
                                String.valueOf(classe.getWMC()),
                                String.valueOf(classe.getClasse_BC()));

                        fileWriter.append(String.join(",", data));
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

    /**
     * Cree le fichier methodes.csv
     * @param javaFiles
     */
    public static void recordMethods(ArrayList<JavaFile> javaFiles)  {
        List<String> header = Arrays.asList("chemin","class", "methode", "methode_LOC",
                "methode_CLOC", "methode_DC", "CC", "methode_BC");

        try {
            FileWriter fileWriter = new FileWriter("methodes.csv");
            fileWriter.append(String.join(",", header));
            fileWriter.append("\n");

            javaFiles.forEach(javaFile -> {
                javaFile.getMethods().forEach(method ->
                {
                    try {
                        List<String> data = Arrays.asList(method.getPath(),
                                method.getClassName(),
                                method.getMethodName(),
                                String.valueOf(method.getMethode_LOC()),
                                String.valueOf(method.getMethode_CLOC()),
                                String.valueOf(method.getMethode_DC()),
                                String.valueOf(method.getCC()),
                                String.valueOf(method.getMethode_BC()));

                        fileWriter.append(String.join(",", data));
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
}
