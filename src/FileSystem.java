import java.util.List;

public class FileSystem {
    private RootDirectory rootDirectory;
    private Directory currentDirectory;


    public FileSystem() {
        this.rootDirectory = new RootDirectory();
        this.currentDirectory = this.rootDirectory;
    }

    public Directory getCurrentDirectory() {
        return this.currentDirectory;
    }

    public File getFile(String fileName) {
        List<File> files = currentDirectory.getFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null; // File not found
    }

    public String searchFile(String fileName) {
        if (isFileExists(fileName)) {
            String directory = getCurrentDirectory().getName();
            return "File '" + fileName + "' exists\nDirectory: " + getCurrentDirectory();
        } else {
            return "File '" + fileName + "' does not exist";
        }
    }

    public boolean isFileExists(String fileName) {
        Directory currentDirectory = getCurrentDirectory();
        List<File> files = currentDirectory.getFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return true; // File exists
            }
        }
        return false; // File does not exist
    }
    public void createFile(String directoryName,String name, Object data, int size, String type) {
        this.currentDirectory.addFile(new File(directoryName,name, data, size, type));
    }

    public void createDirectory(String name) {
        this.currentDirectory.addSubDirectory(new Directory(name));
    }

    public void deleteFile(String name) {
        for (File file : this.currentDirectory.getFiles()) {
            if (file.getName().equals(name)) {
                this.currentDirectory.removeFile(file);
                return;
            }
        }

        System.out.println("File not found: " + name);
    }

    public void deleteDirectory(String name) {
        for (Directory subDirectory : this.currentDirectory.getSubDirectories()) {
            if (subDirectory.getName().equals(name)) {
                this.currentDirectory.removeSubDirectory(subDirectory);
                return;
            }
        }

        System.out.println("Directory not found: " + name);
    }

    public void updateFile(String fileName, String newName) {
        List<File> files = currentDirectory.getFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                file.setName(newName);
                return;
            }
        }
        throw new IllegalArgumentException("File not found: " + fileName);
    }

    public boolean findSubDirectory(String directoryName) {
        for (Directory subDirectory : this.currentDirectory.getSubDirectories()) {
            if (subDirectory.getName().equals(directoryName)) {
                return true;
            }
        }
        return false;
    }


}
