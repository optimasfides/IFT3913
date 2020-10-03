import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
SOURCES:
[https://www.w3schools.com/java/java_regex.asp]
 */

public class Parser {

    /**
     * Cherche tous les methodes dans le fichier donnee qui possede la restriction d'acces comme "public | private ...
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

    /**
     * Extraire le nom de la classe principale dans le fichier
     * @param file le fichoer source a analyser
     * @return le nom de la classe
     */
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

    /**
     * Utilise les underscores pour séparer les arguments dans une signature. Par exemple : pour la signature
     * methodName(typeOfArg1 arg1, typeOfArg2 arg2) ecrivez:
     * methodName_typeOfArg1_typeOfArg2
     * @param methodName methodName(typeOfArg1 arg1, typeOfArg2 arg2
     * @return methodName_typeOfArg1_typeOfArg2
     */
    public static String underscoreMethodeName(String methodName) {
        String underscoredName;
        ArrayList<String> words = new ArrayList<>();
        String[] args;

        words.add(methodName.substring(0, methodName.indexOf("(")));
        args = methodName.substring(methodName.indexOf("(") + 1, methodName.indexOf(")")).split(" ");
        for (int i = 0; i < args.length; i++) {
            // Le type des arguments se trouve aux indexes pairs
            if (i % 2 == 0) {
                words.add(args[i]);
            }
        }
        underscoredName = String.join("_", words);

        return underscoredName;
    }
}

