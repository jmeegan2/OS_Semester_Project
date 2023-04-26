
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInterface extends JFrame implements ActionListener {
    private FileSystem fileSystem;
    private JPanel currentDirectoryPanel;
    private JPanel contentsPanel;
    private JTextField createField;

    public GUIInterface() {

        super("Java File System Simulation");
        System.out.println("GUI interface created");
        this.fileSystem = new FileSystem();
        this.currentDirectoryPanel = new JPanel();
        this.contentsPanel = new JPanel();
        this.createField = new JTextField(20);

        JLabel currentDirectoryLabel = new JLabel("Current directory: ");
        JLabel currentDirectoryName = new JLabel(this.fileSystem.getCurrentDirectory().getName());

        contentsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton createButton = new JButton("Create");
        createButton.addActionListener(this);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(this);

        JButton changeDirectoryButton = new JButton("Change Directory");
        changeDirectoryButton.addActionListener(this);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(changeDirectoryButton);
        buttonPanel.add(refreshButton);

        this.currentDirectoryPanel.add(currentDirectoryLabel);
        this.currentDirectoryPanel.add(currentDirectoryName);
        this.currentDirectoryPanel.add(this.createField);
        this.currentDirectoryPanel.add(buttonPanel);

        this.add(this.currentDirectoryPanel, BorderLayout.NORTH);
        this.add(this.contentsPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Performed");
        if (e.getActionCommand().equals("Create")) {
            String fileName = this.createField.getText();
            if (!fileName.isEmpty()) {
                if (fileName.endsWith("/")) {
                    this.fileSystem.createDirectory(fileName.substring(0, fileName.length() - 1));
                } else {
                    this.fileSystem.createFile(fileName);
                }
                this.createField.setText("");
                refreshContents();
            }
        }
    }

    private void refreshContents() {
        System.out.println("Refreshing contents");
        this.contentsPanel.removeAll();
        this.contentsPanel.setLayout(new BoxLayout(this.contentsPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name");
        JLabel typeLabel = new JLabel("Type");

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(typeLabel, BorderLayout.EAST);
        this.contentsPanel.add(headerPanel);

        for (Directory subDirectory : this.fileSystem.getCurrentDirectory().getSubDirectories()) {
            JLabel name = new JLabel(subDirectory.getName());
            JLabel type = new JLabel("Directory");
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.add(name, BorderLayout.WEST);
            itemPanel.add(type, BorderLayout.EAST);
            this.contentsPanel.add(itemPanel);
        }

        for (File file : this.fileSystem.getCurrentDirectory().getFiles()) {
            JLabel name = new JLabel(file.getName());
            JLabel type = new JLabel("File");
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.add(name, BorderLayout.WEST);
            itemPanel.add(type, BorderLayout.EAST);
            this.contentsPanel.add(itemPanel);
        }

        this.pack();
    }

    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

}

