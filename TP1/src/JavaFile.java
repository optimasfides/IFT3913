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
    }

    private void setClasses() {
        classes = new ArrayList<Class>();
        // TODO detect classNames and for each class find in file do metrics

        String className = "Main"; // temporary
        Class classe = new Class(getPath(), className);
        classe.setClasse_LOC(MetricsCalculator.classe_LOC(file, className));
        classe.setClasse_CLOC(MetricsCalculator.classe_CLOC(file, className));
        //System.out.println(classe.getClasse_CLOC());
        //System.out.println(classe.getClasse_LOC());

        classe.setClasse_DC(MetricsCalculator.classe_DC(classe.getClasse_CLOC(), classe.getClasse_LOC()));
        //System.out.println(classe.getClasse_DC());

        addClass(classe);
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

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

}
