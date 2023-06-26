package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private SQLHelper() {
    }
    private static final String url = "jdbc:mysql://localhost:3306/app";
    private static final String user = "app";
    private static final String password = "pass";
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
    public static QueryRunner runner = new QueryRunner();
    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes LIMIT 1";
        try (var conn = getConn()) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase(){
        var connection = getConn();
        runner.execute(connection, "delete from card_transactions");
        runner.execute(connection,"delete from cards");
        runner.execute(connection,"delete from auth_codes");
        runner.execute(connection,"delete from users");
    }
}