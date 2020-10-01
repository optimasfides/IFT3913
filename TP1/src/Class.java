import java.util.ArrayList;

public class Class {
    private String path;
    private String className;
    private int LOC;   // LOC (Lines of Code) - la mesure de nombre de Lignes de Code
    private int CLOC;  // CLOC (Comment Lines Of Code) - la mesure de lignes de code qui sont de commentaires
    private float DC;    // DC (Density of Comments) - la mesure de la densité de commentaires
    private float BC;
    private int WMC;   // WMC (Weighted Methods per Class)
    private ArrayList<Method> methods;

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
        return WMC;
    }

    public void setWMC(int WMC) {
        this.WMC = WMC;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        this.methods.add(method);
    }

    public int getLOC() {
        return LOC;
    }

    public void setLOC(int LOC) {
        this.LOC = LOC;
    }

    public int getCLOC() {
        return CLOC;
    }

    public void setCLOC(int CLOC) {
        this.CLOC = CLOC;
    }

    public float getDC() {
        return DC;
    }

    public void setDC(float DC) {
        this.DC = DC;
    }

    public float getBC() {
        return BC;
    }

    public void setBC(float BC) {
        this.BC = BC;
    }
}