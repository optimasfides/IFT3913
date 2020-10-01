import java.io.File;
import java.util.ArrayList;

public class JavaFile {
    private String path;
    private File file;
    private ArrayList<Class> classes;
    private ArrayList<Method> methods;

    public JavaFile(String path) {
        setPath(path);
        setFile(new File(path));
        setClasses();
        setMethods();
    }

    private void setClasses() {
        classes = new ArrayList<>();
        ArrayList<String> classNames = Metrics.findClasses(getFile());

        classNames.forEach(className -> {
            Class classe = new Class(getPath(), className);
            classe.setLOC(Metrics.measureLOCofClass(getFile(), className));
            classe.setCLOC(Metrics.measureCLOCofClass(getFile(), className));
            classe.setDC(Metrics.measureDCofClass(classe.getCLOC(), classe.getLOC()));
            classe.setWMC(0); //TODO temporary value 0
            classe.setBC(0); //TODO temporary value 0

            addClass(classe);
        });
    }

    private void setMethods() {
        methods = new ArrayList<>();
        // TODO detect methodNames auto and for each class found in file do metrics

        String className = "Main"; // temporary
        String methodName = "main"; // temporary

        Method method = new Method(getPath(), className, methodName);
        method.setLOC(Metrics.measureLOCofMethod(getFile(), methodName));
        method.setCLOC(Metrics.measureCLOCofMethod(getFile(), methodName));
        method.setDC(Metrics.measureDCofMethod(method.getCLOC(), method.getLOC()));
        method.setCC(Metrics.measureCCofMethod(getFile(), methodName));
        method.setBC(0); //TODO temporary value 0

        addMethod(method);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void addClass(Class classe) {
        this.classes.add(classe);
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
}
