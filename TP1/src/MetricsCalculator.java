import java.util.Scanner;

/**
 * PARTIE 1
 * Le classe, étant donné le fichier source d'une classe java, calcule les métriques
 */
public class MetricsCalculator {


    public static int compterLignes(Scanner fichierJava, String ligneDeCode, int nbLigne, String charDebut, String charFin, boolean contientCommentaires){
        int compteurImbrique = 0;
        do {
            if (ligneDeCode.length() == 0) { //si c'est une ligne vide on passe cette ligne
                ligneDeCode = fichierJava.nextLine();
                continue;
            }
            if(contientCommentaires){ //si contientCommentaires est true, on prend en compte les commentaires
                if(ligneDeCode.contains("//")) ligneDeCode = ligneDeCode.substring(0, ligneDeCode.indexOf("//"));
                if(ligneDeCode.contains("/*")) {
                    nbLigne = compterLignes(fichierJava,ligneDeCode,nbLigne,"/*","*/",false);
                    if(fichierJava.hasNextLine()) ligneDeCode = fichierJava.nextLine();
                    continue;
                }
            }
            int index;
            while ((ligneDeCode.contains(charDebut)) || (ligneDeCode.contains(charFin))) { //dans ce while, on verifie si sur cette ligne du code on a un charactère recherché
                if((ligneDeCode.indexOf(charDebut) < ligneDeCode.indexOf(charFin) || ligneDeCode.indexOf(charFin) == -1) && ligneDeCode.indexOf(charDebut) != -1){
                    compteurImbrique++;
                    index = ligneDeCode.indexOf(charDebut);
                }
                else {
                    compteurImbrique--;
                    index = ligneDeCode.indexOf(charFin);
                }
                ligneDeCode = ligneDeCode.substring(index + 1);
            }
            nbLigne++;
            if(fichierJava.hasNextLine()) ligneDeCode = fichierJava.nextLine();
        }
        while(compteurImbrique != 0);
        return nbLigne;
    }

    /**
     *
     * @param fichierJava
     * @param nomClasse
     * @return  nombre de lignes de code d’une classe
     */
    public static int classe_LOC(Scanner fichierJava, String nomClasse){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains("class " + nomClasse)){
                return compterLignes(fichierJava, ligneDeCode, 0, "{", "}", false);
            }
        }
        return -1; //classe introuvable
    }

    /**
     *
     * @param fichierJava
     * @param nomClasse
     * @return nombre de lignes de code d’une classe qui contiennent des commentaires
     */
    public static int classe_CLOC(Scanner fichierJava, String nomClasse){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains("class " + nomClasse)){
                return compterLignes(fichierJava, ligneDeCode, 0,  "{", "}", true);
            }
        }
        return -1; //classe introuvable
    }

    /**
     *
     * @param fichierJava
     * @param nomMethode
     * @return nombre de lignes de code d’une méthode
     */
    public static int methode_LOC(Scanner fichierJava, String nomMethode){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains(nomMethode + "(")){
                return compterLignes(fichierJava, ligneDeCode, 0, "{", "}", false);
            }
        }
        return -1; //methode introuvable
    }

    /**
     *
     * @param fichierJava
     * @param nomMethode
     * @return nombre de lignes de code d’une méthode qui contiennent des commentaires
     */
    public static int methode_CLOC(Scanner fichierJava, String nomMethode){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains(nomMethode + "(")){
                return compterLignes(fichierJava, ligneDeCode, 0, "{", "}", true);
            }
        }
        return -1; //methode introuvable
    }

    /**
     * densité de commentaires pour une méthode: methode_DC= methode_CLOC/methode_LOC
     * @param methode_CLOC
     * @param methode_LOC
     * @return densité de commentaires pour une méthode: methode_DC
     */
    public static int methode_DC(Integer methode_CLOC, Integer methode_LOC ){
        if (methode_CLOC != -1 && methode_LOC != -1)  {
            return methode_CLOC / methode_LOC;
        }
        return -1; //methode introuvable
    }

    /**
     * densité de commentaires pour une classe: classe_DC= classe_CLOC/classe_LOC
     * @param classe_CLOC
     * @param classe_LOC
     * @return densité de commentaires pour une classe
     */
    public static int classe_DC(Integer classe_CLOC, Integer classe_LOC ){
        if (classe_CLOC != -1 && classe_LOC != -1)  {
            return classe_CLOC / classe_LOC;
        }
        return -1; //methode introuvable
    }
}

