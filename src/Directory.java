import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private List<File> files;
    private List<Directory> subDirectories;

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.subDirectories = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<File> getFiles() {
        return this.files;
    }

    public List<Directory> getSubDirectories() {
        return this.subDirectories;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void removeFile(File file) {
        this.files.remove(file);
    }

    public void addSubDirectory(Directory directory) {
        this.subDirectories.add(directory);
    }

    public void removeSubDirectory(Directory directory) {
        this.subDirectories.remove(directory);
    }
}