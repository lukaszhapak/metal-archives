package source.database;

import source.file.FileOperationHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class TableHelper {

    static void createTables() {
        String sql = "show tables";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);
        List<String> tablesCreated = new ArrayList<>();

        try {
            while (resultSet.next()) {
                tablesCreated.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = Properties.tables.size(); i > 0; i--) {
            if (!tablesCreated.contains(Properties.tables.get(i - 1))) {
                createTable(Properties.tables.get(i - 1));
            }
        }
    }

    private static void createTable(String tableName) {
        FileOperationHelper fileOperationHelper = new FileOperationHelper();
        String sql = fileOperationHelper.getDataFromFile(tableName + "/createTable.sql");
        ConnectionHelper.executeUpdate(sql);
    }
}
