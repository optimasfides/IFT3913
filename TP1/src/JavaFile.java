import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JavaFile {
    private String path;
    private File file;
    private Class classe;
    private ArrayList<Method> methods;

    public JavaFile(String path) {
        setPath(path);
        setFile(new File(path));
        setClasse(new Class(getPath(), Parser.extractClassName(getFile())));
        measureMethods();
        measureClass();
    }

    private void setClasse(Class classe) {
        this.classe = classe;
    }

    private void measureClass() {
        try {
            getClasse().setLOC(Metrics.measureLOCofClass(getFile(), getClasse().getClassName()));
            getClasse().setCLOC(Metrics.measureCLOCofClass(getFile(), getClasse().getClassName()));
            getClasse().setDC(Metrics.measureDCofClass(getClasse().getCLOC(), getClasse().getLOC()));
            getClasse().setWMC(Metrics.measureWMCofClass(getMethods()));
            getClasse().setBC(Metrics.measureBCofClass(getClasse().getDC(), getClasse().getWMC()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void measureMethods() {
        methods = new ArrayList<>();
        ArrayList<String> methodNames = Parser.extractMethodNames(getFile());
        methodNames.forEach(methodName -> {
            try {
                Method method = new Method(getPath(), getClasse().getClassName(), methodName);
                method.setLOC(Metrics.measureLOCofMethod(getFile(), methodName));
                method.setCLOC(Metrics.measureCLOCofMethod(getFile(), methodName));
                method.setDC(Metrics.measureDCofMethod(method.getCLOC(), method.getLOC()));
                method.setCC(Metrics.measureCCofMethod(getFile(), methodName));
                method.setBC(Metrics.measureBCofMethod(method.getDC(), method.getCC()));
                addMethod(method);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        this.methods.add(method);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Class getClasse() {
        return classe;
    }

}
