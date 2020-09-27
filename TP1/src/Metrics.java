import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * PARTIE 1 et 3
 * Le classe, étant donné le fichier source d'une classe java, calcule les métriques
 */
public class Metrics {


    public static int compterLignes(Scanner fichierJava, String ligneDeCode, int nbLigne, String charDebut, String charFin, boolean contientCommentaires) {
        int compteurImbrique = 0;
        do {
            if (ligneDeCode.length() == 0) { //si c'est une ligne vide on passe cette ligne
                ligneDeCode = fichierJava.nextLine();
                continue;
            }
            if (contientCommentaires) { //si contientCommentaires est true, on prend en compte les commentaires
                if (ligneDeCode.contains("//")) ligneDeCode = ligneDeCode.substring(0, ligneDeCode.indexOf("//"));
                if (ligneDeCode.contains("/*")) {
                    nbLigne = compterLignes(fichierJava, ligneDeCode, nbLigne, "/*", "*/", false);
                    if (fichierJava.hasNextLine()) ligneDeCode = fichierJava.nextLine();
                    continue;
                }
            }
            int index;
            while ((ligneDeCode.contains(charDebut)) || (ligneDeCode.contains(charFin))) { //dans ce while, on verifie si sur cette ligne du code on a un charactère recherché
                if ((ligneDeCode.indexOf(charDebut) < ligneDeCode.indexOf(charFin) || ligneDeCode.indexOf(charFin) == -1) && ligneDeCode.indexOf(charDebut) != -1) {
                    compteurImbrique++;
                    index = ligneDeCode.indexOf(charDebut);
                } else {
                    compteurImbrique--;
                    index = ligneDeCode.indexOf(charFin);
                }
                ligneDeCode = ligneDeCode.substring(index + 1);
            }
            nbLigne++;
            if (fichierJava.hasNextLine()) ligneDeCode = fichierJava.nextLine();
        }
        while (compteurImbrique != 0);
        return nbLigne;
    }

    /**
     * @param file
     * @param nomClasse
     * @return nombre de lignes de code d’une classe
     */
    public static int measureLOCofClass(File file, String nomClasse) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligneDeCode = scanner.nextLine();

                if (ligneDeCode.contains("class " + nomClasse)) {
                    return compterLignes(scanner, ligneDeCode, 0, "{", "}", false);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1; //classe introuvable
    }

    /**
     * @param file
     * @param nomClasse
     * @return nombre de lignes de code d’une classe qui contiennent des commentaires
     */
    public static int measureCLOCofClass(File file, String nomClasse) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligneDeCode = scanner.nextLine();

                if (ligneDeCode.contains("class " + nomClasse)) {
                    return compterLignes(scanner, ligneDeCode, 0, "{", "}", true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1; //classe introuvable
    }

    /**
     * @param file
     * @param nomMethode
     * @return nombre de lignes de code d’une méthode
     */
    public static int measureLOCofMethod(File file, String nomMethode) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligneDeCode = scanner.nextLine();

                if (ligneDeCode.contains(nomMethode + "(")) {
                    return compterLignes(scanner, ligneDeCode, 0, "{", "}", false);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1; //classe introuvable

    }

    /**
     * @param file
     * @param nomMethode
     * @return nombre de lignes de code d’une méthode qui contiennent des commentaires
     */
    public static int measureCLOCofMethod(File file, String nomMethode) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String ligneDeCode = scanner.nextLine();

                if (ligneDeCode.contains(nomMethode + "(")) {
                    return compterLignes(scanner, ligneDeCode, 0, "{", "}", true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1; //classe introuvable
    }

    /**
     * densité de commentaires pour une méthode: methode_DC= methode_CLOC/methode_LOC
     *
     * @param methode_CLOC
     * @param methode_LOC
     * @return densité de commentaires pour une méthode: methode_DC
     */
    public static int measureDCofMethod(Integer methode_CLOC, Integer methode_LOC) {
        if (methode_CLOC != -1 && methode_LOC != -1) {
            return methode_CLOC / methode_LOC;
        }
        return -1; //methode introuvable
    }

    /**
     * densité de commentaires pour une classe: classe_DC= classe_CLOC/classe_LOC
     *
     * @param classe_CLOC
     * @param classe_LOC
     * @return densité de commentaires pour une classe
     */
    public static int measureDCofClass(Integer classe_CLOC, Integer classe_LOC) {
        if (classe_CLOC != -1 && classe_LOC != -1) {
            return classe_CLOC / classe_LOC;
        }
        return -1; //methode introuvable
    }

    /**
     * Cherche tous les classes dans le fichier donnee.
     * Traite les interfaces, les énumérations et les classes abstraites comme des classes.
     *
     * @param file
     * @return ArrayList<String> classNames
     */
    public static ArrayList<String> findClasses(File file) {
        ArrayList<String> classNames = new ArrayList<>();
        List<String> words;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(" class ")) {
                    words = Arrays.asList(line.split(" "));
                    String className = words.get(1 + words.indexOf("class"));
                    if (className.contains("{")) {
                        className = className.substring(0, className.length() - 1);
                    }
                    classNames.add(className);
                } else if (line.contains(" enum ")) {
                    words = Arrays.asList(line.split(" "));
                    String className = words.get(1 + words.indexOf("enum"));
                    if (className.contains("{")) {
                        className = className.substring(0, className.length() - 1);
                    }
                    classNames.add(className);
                } else if (line.contains(" interface ")) {
                    words = Arrays.asList(line.split(" "));
                    String className = words.get(1 + words.indexOf("interface"));
                    if (className.contains("{")) {
                        className = className.substring(0, className.length() - 1);
                    }
                    classNames.add(className);
                }
            }
            return classNames;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return classNames;
    }

    /**
     * Cherche tous les methodes dans le fichier donnee.
     *
     * @param file
     * @return cArrayList<String> methodNames
     */
    public static ArrayList<String> findMethods(File file) {
        ArrayList<String> methodNames = new ArrayList<>();
        List<String> words;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //TODO

            }
            return methodNames;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return methodNames;
    }

    public static void measureBCofMethod(){
        //TODO
    }

    public static void measureBCofClass(){
        //TODO
    }

    public static void measureCCofMethod(){
        //TODO
    }

    public static void measureWMCofClass(){
        //TODO
    }
}



