package ru.job4j.jdcb;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static Connection getConnection() throws Exception {
        Properties config = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("dbConnection.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver"));
        return DriverManager.getConnection(config.getProperty("url"),
                config.getProperty("username"), config.getProperty("password"));
    }


    private void initConnection() {
        connection = null;
    }

    public static void createTable(String tableName) throws Exception {
        try  (Connection connection = getConnection()) {
           try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                       "CREATE TABLE IF NOT EXISTS " + tableName + " (%s, %s);",
                       "id serial PRIMARY KEY",
                       "name varchar(255)"
                );
                statement.execute(sql);
               System.out.println(getTableScheme(connection, tableName));
           }
        }
    }

    public static void dropTable(String tableName) throws Exception {
        try  (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "DROP TABLE " + tableName
                );
                statement.execute(sql);
                System.out.println(tableName + " is delete");
            }
        }
    }

    public static void addColumn(String tableName, String columnName, String type) throws Exception {
        try  (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, tableName));
            }
        }
    }

    public static void dropColumn(String tableName, String columnName) throws Exception {
        try  (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE " + tableName + " DROP COLUMN " + columnName
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, tableName));
            }
        }
    }

    public static void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try  (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, tableName));
            }
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        createTable("demo");
        addColumn("demo", "count", "int");
        renameColumn("demo", "count", "max");
        dropColumn("demo", "max");
        dropTable("demo");
    }




}