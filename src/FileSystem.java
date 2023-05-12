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

    public void changeDirectory(String path) {
        if (path.equals("/")) {
            this.currentDirectory = this.rootDirectory;
            return;
        }

        String[] directories = path.split("/");

        for (String directoryName : directories) {
            if (directoryName.isEmpty()) {
                continue;
            }

            boolean found = false;

            for (Directory subDirectory : this.currentDirectory.getSubDirectories()) {
                if (subDirectory.getName().equals(directoryName)) {
                    this.currentDirectory = subDirectory;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Directory not found: " + directoryName);
                return;
            }
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
    public void createFile(String name) {
        this.currentDirectory.addFile(new File(name, null));
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

    public void listContents() {
        System.out.println("Contents of directory " + this.currentDirectory.getName() + ":");

        for (Directory subDirectory : this.currentDirectory.getSubDirectories()) {
            System.out.println("d " + subDirectory.getName());
        }

        for (File file : this.currentDirectory.getFiles()) {
            System.out.println("- " + file.getName());
        }
    }
}
