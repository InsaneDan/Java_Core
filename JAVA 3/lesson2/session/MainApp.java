package lesson2.session;

import java.sql.*;

public class MainApp {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) {

        try {
            connect();
            prepareAllStatements();
            //clearTable();
            //rollback();
            fillTableBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void rollback() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1' , 80);");

        Savepoint sp1 = connection.setSavepoint();
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2' , 80);");

        Savepoint sp2 = connection.setSavepoint();
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob5' , 80);");
        connection.rollback(sp1);

        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3' , 80);");

        connection.setAutoCommit(true);
    }

    private static void fillTableBatch() throws SQLException {
        long begin = System.currentTimeMillis();

        connection.setAutoCommit(false);

        for (int i = 1; i <= 1000; i++) {
            psInsert.setString(1, "Bob" + i);
            psInsert.setInt(2, i * 15 % 100);
            psInsert.addBatch();
        }
        psInsert.executeBatch();

//        connection.commit();
        connection.setAutoCommit(true);

        long end = System.currentTimeMillis();
        System.out.printf("Time: %d ms ", end - begin);
    }

    private static void fillTable() throws SQLException {
        long begin = System.currentTimeMillis();

        connection.setAutoCommit(false);

        for (int i = 1; i <= 1000; i++) {
            psInsert.setString(1, "1_" + i);
            psInsert.setString(2, "2_" + i);
            psInsert.setString(4, "3_" + i);
            psInsert.setString(4, "4_" + i);
            psInsert.executeUpdate();
        }

//        connection.commit();
        connection.setAutoCommit(true);

        long end = System.currentTimeMillis();
        System.out.printf("Time: %d ms ", end - begin);
    }

    private static void prepareAllStatements() throws SQLException {
        System.out.println("connection.prepareStatement");
        psInsert = connection.prepareStatement("INSERT INTO auth (login, psw, nickname, pswHash) VALUES (?, ?, ?, ?);");
    }

    //CRUD create read update delete
    private static void exSelect() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT name, score FROM students WHERE score >70;");
        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("score"));
        }
        rs.close();
    }

    private static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM students;");
    }

    private static void exDelete() throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE score = 40;");
    }

    private static void exUpdate() throws SQLException {
        stmt.executeUpdate("UPDATE students SET score = 75 WHERE score = 10;");
    }

    private static void exInsert() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3' , 80);");
        stmt.executeUpdate("INSERT INTO students (name, score) " +
                "VALUES ('Bob4' , 20), ('Bob5' , 40),('Bob6' , 50);");
    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
