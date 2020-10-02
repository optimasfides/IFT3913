import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import java.util.*;


/**
 * PARTIE 1 et 3
 * Le classe, étant donné le fichier source d'une classe java, calcule les métriques
 */
public class Metrics {


    /**
     * @param file
     * @param nomClasse
     * @return nombre de lignes de code d’une classe
     */
    public static int methodName(File file) {
    	/*
    	commetns
    	*/
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



}



