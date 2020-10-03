import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*
SOURCES:
[https://stackoverflow.com/questions/2056221/recursively-list-files-in-java]
 */

/**
 * Le code prenne en entree le chemin d'acces d'un dossier qui contient du code java,
 * trouve et enregistre tous les fichiers .java, calcule les metriques du chaqu'un,
 * ensuite produise deux fichiers au format CSV (« comma separated values», valeurs separees par des virgules).
 */
public class Main {
    private static String path = "javaProjects";
    private static final ArrayList<JavaFile> javaFiles = new ArrayList<>();

    /**
     * Trouve et enregistre tous les fichiers .java a partir du chemin d'accès d'un dossier qui contient du code java
     */
    private static void findJavaFiles() {
        System.out.println("Searching for .java files in the directory: '"+ path + "' ...");

        try {
            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .forEach((filePath -> {
                        if (String.valueOf(filePath).contains(".java")) {
                            System.out.println(String.valueOf(filePath));
                            javaFiles.add(new JavaFile(String.valueOf(filePath)));
                        }
                    }));

        } catch (IOException e) {
            e.printStackTrace();
            getUserInput();
        }
        System.out.println("Finished");
    }

    /**
     * Produise deux fichiers au format CSV: classes.csv et methodes.csv
     */
    private static void createCSVs() {
        System.out.println("Recording metric calculs...");

        CSVWriter.recordClasses(javaFiles);
        CSVWriter.recordMethods(javaFiles);

        System.out.println("CSV files created for both classes.csv and methodes.csv");
    }

    /**
     * Gather user's input for path
     */
    private static void getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please place the directory with java project near the exetutable file and enter it's name.");
        path = input.nextLine();

        System.out.println("Path to analyse is: " + path);
    }

    public static void main(String[] args) {
        getUserInput();
        findJavaFiles();
        createCSVs();
    }
}
