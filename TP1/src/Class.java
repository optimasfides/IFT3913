import java.util.ArrayList;

public class Class {
    private String path;
    private String className;
    private int classe_LOC;
    private int classe_CLOC;
    private int classe_DC;
    private int classe_BC;
    private int WMC;
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

    public int getClasse_LOC() {
        return classe_LOC;
    }

    public void setClasse_LOC(int classe_LOC) {
        this.classe_LOC = classe_LOC;
    }

    public int getClasse_CLOC() {
        return classe_CLOC;
    }

    public void setClasse_CLOC(int classe_CLOC) {
        this.classe_CLOC = classe_CLOC;
    }

    public int getClasse_DC() {
        return classe_DC;
    }

    public void setClasse_DC(int classe_DC) {
        this.classe_DC = classe_DC;
    }

    public int getClasse_BC() {
        return classe_BC;
    }

    public void setClasse_BC(int classe_BC) {
        this.classe_BC = classe_BC;
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
}