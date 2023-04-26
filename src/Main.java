public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.createDirectory("documents");
        fileSystem.createFile("documents/report.txt");
        fileSystem.createDirectory("images");
        fileSystem.createFile("images/photo.jpg");
        GUIInterface gui = new GUIInterface();
        gui.setFileSystem(fileSystem);
    }

}

