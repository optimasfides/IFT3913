import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PARTIE 1 et 3
 * Le classe, étant donné le fichier source d'une classe java, calcule les métriques
 */
public class Metrics {


    /**
     * LOC : "Lines Of Code"
     * Calcule le nombre de lignes de code d’une méthode
     * @param file le fichier a scanner pour trouver la methode
     * @param methodName le nom du methode pour laquelle il faut calculer le LOC
     * @return LOC de la methode
     * @throws FileNotFoundException si le fichier a scanner n'est trouve
     */
    public static int measureLOCofMethod(File file, String methodName) throws FileNotFoundException {
        int nbLines = 0;
        Stack<Character> brackets = new Stack<>(); // controle des parentheses pour le debut et le fin de la methode
        Pattern accessPattern = Pattern.compile("public | private | protected");
        Pattern commentPattern = Pattern.compile("^//");
        Matcher matcher;
        List<String> methodNameMultipleLines = Arrays.asList(methodName.split("\n"));

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcher = accessPattern.matcher(line);

            if (line.contains(methodNameMultipleLines.get(0)) && matcher.find()) {
                boolean match = true;
                int index = 1;
                while (scanner.hasNextLine() && index < methodNameMultipleLines.size()) {
                    line = scanner.nextLine();
                    if (!line.trim().contains(methodNameMultipleLines.get(index)))
                        match = false;
                    index++;
                }

                if (match) {
                    nbLines += methodNameMultipleLines.size();
                    if (line.contains("{")) {
                        brackets.push('{');
                        while (scanner.hasNextLine() && !brackets.empty()) {
                            line = scanner.nextLine();
                            if (line.contains("{"))
                                brackets.push('{');
                            if (line.contains("}"))
                                brackets.pop();
                            if (line.trim().length() > 0)
                                nbLines++;

                            // en cas si le commentaire est imbrique en meme ligne que la ligne du code,
                            // on double la ligne
                            matcher = commentPattern.matcher(line.trim());
                            if (line.contains("//") && !line.contains("\"//\"") && !matcher.find())
                                nbLines++;
                        }
                    }
                }
            }
        }

