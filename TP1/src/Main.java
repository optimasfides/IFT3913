import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException { //il faut creer new scanner pour chaque comptage de ligne

        CSVWriter.creerCSV_classes();
        CSVWriter.creerCSV_methodes();


        File file = new File("test.java");
        String nomClasse = "Main";
        String nomMethode = "main";
        Scanner sc = new Scanner(file);
        System.out.println("nombre de lignes de code de la classe " + nomClasse + " : " + MetricsCalculator.classe_LOC(sc, nomClasse));
        sc = new Scanner(file);
        System.out.println("nombre de lignes de code de la méthode " + nomMethode + " : " + MetricsCalculator.methode_LOC(sc, nomMethode));
        sc = new Scanner(file);
        System.out.println("nombre de lignes de code contenant entre autres des commentaires de la classe " + nomClasse + " : " + MetricsCalculator.classe_CLOC(sc, nomClasse));
        sc = new Scanner(file);
        System.out.println("nombre de lignes de code contenant entre autres des commentaires de la méthode " + nomMethode + " : " + MetricsCalculator.methode_CLOC(sc, nomMethode));
    }
}
