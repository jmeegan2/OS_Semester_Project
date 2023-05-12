import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingUI_GUI extends JFrame {
    private JPanel MainPanel;
    private JScrollPane SideBarScroll;
    private JButton SearchBTN;
    private JButton DeleteBTN;
    private JButton UpdateBTN;
    private JButton CreateBTN;
    private JList<String> list1; // Updated to JList
    private JPanel ListArea;
    private JButton RefreshBTN;
    private FileSystem fileSystem;

    public SwingUI_GUI(String title) {
        super(title);
        this.fileSystem = new FileSystem();

        SearchBTN.setPreferredSize(new Dimension(200, 75));
        DeleteBTN.setPreferredSize(new Dimension(200, 75));
        UpdateBTN.setPreferredSize(new Dimension(200, 75));
        CreateBTN.setPreferredSize(new Dimension(200, 75));
        RefreshBTN.setPreferredSize(new Dimension(200, 75));

        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode
        list1.setLayoutOrientation(JList.VERTICAL); // Set layout orientation

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        refreshListArea();
        RefreshBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshListArea();
            }
        });
    }

    public static void main(String[] args) {
        SwingUI_GUI frame = new SwingUI_GUI("File Simulation System 439");
        frame.setSize(1250, 1000);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        FileSystem fileSystem = new FileSystem();
        frame.setFileSystem(fileSystem);
        fileSystem.createDirectory("documents");
        fileSystem.createFile("documents/report.txt");
        fileSystem.createDirectory("images");
        fileSystem.createFile("images/photo.jpg");

    }

    private void refreshListArea() {
        DefaultListModel<String> model = new DefaultListModel<>(); // Create a DefaultListModel
        List<File> files = fileSystem.getCurrentDirectory().getFiles();
        for (File file : files) {
            model.addElement(file.getName()); // Add file name to the model
        }

        list1.setModel(model); // Set the model to the JList
    }
    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }
}
