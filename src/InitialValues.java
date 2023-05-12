public class InitialValues {
    public static void initializeFileSystem(FileSystem fileSystem) {
        fileSystem.createDirectory("documents");
        fileSystem.createDirectory("images");
        fileSystem.createDirectory("videos");
        fileSystem.createDirectory("music");
        fileSystem.createDirectory("downloads");

        fileSystem.createFile("documents/report.txt");
        fileSystem.createFile("documents/document.txt");
        fileSystem.createFile("documents/note.txt");

        fileSystem.createFile("images/photo.jpg");
        fileSystem.createFile("images/picture.png");
        fileSystem.createFile("images/image.jpg");

        fileSystem.createFile("videos/video.mp4");
        fileSystem.createFile("videos/movie.mp4");
        fileSystem.createFile("videos/clip.mp4");

        fileSystem.createFile("music/song.mp3");
        fileSystem.createFile("music/track.mp3");
        fileSystem.createFile("music/music.mp3");

        fileSystem.createFile("downloads/file.zip");
        fileSystem.createFile("downloads/data.zip");
        fileSystem.createFile("downloads/archive.zip");
    }
}