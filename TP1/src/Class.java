import java.util.ArrayList;

public class Class {
    private String path;
    private String className;
    private int LOC;
    private int CLOC;
    private int DC;
    private int BC;
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

    public int getDC() {
        return DC;
    }

    public void setDC(int DC) {
        this.DC = DC;
    }

    public int getBC() {
        return BC;
    }

    public void setBC(int BC) {
        this.BC = BC;
    }
}