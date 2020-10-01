public class Method {
    private String path;
    private String className;
    private String methodName;
    private int LOC;
    private int CLOC;
    private float DC;
    private float BC;
    private int CC;    // Complexité cyclomatiquede McCabe

    public Method(String path, String className, String methodName) {
        setPath(path);
        setClassName(className);
        setMethodName(methodName);
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

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }
}
