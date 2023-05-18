import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SwingUI_GUI extends JFrame {
    private JPanel MainPanel;
    private JScrollPane SideBarScroll;
    private JButton ShowPanelSearchButton;
    private JButton ShowPanelDeleteButton;
    private JButton ShowPanelUpdateButton;
    private JButton ShowPanelCreateButton;
    private JList<String> MainListArea; // Updated to JList
    private JPanel ListArea;
    private JButton RefreshBTN;
    private JTextField CreateTextField;
    private JPanel CreatePanel;
    private JButton SendButton_Create;
    private JPanel DeletePanel;
    private JButton SendButton_Delete;
    private JTextField DeleteTextField;
    private JLabel DeleteLabel;
    private JButton SendButton_Update;
    private JTextField UpdateTextField;
    private JPanel UpdatePanel;
    private JLabel updatelabel;
    private JTextField InnerUpdateTextField;
    private JLabel searchLabel;
    private JTextField SearchTextField;
    private JButton SendButton_Search;
    private JPanel SearchPanel;
    private JPanel FileInformationWindow;
    private JTextArea FileInformationWindowTextField;
    private JButton AddFavoriteButton;
    private JList FavoriteList;
    private JButton ShowPanelCloseButton;
    private JPanel ActualListOfFilesPanel;
    private JTextArea FileInfoTextArea;
    private JLabel LabelCreate;
    private JTextField DataField;
    private JTextField SizeField;
    private JTextField TypeField;
    private JTextField DName;
    private JLabel lad;
    private DefaultListModel<String> FavoriteListModel;
    private FileSystem fileSystem;

    public SwingUI_GUI(String title) {
        // Initializing instance variables
        super(title);
        this.fileSystem = new FileSystem();

    // Model setup
        FavoriteListModel = new DefaultListModel<>();
        FavoriteList.setModel(FavoriteListModel);

    // Button setup
        ShowPanelSearchButton.setPreferredSize(new Dimension(200, 75));
        ShowPanelDeleteButton.setPreferredSize(new Dimension(200, 75));
        ShowPanelUpdateButton.setPreferredSize(new Dimension(200, 75));
        ShowPanelCreateButton.setPreferredSize(new Dimension(200, 75));
        RefreshBTN.setPreferredSize(new Dimension(200, 75));
        RefreshBTN.addActionListener(e -> refreshListArea());

    // MainListArea setup
        MainListArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode
        MainListArea.setLayoutOrientation(JList.VERTICAL); // Set layout orientation

    // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

    // Initial panel visibility setup
        CreatePanel.setVisible(false);
        UpdatePanel.setVisible(false);
        SearchPanel.setVisible(false);
        FileInformationWindow.setVisible(false);
        DeletePanel.setVisible(false);



        // TOP OF MAIN PANEL BUTTONS
        ShowPanelCloseButton.addActionListener(e -> {
            togglePanelVisibility(FileInformationWindow);
            togglePanelVisibility(ActualListOfFilesPanel);
        });

        ShowPanelUpdateButton.addActionListener(e -> togglePanelVisibility(UpdatePanel));

        ShowPanelSearchButton.addActionListener(e -> togglePanelVisibility(SearchPanel));

        ShowPanelCreateButton.addActionListener(e -> togglePanelVisibility(CreatePanel));

        ShowPanelDeleteButton.addActionListener(e -> togglePanelVisibility(DeletePanel));


        // INNER JPANEL BUTTONS THAT DO CRUD
        SendButton_Create.addActionListener(e -> {
            String directoryName = DName.getText();
            String fileNameCreate = CreateTextField.getText();
            Object dataObject = DataField.getText(); // Assuming your data is text
            Integer sizeValue = Integer.valueOf(SizeField.getText());
            String typeValue = TypeField.getText();

            if (!fileNameCreate.isEmpty()) {
                if (!fileSystem.isFileExists(fileNameCreate)) {
                    fileSystem.createFile(directoryName, fileNameCreate, dataObject, sizeValue, typeValue);
                    refreshListArea();
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File " + fileNameCreate +" has been created");
                    DName.setText("");
                    CreateTextField.setText("");
                    DataField.setText(""); // Assuming your data is text
                    SizeField.setText("");
                    TypeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File already exists!");
                }
            }
        });

        SendButton_Delete.addActionListener(e -> {
            String fileNameDelete = DeleteTextField.getText();
            if (!fileNameDelete.isEmpty()) {
                if (fileSystem.isFileExists(fileNameDelete)) {
                    fileSystem.deleteFile(fileNameDelete);
                    refreshListArea();
                    DeleteTextField.setText("");
                    if (FavoriteListModel.contains(fileNameDelete)) {
                        FavoriteListModel.removeElement(fileNameDelete);
                    }
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File " + fileNameDelete +" has been deleted");
                } else {
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File does not exist!");
                }
            }
        });


        SendButton_Update.addActionListener(e -> {
            String fileNameToUpdate = UpdateTextField.getText();
            String newValueOfFile = InnerUpdateTextField.getText();

            if (!fileNameToUpdate.isEmpty() && !newValueOfFile.isEmpty()) {
                if (fileSystem.isFileExists(fileNameToUpdate)) {
                    fileSystem.updateFile(fileNameToUpdate, newValueOfFile);
                    refreshListArea();
                    UpdateTextField.setText(""); // Clear the UpdateTextField
                    InnerUpdateTextField.setText(""); // Clear the InnerUpdateTextField
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File (" + fileNameToUpdate + ") has been updated");
                    // If the updated file was in the favorites, update it there too
                    if (FavoriteListModel.contains(fileNameToUpdate)) {
                        FavoriteListModel.removeElement(fileNameToUpdate);
                        FavoriteListModel.addElement(newValueOfFile);
                    }
                } else {
                    JOptionPane.showMessageDialog(SwingUI_GUI.this, "File does not exist!");
                }
            }
        });

        SendButton_Search.addActionListener(e -> {
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
        });


        AddFavoriteButton.addActionListener(e -> {
            String fileToBeAddedToFavoriteList = FileInformationWindowTextField.getText();
            if (FavoriteListModel.contains(fileToBeAddedToFavoriteList)) {
                JOptionPane.showMessageDialog(SwingUI_GUI.this, "File is already in the favorite list!");
            } else {
                FavoriteListModel.addElement(fileToBeAddedToFavoriteList);
            }
        });


        // LISTS
        MainListArea.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFileName = MainListArea.getSelectedValue();
                if (selectedFileName != null) {
                    // Retrieve the file information based on the selected file name
                    // Update the FileInformationWindow components
                    ActualListOfFilesPanel.setVisible(false);
                    FileInformationWindow.setVisible(true);
                    FileInformationWindowTextField.setText(selectedFileName);

                    updateFileInfo(selectedFileName);
                }
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

        SystemData.initializeFileSystem(fileSystem);
        frame.refreshListArea();
    }

    // Methods used throughout the GUI enabling CRUD actions and view changes
    private void updateFileInfo(String fileName) {
        if (fileSystem.isFileExists(fileName)) {
            File selectedFile = fileSystem.getFile(fileName);
            String NameDirectory = selectedFile.getDirectoryName();
            String fileType = selectedFile.getType();
            int fileSize = selectedFile.getSize();
            Object fileData = selectedFile.getData();

            // Format the file information string
            String fileInformation =
                    "Directory Name: "+ NameDirectory +
                            "\nType: " + fileType +
                            "\nSize: " + fileSize +
                            "\nData: " + fileData;

            // Update the FileInfoTextArea with the file information
            FileInfoTextArea.setText(fileInformation);
        } else {
            FileInfoTextArea.setText("File does not exist");
        }
    }

    // This method refreshes the file list
    private void refreshListArea() {
        updateFileList();
    }

    // This method updates the file list in the main area
    private void updateFileList() {
        DefaultListModel<String> model = new DefaultListModel<>(); // Create a DefaultListModel
        List<File> files = fileSystem.getCurrentDirectory().getFiles();
        // Keep track of current file names
        Set<String> currentFileNames = new HashSet<>();
        for (File file : files) {
            String fileName = file.getName();
            currentFileNames.add(fileName);
            // Check if the file is already present in the model
            if (!model.contains(fileName)) {
                model.addElement(fileName); // Add file name to the model
            }
        }

        MainListArea.setModel(model); // Set the model to the JList
    }

    // This method sets the file system
    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    // This method toggles the visibility of a given panel
    private void togglePanelVisibility(JPanel panel) {
        boolean isPanelVisible = panel.isVisible(); // Get the current visibility state
        panel.setVisible(!isPanelVisible); // Toggle the visibility of the panel
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

