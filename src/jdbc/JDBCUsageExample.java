package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * First, run SetupDerbyDatabase.
 */
public class JDBCUsageExample {
    public static final String SELECT_ALL = "SELECT * FROM animal";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:derby:zoo;create=true");
             PreparedStatement preparedStmt = connection.prepareStatement(SELECT_ALL);
             Statement statement            = connection.createStatement();
             ResultSet preparedStmtRs       = preparedStmt.executeQuery();
             ResultSet stmtRs               = statement.executeQuery(SELECT_ALL)) {


            List<Map<String, String>> dataFromStmt          = extractDataFromRs(stmtRs);
            List<Map<String, String>> dataFromPreparedStmt  = extractDataFromRs(preparedStmtRs);

            System.out.println(dataFromStmt);
            System.out.println(dataFromPreparedStmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> extractDataFromRs(ResultSet rs) throws SQLException {
        List<Map<String, String>> result = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cc = rsmd.getColumnCount();
        while (rs.next()) {
            Map<String, String> itMap = new HashMap<>();
            for (int i = 1; i <= cc; i++) {
                String colName = rsmd.getColumnName(i);
                String colValue = rs.getString(i);
                itMap.put(colName, colValue);
            }
            result.add(itMap);
        }

        return result;
    }
}
