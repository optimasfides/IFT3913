import java.io.File;
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
        setMethods(new ArrayList<>());
    }

    private void setClasse(Class classe) {
        this.classe = classe;
    }

    private void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
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
