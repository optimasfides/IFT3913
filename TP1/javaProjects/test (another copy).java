package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainTEST2{

    public static int compterLignes(Scanner fichierJava, String ligneDeCode, int nbLigne, String charDebut, String charFin, boolean compterCommentaires){
        int compteurImbrique = 0;
        do {
            if (ligneDeCode.length() == 0) { //si c'est une ligne vide on passe cette ligne
                ligneDeCode = fichierJava.nextLine();
                continue;
            }
            if(compterCommentaires){
                if(ligneDeCode.contains("//")) ligneDeCode = ligneDeCode.substring(0, ligneDeCode.indexOf("//"));
                if
            }
            int index;
            while ((ligneDeCode.contains(charDebut)) || (ligneDeCode.contains(charFin))) { //on verifie si sur cette ligne du code on a un charactère recherché
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
/* 
/*
*/
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

    public static int classe_CLOC(Scanner fichierJava, String nomClasse){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains("class " + nomClasse)){
                return compterLignes(fichierJava, ligneDeCode, 0,  "{", "}", true);
            }
        }
        return -1; //classe introuvable
    }

    public static int methode_LOC(Scanner fichierJava, String nomMethode){

        while (fichierJava.hasNextLine()){
            String ligneDeCode = fichierJava.nextLine();

            if (ligneDeCode.contains(nomMethode + "(")){
                return compterLignes(fichierJava, ligneDeCode, 0, "{", "}", false);
            }
        }
        return -1; //methode introuvable
    }
/*l

j
*/

    public static void main(String[] args) throws FileNotFoundException { //il faut creer new scanner pour chaque comptage de ligne
        File file = new File("test.java");
        String nomClasse = "Main";
        String nomMethode = "main";
        Scanner sc = new Scanner(file);
        System.out.println("nombre de lignes de code de la classe " + nomClasse + " : " + classe_LOC(sc, nomClasse));
        sc = new Scanner(file);
        System.out.println("nombre de lignes de code de la methode " + nomMethode + " : " + methode_LOC(sc, nomMethode));
        sc = new Scanner(file);
        System.out.println("nombre de lignes de code AVEC COMMENT de la classe " + nomClasse + " : " + classe_CLOC(sc, nomClasse));
    }
}
