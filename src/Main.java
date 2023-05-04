public class Main {
    public static void main(String[] args) {
        GUIInterface gui = new GUIInterface();
        FileSystem fileSystem = new FileSystem();
        gui.setFileSystem(fileSystem);
        fileSystem.createDirectory("documents");
        fileSystem.createFile("documents/report.txt");
        fileSystem.createDirectory("images");
        fileSystem.createFile("images/photo.jpg");


    }

}

