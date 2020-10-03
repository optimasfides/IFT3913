
public class Class {
    private String path;        // chemin
    private String className;   // nom de la classe
    private int classLOC;   // LOC (Lines of Code) - la mesure de nombre de Lignes de Code
    private int classCLOC;  // CLOC (Comment Lines Of Code) - la mesure de lignes de code qui sont de commentaires
    private float classDC;  // DC (Density of Comments) - la mesure de la densité de commentaires
    private float classBC;  // degré selon lequel une classe est bien commentée classe_BC = classe_DC / WMC
    private int classWMC;   // WMC (Weighted Methods per Class)

    public Class(String path, String className) {
        setPath(path);
        setClassName(className);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getWMC() {
        return classWMC;
    }

    public void setWMC(int WMC) {
        this.classWMC = WMC;
    }

    public int getLOC() {
        return classLOC;
    }

    public void setLOC(int LOC) {
        this.classLOC = LOC;
    }

    public int getCLOC() {
        return classCLOC;
    }

    public void setCLOC(int CLOC) {
        this.classCLOC = CLOC;
    }

    public float getDC() {
        return classDC;
    }

    public void setDC(float DC) {
        this.classDC = DC;
    }

    public float getBC() {
        return classBC;
    }

    public void setBC(float BC) {
        this.classBC = BC;
    }
}