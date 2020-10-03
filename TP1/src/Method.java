public class Method {
    private String path;        // chemin
    private String className;   // nom de la classe
    private String methodName;  // nom de la methode
    private int methodLOC;   // LOC (Lines of Code) - la mesure de nombre de Lignes de Code
    private int methodCLOC;  // CLOC (Comment Lines Of Code) - la mesure de lignes de code qui sont de commentaires
    private float methodDC;  // DC (Density of Comments) - la mesure de la densité de commentaires
    private float methodBC;  // degré selon lequel une méthode est bien commentée methode_BC = methode_DC / CC
    private int methodCC;    // Complexité cyclomatiquede McCabe

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
        return methodLOC;
    }

    public void setLOC(int LOC) {
        this.methodLOC = LOC;
    }

    public int getCLOC() {
        return methodCLOC;
    }

    public void setCLOC(int CLOC) {
        this.methodCLOC = CLOC;
    }

    public float getDC() {
        return methodDC;
    }

    public void setDC(float DC) {
        this.methodDC = DC;
    }

    public float getBC() {
        return methodBC;
    }

    public void setBC(float BC) {
        this.methodBC = BC;
    }

    public int getCC() {
        return methodCC;
    }

    public void setCC(int CC) {
        this.methodCC = CC;
    }
}
