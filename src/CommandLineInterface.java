import java.util.Scanner;

public class CommandLineInterface {
    private FileSystem fileSystem;
    private Scanner scanner;

    public CommandLineInterface() {
        this.fileSystem = new FileSystem();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print(this.fileSystem.getCurrentDirectory().getName() + "$ ");
            String input = this.scanner.nextLine();
            String[] commands = input.split("\\s+");

            switch (commands[0]) {
                case "cd":
                    if (commands.length != 2) {
                        System.out.println("Invalid command: cd requires a directory path");
                    } else {
                        this.fileSystem.changeDirectory(commands[1]);
                    }
                    break;
                case "ls":
                    this.fileSystem.listContents();
                    break;
                case "mkdir":
                    if (commands.length != 2) {
                        System.out.println("Invalid command: mkdir requires a directory name");
                    } else {
                        this.fileSystem.createDirectory(commands[1]);
                    }
                    break;
                case "touch":
                    if (commands.length != 2) {
                        System.out.println("Invalid command: touch requires a file name");
                    } else {
//                        this.fileSystem.createFile(commands[1]);
                    }
                    break;
                case "rm":
                    if (commands.length != 2) {
                        System.out.println("Invalid command: rm requires a file or directory name");
                    } else if (this.fileSystem.getCurrentDirectory().getName().equals("/") && commands[1].equals("/")) {
                        System.out.println("Cannot delete root directory");
                    } else {
                        if (commands[1].startsWith("/")) {
                            this.fileSystem.changeDirectory("/");
                        }

                        if (commands[1].endsWith("/")) {
                            this.fileSystem.deleteDirectory(commands[1].substring(0, commands[1].length() - 1));
                        } else {
                            this.fileSystem.deleteFile(commands[1]);
                        }
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command: " + input);
            }
        }
    }
}