        return nbLines;
    }

    /**
     * CLOC : "Comment Lines Of Code"
     * Calcule le nombre de lignes de code d’une methode qui contiennent des commentaires
     * @param file le fichier a scanner pour trouver la methode
     * @param methodName le nom du methode pour laquelle il faut calculer le CLOC
     * @return CLOC de la methode
     * @throws FileNotFoundException si le fichier a scanner n'est trouve
     */
    public static int measureCLOCofMethod(File file, String methodName) throws FileNotFoundException {
        int nbLines = 0;
        Stack<Character> mainBrackets = new Stack<>(); // controle pour le debut et le fin de la methode
        Stack<String> commentBrackets = new Stack<>(); // controle pour le debut et le fin des commentaires
        Pattern accessPattern = Pattern.compile("public | private | protected");
        Matcher matcher;
        List<String> methodNameMultipleLines = Arrays.asList(methodName.split("\n"));

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcher = accessPattern.matcher(line);

            if (line.contains(methodNameMultipleLines.get(0)) && matcher.find()) {
                boolean match = true;
                int index = 1;
                while (scanner.hasNextLine() && index < methodNameMultipleLines.size()) {
                    line = scanner.nextLine();
                    if (!line.trim().contains(methodNameMultipleLines.get(index)))
                        match = false;
                    index++;
                }

                if (match) {
                    if (line.contains("{")) {
                        mainBrackets.push('{');
                        while (scanner.hasNextLine() && !mainBrackets.empty()) {
                            line = scanner.nextLine();
                            if (line.contains("{"))
                                mainBrackets.push('{');
                            if (line.contains("}"))
                                mainBrackets.pop();
                            if (line.trim().length() > 0) {
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
                                if (line.contains("*/") && !line.contains("\"*/\"") && !commentBrackets.empty()) {
                                    commentBrackets.pop();
                                }
                            }
                        }
                    }
                }
            }
        }

        return nbLines;
    }

    /**
     * DC : "densite de commentaire"
     * Calcule densité de commentaires pour une méthode: methode_DC= methode_CLOC/methode_LOC
     * @param methodCLOC "Comment Lines Of Code" de la methode donnee
     * @param methodLOC "Lines Of Code" de la methode donnee
     * @return densité de commentaires pour une méthode: methode_DC
     */
    public static float measureDCofMethod(int methodCLOC, int methodLOC) {
        if (methodCLOC != -1 && methodLOC != -1) {
            return (float) methodCLOC / methodLOC;
        }
        return 0; //methode introuvable
    }

    /**
     * CC - Complexité cyclomatique de McCabe
     * Mesure du nombre de chemins linéairement indépendants
     * @param file le fichier a scanner pour trouver la methode
     * @param methodName le nom du methode pour laquelle il faut calculer le CC
     * @return CC de la methode
     * @throws FileNotFoundException si le fichier a scanner n'est trouve
     */
    public static int measureCCofMethod(File file, String methodName) throws FileNotFoundException {
        int complexity = 1;  // le chemin principal
        Stack<Character> brackets = new Stack<>(); // controle des parentheses pour le debut et le fin de la methode

        Pattern accessPattern = Pattern.compile("public | private | protected");
        Matcher matcher;
        List<String> methodNameMultipleLines = Arrays.asList(methodName.split("\n"));

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcher = accessPattern.matcher(line);

            if (line.contains(methodNameMultipleLines.get(0)) && matcher.find()) {
                boolean match = true;
                int index = 1;
                while (scanner.hasNextLine() && index < methodNameMultipleLines.size()) {
                    line = scanner.nextLine();
                    if (!line.trim().contains(methodNameMultipleLines.get(index)))
                        match = false;
                    index++;
                }

                if (match) {
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
        }

        return 1;
    }

    /**
     * BC : "Bien commente"
     * Calcule le degré selon lequel une méthode est bien commentée methode_BC = methode_DC / CC
     * @param methodDC densité de commentaires de méthode donnee
     * @param methodCC complexité cyclomatique de McCabe de la méthode donnee
     * @return degré de BC
     */
    public static float measureBCofMethod(float methodDC, int methodCC) {
        if (methodDC != -1 && methodCC != -1) {
            return methodDC / methodCC;
        }
        return 0; //methode introuvable
    }

    /**
     * LOC : "Lines Of Code"
     * Calcule le nombre de lignes de code d’une classe a partir de ligne 1 du fichier a partir de ligne 1 du fichier
     * @param file fichier a scanner
     * @return le nombre de LOC de la classe
     * @throws FileNotFoundException si le fichier a scanner n'est trouve
     */
    public static int measureLOCofClass(File file) throws FileNotFoundException {
        int nbLines = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
                if (line.trim().length() > 0)
                    nbLines++;

                // en cas si le commentaire est imbrique en meme ligne que la ligne du code,
                // on double la ligne
                if (line.contains("//") && !line.contains("\"//\""))
                    nbLines++;
        }

        return nbLines;
    }

    /**
     * CLOC : "Comment Lines Of Code"
     * Calcule le nombre de lignes de code d’une classe qui contiennent des commentaires a partir de ligne 1 du fichier
     * @param file le fichier a scanner
     * @return nombre de CLOC de classe principale du fichier
     * @throws FileNotFoundException si le fichier a scanner n'est trouve
     */
    public static int measureCLOCofClass(File file) throws FileNotFoundException {
        int nbLines = 0;
        Stack<String> commentBrackets = new Stack<>(); // controle pour le debut et le fin des commentaires
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().length() > 0) {

                if (!commentBrackets.empty())
                    nbLines++;

                if (line.contains("//") && !line.contains("\"//\""))
                    nbLines++;

                if (line.contains("/*") && !line.contains("\"/*\"") && !line.contains("/**")) {
                    commentBrackets.push("/*");
                    nbLines++;
                }
                if (line.contains("/**") && !line.contains("\"/**\"")) {
                    commentBrackets.push("/**");
                    nbLines++;
                }
                if (line.contains("*/") && !line.contains("\"*/\""))
                    commentBrackets.pop();
            }
        }

        return nbLines;
    }

    /**
     * Calcule la densité de commentaires pour une classe: classe_DC = classe_CLOC/classe_LOC
     * @param classCLOC "Comment Lines Of Code" d'une classe
     * @param classLOC "Lines Of Code" d'une classe
     * @return densité de commentaires pour une classe
     */
    public static float measureDCofClass(int classCLOC, int classLOC) {
        if (classCLOC != -1 && classLOC != -1) {
            return (float) classCLOC / classLOC;
        }
        return 0; //methode introuvable
    }


    /**
     * Calcule le degré selon lequel une classe est bien commentée classe_BC = classe_DC / WMC
     * @param classDC densité de commentaires pour une classe
     * @param classWMC « Weighted Methods per Class »
     * @return classe_BC
     */
    public static float measureBCofClass(float classDC, int classWMC){
        if (classDC != -1 && classWMC != -1) {
            return classDC / classWMC;
        }
        return 0; //methode introuvable
    }

    /**
     * Calcule le WMC : « Weighted Methods per Class », pour chaque classe. C’est la somme pondérée des complexités
     * des méthodes d'une classe. Si toutes les méthodes d'une classe sont de complexité 1, elle est égale au
     * nombre de méthodes.
     * @param methods tous les methodes de la classe
     * @return WMC
     */
    public static int measureWMCofClass(ArrayList<Method> methods) {
        return methods.stream().mapToInt(Method::getCC).sum();
    }


}



