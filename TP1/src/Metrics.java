import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import java.util.*;


/*
SOURCES:
[https://www.w3schools.com/java/java_regex.asp]
 */

/**
 * PARTIE 1 et 3
 * Le classe, étant donné le fichier source d'une classe java, calcule les métriques
 */
public class Metrics {


    public static int measureLOCofMethod(File file, String methodName) throws FileNotFoundException {
        int nbLines = 0;
        Stack<Character> brackets = new Stack<>(); // controle des parentheses pour le debut et le fin de la methode
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(methodName)) {
                nbLines++;
                if (line.contains("{")) {
                    brackets.push('{');
                    while (scanner.hasNextLine() && !brackets.empty()) {
                        line = scanner.nextLine();
                        if (line.contains("{"))
                            brackets.push('{');
                        if (line.contains("}"))
                            brackets.pop();
                        if (line.length() > 0)
                            nbLines++;

                        // en cas si le commentaire est imbrique en meme ligne que la ligne du code,
                        // on double la ligne
                        if (line.contains("//") && !line.contains("\"//\""))
                            nbLines++;
                    }
                }
            }
        }

        return nbLines;
    }

    public static int measureCLOCofMethod(File file, String methodName) throws FileNotFoundException {
        int nbLines = 0;
        Stack<Character> mainBrackets = new Stack<>(); // controle pour le debut et le fin de la methode
        Stack<String> commentBrackets = new Stack<>(); // controle pour le debut et le fin des commentaires
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(methodName)) {
                if (line.contains("{")) {
                    mainBrackets.push('{');
                    while (scanner.hasNextLine() && !mainBrackets.empty()) {
                        line = scanner.nextLine();
                        if (line.contains("{"))
                            mainBrackets.push('{');
                        if (line.contains("}"))
                            mainBrackets.pop();
                        if (line.length() > 0) {
                            if (!commentBrackets.empty()) {
                                nbLines++;
                            }
                            if (line.contains("//") && !line.contains("\"//\"")) {
                                nbLines++;
                            }
                            if (line.contains("/*") && !line.contains("\"/*\"")) {
                                commentBrackets.push("/*");
                                nbLines++;
                            }
                            if (line.contains("*/") && !line.contains("\"*/\"")) {
                                commentBrackets.pop();
                            }
                        }
                    }
                }
            }
        }

        return nbLines;
    }

    /**
     * densité de commentaires pour une méthode: methode_DC= methode_CLOC/methode_LOC
     *
     * @param CLOC
     * @param LOC
     * @return densité de commentaires pour une méthode: methode_DC
     */
    public static float measureDCofMethod(int CLOC, int LOC) {
        if (CLOC != -1 && LOC != -1) {
            return (float) CLOC / LOC;
        }
        return -1; //methode introuvable
    }

    /**
     * CC - Complexité cyclomatique de McCabe
     * Mesure du nombre de chemins linéairement indépendants
     */
    public static int measureCCofMethod(File file, String methodName) throws FileNotFoundException {
        int complexity = 1;  // le chemin principal
        Stack<Character> brackets = new Stack<>(); // controle des parentheses pour le debut et le fin de la methode

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(methodName)) {
                    if (line.contains("{")) {
                        brackets.push('{');
                        while (scanner.hasNextLine() && !brackets.empty()) {
                            line = scanner.nextLine();
                            if (line.contains("{"))
                                brackets.push('{');
                            if (line.contains("}"))
                                brackets.pop();
                            if (line.contains(" if") || line.contains(" while") ||
                                    line.contains(" case") || line.contains("else ") ||
                                    line.contains(" default"))
                                complexity++;
                        }
                    }
                    return complexity;
                }
            }
        return -1;
    }

    //BC = methode_DC / CC
    public static float measureBCofMethod(float DC, int CC) {
        if (DC != -1 && CC != -1) {
            return DC / CC;
        }
        return -1; //methode introuvable
    }

    public static int measureLOCofClass(File file, String className) throws FileNotFoundException {
        int nbLines = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
                if (line.length() > 0)
                    nbLines++;

                // en cas si le commentaire est imbrique en meme ligne que la ligne du code,
                // on double la ligne
                if (line.contains("//") && !line.contains("\"//\""))
                    nbLines++;
        }

        return nbLines;
    }

    public static int measureCLOCofClass(File file, String className) throws FileNotFoundException {
        int nbLines = 0;
        Stack<String> commentBrackets = new Stack<>(); // controle pour le debut et le fin des commentaires
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.length() > 0) {

                if (!commentBrackets.empty())
                    nbLines++;

                if (line.contains("//") && !line.contains("\"//\"")) {
                    nbLines++;
                }
                if (line.contains("/*") && !line.contains("\"/*\"") && !line.contains("/**")) {
                    commentBrackets.push("/*");
                    nbLines++;
                }
                if (line.contains("/**") && !line.contains("\"/**\"")) {
                    commentBrackets.push("/**");
                    nbLines++;
                }
                if (line.contains("*/") && !line.contains("\"*/\"")) {
                    commentBrackets.pop();
                }
            }
        }

        return nbLines;
    }

    /**
     * densité de commentaires pour une classe: classe_DC= classe_CLOC/classe_LOC
     *
     * @param CLOC
     * @param LOC
     * @return densité de commentaires pour une classe
     */
    public static float measureDCofClass(int CLOC, int LOC) {
        if (CLOC != -1 && LOC != -1) {
            return (float) CLOC / LOC;
        }
        return -1; //methode introuvable
    }

    // classe_BC= classe_DC/ WMC
    public static float measureBCofClass(float DC, int WMC){
        if (DC != -1 && WMC != -1) {
            return DC / WMC;
        }
        return -1; //methode introuvable
    }

    public static int measureWMCofClass(ArrayList<Method> methods) {
        return methods.stream().mapToInt(Method::getCC).sum();
    }


}



