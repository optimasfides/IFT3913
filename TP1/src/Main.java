import java.io.FileNotFoundException;
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
        System.out.println("Searching for .java files...");

        try {
            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .forEach((filePath -> {
                        if (String.valueOf(filePath).contains(".java")) {
                            System.out.println(filePath);
                            javaFiles.add(new JavaFile(String.valueOf(filePath)));
                        }
                    }));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");
    }

    /**
     * Produise deux fichiers au format CSV: classes.csv et methodes.csv
     */
    private static void createCSVs() {
        System.out.println("Recording metrics...");
        CSVWriter.recordClasses(javaFiles);
        CSVWriter.recordMethods(javaFiles);

        System.out.println("Done.");
        System.out.println("CSV files created for both classes.csv and methodes.csv");
    }

    /**
     * Demande et enregistre le chemin de la repertoire a analyser rentre par l'utilisateur
     */
    private static void getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please place the directory with java project near the exetutable file and enter it's name.");
        path = input.nextLine();

        System.out.println("Path to analyse is: " + path);
    }

    /**
     * Calculer les LOC, CLOC, DC, BC, CC, WMC des classes et des methodes
     */
    private static void doMetrics() {
        System.out.println("Doing metrics...");
        javaFiles.forEach(javaFile -> {
            try {

                ArrayList<String> methodNames = Parser.extractMethodNames(javaFile.getFile());
                methodNames.forEach(methodName -> {
                    try {
                        Method method = new Method(javaFile.getPath(), javaFile.getClasse().getClassName(), methodName);
                        method.setLOC(Metrics.measureLOCofMethod(javaFile.getFile(), methodName));
                        method.setCLOC(Metrics.measureCLOCofMethod(javaFile.getFile(), methodName));
                        method.setDC(Metrics.measureDCofMethod(method.getCLOC(), method.getLOC()));
                        method.setCC(Metrics.measureCCofMethod(javaFile.getFile(), methodName));
                        method.setBC(Metrics.measureBCofMethod(method.getDC(), method.getCC()));
                        javaFile.addMethod(method);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                javaFile.getClasse().setLOC(Metrics.measureLOCofClass(javaFile.getFile()));
                javaFile.getClasse().setCLOC(Metrics.measureCLOCofClass(javaFile.getFile()));
                javaFile.getClasse().setDC(Metrics.measureDCofClass(javaFile.getClasse().getCLOC(),
                        javaFile.getClasse().getLOC()));
                javaFile.getClasse().setWMC(Metrics.measureWMCofClass(javaFile.getMethods()));
                javaFile.getClasse().setBC(Metrics.measureBCofClass(javaFile.getClasse().getDC(),
                        javaFile.getClasse().getWMC()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Done.\n");
    }

    public static void main(String[] args) {
        getUserInput();
        findJavaFiles();
        doMetrics();
        createCSVs();
    }
}
