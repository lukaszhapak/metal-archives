package source.admin;

import source.database.ConnectionHelper;
import source.database.Properties;
import source.file.FileOperationHelper;


class AdminRepository {

    void eraseData() {
        for (String table : Properties.tables) {
            String sql = "delete from " + table;
            ConnectionHelper.executeUpdate(sql);
            sql = "ALTER TABLE " + table + " AUTO_INCREMENT = 1";
            ConnectionHelper.executeUpdate(sql);
        }
    }

    void insertDummyData() {
        FileOperationHelper fileOperationHelper = new FileOperationHelper();
        String[] queries = fileOperationHelper.getDataFromFile("./src/main/resources/admin/insertData.sql").split("insert");

        for (String sql : queries) {
            if (sql.length() > 5) {
                ConnectionHelper.executeUpdate("insert" + sql);
            }
        }
    }
}
