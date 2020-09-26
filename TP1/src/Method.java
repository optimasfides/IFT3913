public class Method {
    private String path;
    private String className;
    private String methodName;
    private int methode_LOC;
    private int methode_CLOC;
    private int methode_DC;
    private int methode_BC;
    private int CC;

    public Method(String path, String className, String methodName) {
        this.path = path;
        this.className = className;
        this.methodName = methodName;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getMethode_LOC() {
        return methode_LOC;
    }

    public void setMethode_LOC(int methode_LOC) {
        this.methode_LOC = methode_LOC;
    }

    public int getMethode_CLOC() {
        return methode_CLOC;
    }

    public void setMethode_CLOC(int methode_CLOC) {
        this.methode_CLOC = methode_CLOC;
    }

    public int getMethode_DC() {
        return methode_DC;
    }

    public void setMethode_DC(int methode_DC) {
        this.methode_DC = methode_DC;
    }

    public int getMethode_BC() {
        return methode_BC;
    }

    public void setMethode_BC(int methode_BC) {
        this.methode_BC = methode_BC;
    }

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }
}
