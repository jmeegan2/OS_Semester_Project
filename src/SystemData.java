public class SystemData {
    public static void initializeFileSystem(FileSystem fileSystem) {
        fileSystem.createDirectory("documents");
        fileSystem.createDirectory("images");
        fileSystem.createDirectory("videos");
        fileSystem.createDirectory("music");
        fileSystem.createDirectory("downloads");

        fileSystem.createFile("documents/report.txt", "This is some data", 500, "txt");
        fileSystem.createFile("documents/document.txt", "This is some data", 300, "txt");
        fileSystem.createFile("documents/note.txt", "This is some data", 250, "txt");

        fileSystem.createFile("images/photo.jpg", "This is some data", 700, "jpg");
        fileSystem.createFile("images/picture.png", "This is some data", 600, "png");
        fileSystem.createFile("images/image.jpg", "This is some data", 800, "jpg");

        fileSystem.createFile("videos/video.mp4", "This is some data", 1000, "mp4");
        fileSystem.createFile("videos/movie.mp4", "This is some data", 2000, "mp4");
        fileSystem.createFile("videos/clip.mp4", "This is some data", 1500, "mp4");

        fileSystem.createFile("music/song.mp3", "This is some data", 500, "mp3");
        fileSystem.createFile("music/track.mp3", "This is some data", 600, "mp3");
        fileSystem.createFile("music/music.mp3", "This is some data", 700, "mp3");

        fileSystem.createFile("downloads/file.zip", "This is some data", 1000, "zip");
        fileSystem.createFile("downloads/data.zip", "This is some data", 1100, "zip");
        fileSystem.createFile("downloads/archive.zip", "This is some data", 1200, "zip");
    }
}