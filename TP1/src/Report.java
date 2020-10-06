import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Partie 4, un rapport general d'analyse d'un projet java
 */
public class Report {
    private ArrayList<JavaFile> javaFiles;
    private int nbJavaFiles;
    private float avgLOCofClasses; // TODO
    private float avgCLOCofClasses; // TODO
    private float maxLOCperClasse; // TODO
    private float maxCLOCperClass; // TODO
    private float avgLOCofMethods; // TODO
    private float avgCLOCofMethods; // TODO
    private float maxLOCperMethod; // TODO
    private float maxCLOCperMethod; // TODO
    private int maxCCperMethod; // TODO
    private int maxWMCperClass; // TODO
    private float maxDCperClass; // TODO
    private float avgBCofClasses; // TODO
    private float maxDCperMethod; // TODO
    private float avgBCofMethodes; // TODO

    public Report(ArrayList<JavaFile> javaFiles) {
        setJavaFiles(javaFiles);
        setNbJavaFiles(javaFiles.size());
    }

    public void createReportFile() {

        try {
            FileWriter fileWriter = new FileWriter("report.txt");
            fileWriter.append("Total java files: " + getNbJavaFiles());
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("File report.txt is created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JavaFile> getJavaFiles() {
        return javaFiles;
    }

    public void setJavaFiles(ArrayList<JavaFile> javaFiles) {
        this.javaFiles = javaFiles;
    }

    public int getNbJavaFiles() {
        return nbJavaFiles;
    }

    public void setNbJavaFiles(int nbJavaFiles) {
        this.nbJavaFiles = nbJavaFiles;
    }

    public float getAvgLOCofClasses() {
        return avgLOCofClasses;
    }

    public void setAvgLOCofClasses(float avgLOCofClasses) {
        this.avgLOCofClasses = avgLOCofClasses;
    }

    public float getAvgCLOCofClasses() {
        return avgCLOCofClasses;
    }

    public void setAvgCLOCofClasses(float avgCLOCofClasses) {
        this.avgCLOCofClasses = avgCLOCofClasses;
    }

    public float getMaxLOCperClasse() {
        return maxLOCperClasse;
    }

    public void setMaxLOCperClasse(float maxLOCperClasse) {
        this.maxLOCperClasse = maxLOCperClasse;
    }

    public float getMaxCLOCperClass() {
        return maxCLOCperClass;
    }

    public void setMaxCLOCperClass(float maxCLOCperClass) {
        this.maxCLOCperClass = maxCLOCperClass;
    }

    public float getAvgLOCofMethods() {
        return avgLOCofMethods;
    }

    public void setAvgLOCofMethods(float avgLOCofMethods) {
        this.avgLOCofMethods = avgLOCofMethods;
    }

    public float getAvgCLOCofMethods() {
        return avgCLOCofMethods;
    }

    public void setAvgCLOCofMethods(float avgCLOCofMethods) {
        this.avgCLOCofMethods = avgCLOCofMethods;
    }

    public float getMaxLOCperMethod() {
        return maxLOCperMethod;
    }

    public void setMaxLOCperMethod(float maxLOCperMethod) {
        this.maxLOCperMethod = maxLOCperMethod;
    }

    public float getMaxCLOCperMethod() {
        return maxCLOCperMethod;
    }

    public void setMaxCLOCperMethod(float maxCLOCperMethod) {
        this.maxCLOCperMethod = maxCLOCperMethod;
    }

    public int getMaxCCperMethod() {
        return maxCCperMethod;
    }

    public void setMaxCCperMethod(int maxCCperMethod) {
        this.maxCCperMethod = maxCCperMethod;
    }

    public int getMaxWMCperClass() {
        return maxWMCperClass;
    }

    public void setMaxWMCperClass(int maxWMCperClass) {
        this.maxWMCperClass = maxWMCperClass;
    }

    public float getMaxDCperClass() {
        return maxDCperClass;
    }

    public void setMaxDCperClass(float maxDCperClass) {
        this.maxDCperClass = maxDCperClass;
    }

    public float getAvgBCofClasses() {
        return avgBCofClasses;
    }

    public void setAvgBCofClasses(float avgBCofClasses) {
        this.avgBCofClasses = avgBCofClasses;
    }

    public float getMaxDCperMethod() {
        return maxDCperMethod;
    }

    public void setMaxDCperMethod(float maxDCperMethod) {
        this.maxDCperMethod = maxDCperMethod;
    }

    public float getAvgBCofMethodes() {
        return avgBCofMethodes;
    }

    public void setAvgBCofMethodes(float avgBCofMethodes) {
        this.avgBCofMethodes = avgBCofMethodes;
    }
}

