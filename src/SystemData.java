public class SystemData {
    public static void initializeFileSystem(FileSystem fileSystem) {
        fileSystem.createDirectory("documents");
        fileSystem.createDirectory("images");
        fileSystem.createDirectory("videos");
        fileSystem.createDirectory("music");
        fileSystem.createDirectory("downloads");

        fileSystem.createFile("documents", "report", "This is some data", 500, "txt");
        fileSystem.createFile("documents", "document", "This is some data", 300, "txt");
        fileSystem.createFile("documents", "note", "This is some data", 250, "txt");

        fileSystem.createFile("images", "photo", "This is some data", 700, "jpg");
        fileSystem.createFile("images", "picture", "This is some data", 600, "png");
        fileSystem.createFile("images", "image", "This is some data", 800, "jpg");

        fileSystem.createFile("videos", "video", "This is some data", 1000, "mp4");
        fileSystem.createFile("videos", "movie", "This is some data", 2000, "mp4");
        fileSystem.createFile("videos", "clip", "This is some data", 1500, "mp4");

        fileSystem.createFile("music", "song", "This is some data", 500, "mp3");
        fileSystem.createFile("music", "track", "This is some data", 600, "mp3");
        fileSystem.createFile("music", "music", "This is some data", 700, "mp3");

        fileSystem.createFile("downloads", "file", "This is some data", 1000, "zip");
        fileSystem.createFile("downloads", "data", "This is some data", 1100, "zip");
        fileSystem.createFile("downloads", "archive", "This is some data", 1200, "zip");
    }

}