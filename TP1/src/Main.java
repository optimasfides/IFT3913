import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
SOURCES:
[https://stackoverflow.com/questions/2056221/recursively-list-files-in-java]
 */

public class Main {
    private static String path = "javaProjects";
    private static ArrayList<JavaFile> javaFiles = new ArrayList<JavaFile>();

    private static void findJavaFiles() {
        System.out.println("Searching for .java files in the directory: '"+ path + "' ...");

        try {
            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .forEach((filePath) ->
                            javaFiles.add(new JavaFile(String.valueOf(filePath))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

    private static void createCSVs() {
        System.out.println("Recording metric calculs...");

        CSVWriter.recordClasses(javaFiles);
        CSVWriter.recordMethods(javaFiles);

        System.out.println("CSV files created for both classes.csv and methodes.csv");
    }

    public static void main(String[] args) throws FileNotFoundException {
        findJavaFiles();
        createCSVs();
    }
}
