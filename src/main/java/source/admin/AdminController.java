package source.admin;

import javafx.scene.control.TextArea;
import source.file.FileOperationHelper;

public class AdminController {

    public TextArea dummyDataEditor;

    private FileOperationHelper fileOperationHelper;
    private AdminRepository adminRepository;

    public void initialize() {
        fileOperationHelper = new FileOperationHelper();
        adminRepository = new AdminRepository();
        dummyDataEditor.setText(fileOperationHelper.getDataFromFile("./src/main/resources/admin/insertData.sql"));
    }

    public void eraseData() {
        adminRepository.eraseData();
    }

    public void addDummyData() {
        adminRepository.insertDummyData();
    }

    public void saveChanges() {
        fileOperationHelper.saveDataInFile("./src/main/resources/admin/insertData.sql", dummyDataEditor.getText());
    }
}
