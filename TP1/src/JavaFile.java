import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JavaFile {
    private String path;
    private File file;
    private ArrayList<Class> classes;
    private ArrayList<Method> methods;

    public JavaFile(String path) {
        this.path = path;
        this.file = new File(path);
        setClasses();
        setMethods();
    }

    private void findClasses() {
        // TODO
    }

    private void findMethods() {
        // TODO
    }

    private void setClasses() {
        classes = new ArrayList<Class>();
        // TODO detect classNames auto and for each class find in file do metrics

        String className = "Main"; // temporary
        Class classe = new Class(getPath(), className);
        classe.setClasse_LOC(MetricsCalculator.classe_LOC(getFile(), className));
        classe.setClasse_CLOC(MetricsCalculator.classe_CLOC(getFile(), className));
        classe.setClasse_DC(MetricsCalculator.classe_DC(classe.getClasse_CLOC(), classe.getClasse_LOC()));

        addClass(classe);
    }

    private void setMethods() {
        methods = new ArrayList<Method>();
        // TODO detect methodNames auto and for each class found in file do metrics

        String className = "Main"; // temporary
        String methodName = "main"; // temporary

        Method method = new Method(getPath(), className, methodName);
        method.setMethode_LOC(MetricsCalculator.methode_LOC(getFile(), methodName));
        method.setMethode_CLOC(MetricsCalculator.methode_CLOC(getFile(), methodName));
        method.setMethode_DC(MetricsCalculator.classe_DC(method.getMethode_CLOC(), method.getMethode_LOC()));

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
