
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

        // add padding to the currentDirectoryPanel
        this.currentDirectoryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // create buttons with icons and set their backgrounds and font colors
        JButton createButton = new JButton("Create", new ImageIcon("create.png"));
        createButton.setPreferredSize(new Dimension(120, 30)); // increase button size
        createButton.setBackground(Color.WHITE);
        createButton.setForeground(Color.DARK_GRAY);
        createButton.addActionListener(this);

        JButton deleteButton = new JButton("Delete", new ImageIcon("delete.png"));
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.DARK_GRAY);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to execute when deleteButton is clicked
                String fileName = createField.getText();
                fileSystem.deleteFile(fileName);
                refreshContents();
                createField.setText("");
            }
        });


        JButton editButton = new JButton("Edit", new ImageIcon("edit.png"));
        editButton.setPreferredSize(new Dimension(120, 30)); // increase button size
        editButton.setBackground(Color.WHITE);
        editButton.setForeground(Color.DARK_GRAY);
        editButton.addActionListener(this);

        JButton changeDirectoryButton = new JButton("Change Directory", new ImageIcon("change.png"));
        changeDirectoryButton.setPreferredSize(new Dimension(150, 30)); // increase button size
        changeDirectoryButton.setBackground(Color.WHITE);
        changeDirectoryButton.setForeground(Color.DARK_GRAY);
        changeDirectoryButton.addActionListener(this);

        JButton refreshButton = new JButton("Refresh", new ImageIcon("refresh.png"));
        refreshButton.setPreferredSize(new Dimension(120, 30)); // increase button size
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setForeground(Color.DARK_GRAY);
        refreshButton.addActionListener(this);

        // add buttons to the buttonPanel with padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10)); // add padding between buttons
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(changeDirectoryButton);
        buttonPanel.add(refreshButton);

        // set font styles and sizes for labels
        currentDirectoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        currentDirectoryName.setFont(new Font("Arial", Font.PLAIN, 14));

        // add components to the currentDirectoryPanel
        this.currentDirectoryPanel.add(currentDirectoryLabel);
        this.currentDirectoryPanel.add(currentDirectoryName);
        this.currentDirectoryPanel.add(this.createField);
        this.currentDirectoryPanel.add(buttonPanel);

        // set backgrounds for panels
        this.currentDirectoryPanel.setBackground(Color.LIGHT_GRAY);
        this.contentsPanel.setBackground(Color.WHITE);

// add padding to the contentsPanel
        this.contentsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

// add panels to the main frame
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

