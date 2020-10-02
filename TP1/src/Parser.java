import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Cherche tous les methodes dans le fichier donnee.
     *
     * @param file : fichier source
     * @return ArrayList<String> methodNames
     */
    public static ArrayList<String> extractMethodNames(File file) {
        Pattern accessPattern = Pattern.compile("public | private | protected");
        Pattern classPattern = Pattern.compile("class | enum | interface");

        ArrayList<String> methodNames = new ArrayList<>();
        List<String> words;
        Matcher matcher;
        String methodName;
        String args;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // si la methode possede l'extriction d'access
                matcher = accessPattern.matcher(line);
                if(matcher.find()) {
                    matcher = classPattern.matcher(line);
                    if (!matcher.find()) {
                        if (line.contains("(")) {
                            methodName = line.substring(0, line.indexOf("("));
                            words = Arrays.asList(methodName.split(" "));
                            methodName = words.get(words.size() - 1);
                            args = line.substring(line.indexOf("("));
                            if (args.contains(")")) {
                                args = line.substring(line.indexOf("("), line.indexOf(")") + 1);
                            }
                            //System.out.println(methodName + args);
                            methodNames.add(methodName + args);
                        }
                    }
                }
            }
            return methodNames;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return methodNames;
    }

    public static String extractClassName(File file) {
        String className = "";
        List<String> words;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Pattern pattern = Pattern.compile(" class | enum | interface ");
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    words = Arrays.asList(line.split(" "));
                    if (words.contains("class"))
                        className = words.get(1 + words.indexOf("class"));
                    if (words.contains("enum"))
                        className = words.get(1 + words.indexOf("enum"));
                    if (words.contains("interface"))
                        className = words.get(1 + words.indexOf("interface"));
                    if (className.contains("{")) {
                        className = className.substring(0, className.length() - 1);
                    }
                    return className;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return className;
    }
}

