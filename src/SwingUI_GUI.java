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
    private JList<String> MainListArea; // Updated to JList
    private JPanel ListArea;
    private JButton RefreshBTN;
    private JTextField CreateTextField;
    private JPanel CreatePanel;
    private JButton SendBTN_Create;
    private JPanel DeletePanel;
    private JButton SendBTN_Delete;
    private JTextField DeleteTextField;
    private JLabel DeleteLabel;
    private JButton SendBTN_Update;
    private JTextField UpdateTextField;
    private JPanel UpdatePanel;
    private JLabel updatelabel;
    private JTextField InnerUpdateTextField;
    private JLabel searchLabel;
    private JTextField SearchTextField;
    private JButton SendBTN_Search;
    private JPanel SearchPanel;
    private JPanel FileInformationWindow;
    private JTextField FileInformationWindowTextField;
    private JButton FavoriteButton;
    private JList FavoriteList;
    private JButton CloseBTN;
    private JPanel ActualListOfFilesPanel;
    private DefaultListModel<String> FavoriteListModel;
    private FileSystem fileSystem;

    public SwingUI_GUI(String title) {
        super(title);
        this.fileSystem = new FileSystem();
        FavoriteListModel = new DefaultListModel<>();
        FavoriteList.setModel(FavoriteListModel);

        SearchBTN.setPreferredSize(new Dimension(200, 75));
        DeleteBTN.setPreferredSize(new Dimension(200, 75));
        UpdateBTN.setPreferredSize(new Dimension(200, 75));
        CreateBTN.setPreferredSize(new Dimension(200, 75));
        RefreshBTN.setPreferredSize(new Dimension(200, 75));

        MainListArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode
        MainListArea.setLayoutOrientation(JList.VERTICAL); // Set layout orientation

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        RefreshBTN.addActionListener(e -> refreshListArea());


        CreatePanel.setVisible(false);
        CreateBTN.addActionListener(e -> togglePanelVisibility(CreatePanel));

        SendBTN_Create.addActionListener(e -> {
            String fileNameCreate = CreateTextField.getText();
            if (!fileNameCreate.isEmpty()) {
                fileSystem.createFile(fileNameCreate);
                refreshListArea();
                CreatePanel.setVisible(false);
            }
        });
        DeletePanel.setVisible(false);
        DeleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePanelVisibility(DeletePanel);
            }
        });
        SendBTN_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileNameDelete = DeleteTextField.getText();
                if (!fileNameDelete.isEmpty()) {
                    if (fileSystem.isFileExists(fileNameDelete)) {
                        fileSystem.deleteFile(fileNameDelete);
                        refreshListArea();
                        DeleteTextField.setText("");
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, "File " + fileNameDelete +" has been deleted");
                    } else {
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, "File does not exist!");
                    }
                }
            }
        });
        UpdatePanel.setVisible(false);
        UpdateBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePanelVisibility(UpdatePanel);
            }
        });
        SendBTN_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileNameToUpdate = UpdateTextField.getText();
                String newValueOfFile = InnerUpdateTextField.getText();

                if (!fileNameToUpdate.isEmpty() && !newValueOfFile.isEmpty()) {
                    if (fileSystem.isFileExists(fileNameToUpdate)) {
                        fileSystem.updateFile(fileNameToUpdate, newValueOfFile);
                        refreshListArea();
                        UpdateTextField.setText(""); // Clear the UpdateTextField
                        InnerUpdateTextField.setText(""); // Clear the InnerUpdateTextField
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, "File (" + fileNameToUpdate + ") has been updated");
                    } else {
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, "File does not exist!");
                    }
                }
            }
        });
        SearchPanel.setVisible(false);
        SearchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            togglePanelVisibility(SearchPanel);
            }
        });
        SendBTN_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileToBeSearched = SearchTextField.getText();
                String result = fileSystem.searchFile(fileToBeSearched);
                    if (fileSystem.isFileExists(fileToBeSearched)) {
                        // Retrieve the file information based on the selected file name
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, result);
                        SearchTextField.setText(""); // Clear the SearchTextField
                        SearchPanel.setVisible(false); // Hide the SearchPanel
                        // Update the FileInformationWindow components
                        ActualListOfFilesPanel.setVisible(false);
                        FileInformationWindowTextField.setText(fileToBeSearched);
                        FileInformationWindow.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(SwingUI_GUI.this, result);
                    }
                }
        });

        FileInformationWindow.setVisible(false);
        MainListArea.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFileName = MainListArea.getSelectedValue();
                if (selectedFileName != null) {
                    // Retrieve the file information based on the selected file name


                    // Update the FileInformationWindow components
                    ActualListOfFilesPanel.setVisible(false);
                    FileInformationWindow.setVisible(true);
                    FileInformationWindowTextField.setText(selectedFileName);

                }
            }
        });


        FavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileToBeAddedToFavoriteList = FileInformationWindowTextField.getText();
                if (FavoriteListModel.contains(fileToBeAddedToFavoriteList)) {
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File is already in the favorite list!");
                } else {
                    FavoriteListModel.addElement(fileToBeAddedToFavoriteList);
                }
            }
        });
        CloseBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                togglePanelVisibility(FileInformationWindow);
                togglePanelVisibility(ActualListOfFilesPanel);
            }
        });

        FavoriteList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFileName = (String) FavoriteList.getSelectedValue();
                if (selectedFileName != null) {
                    // Retrieve the file information based on the selected file name
                    // Update the FileInformationWindow components
                    ActualListOfFilesPanel.setVisible(false);
                    FileInformationWindow.setVisible(true);
                    FileInformationWindowTextField.setText(selectedFileName);

                }
            }
        });
    }
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public static void main(String[] args) {
        SwingUI_GUI frame = new SwingUI_GUI("File Simulation System 439");
        frame.setSize(1250, 1000);
        frame.setFileSystem(new FileSystem()); // Create a new instance of FileSystem
        frame.createUIComponents(); // Create the UI components
        frame.setLocationRelativeTo(null);

        // Create initial files
        FileSystem fileSystem = frame.getFileSystem();
        frame.updateFileList(); // Populate the file list
        frame.setVisible(true); // Make the frame visible

        InitialValues.initializeFileSystem(fileSystem);
        frame.refreshListArea();
    }


    private void refreshListArea() {
      updateFileList();
    }
    private void updateFileList() {
        DefaultListModel<String> model = new DefaultListModel<>(); // Create a DefaultListModel
        List<File> files = fileSystem.getCurrentDirectory().getFiles();

        for (File file : files) {
            String fileName = file.getName();

            // Check if the file is already present in the model
            if (!model.contains(fileName)) {
                model.addElement(fileName); // Add file name to the model
            }
        }

        MainListArea.setModel(model); // Set the model to the JList
    }

    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }
    private void togglePanelVisibility(JPanel panel) {
        boolean isPanelVisible = panel.isVisible(); // Get the current visibility state
        panel.setVisible(!isPanelVisible); // Toggle the visibility of the panel
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

