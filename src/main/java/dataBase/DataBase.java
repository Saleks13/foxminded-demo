package dataBase;

import utils.PropertiesFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase {


    //method which opens connection to DB
    public Connection openConnection() {

        Connection connection = null;

        String jdbcUrl = "jdbc:postgresql://"
                + PropertiesFile.getDatabaseHost()
                + ":"
                + PropertiesFile.getDatabasePort()
                + "/"
                + PropertiesFile.getDatabaseName();

        try {
            connection = DriverManager.getConnection(jdbcUrl,
                    PropertiesFile.getDatabaseLogin(), PropertiesFile.getDatabasePassword());
            //System.out.println("Connected to PostgreSQL server");

        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server");
            e.printStackTrace();

        }

        return connection;
    }

    //method intended to return one value from a passed query
    public String getValue(String query) throws SQLException {

        String resultString = null;
        Connection connection = null;

        try {

            //open connection here
            connection = openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                resultString = rs.getString(1);
            }

        } catch (Exception e) {


            //here close connection
        } finally {
            if (!connection.isClosed()) {
                connection.close();
                System.out.println();

            }
        }
        return resultString;

    }

    //method which returns list of values for a given query
    public List<String> getList(String query) throws SQLException {
        List<String> resultList = new ArrayList<>();
        Connection connection = null;

        try {

            //open connection here
            connection = openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;

            while (rs.next()) {
                //System.out.println(rs.getString(1));
                resultList.add(rs.getString(i));
                i++;
            }

        } catch (Exception ignored) {

            //here close connection
        } finally {
            if (!connection.isClosed()) {
                connection.close();
                System.out.println();

            }
        }
        return resultList;
    }

    //method which returns map of values for a given query
    public HashMap<String, String> getHashMap(String query) throws SQLException {

        HashMap<String, String> resultsHashMap = new HashMap<>();
        Connection connection = null;

        try {

            //open connection here
            connection = openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();


            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    resultsHashMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
            }

        } catch (Exception ignored) {

            //here close connection
        } finally {
            if (!connection.isClosed()) {
                connection.close();
                System.out.println();

            }
        }
        return resultsHashMap;
    }

    //method that receives tablename, list of conditions and inside there is a method to generate query and return resultset
    public ResultSet selectResultSet(String tableName, HashMap<String, String> conditions) throws SQLException {

        ResultSet rs = null;

        Connection connection = null;
        try {
            //open connection here
            connection = openConnection();
            rs = Helper.selectQuery(connection, tableName, conditions);

            return rs;

        } catch (Exception e) {

            //here close connection
        } finally {
            if (!connection.isClosed()) {
                connection.close();
                System.out.println();

            }
        }
        return rs;
    }


    //method intended to return one value of specified column name
    public String getValue(ResultSet rs, String parameter) throws SQLException {

        String resultString = null;


        while (rs.next()) {
            System.out.println("VALUES IN DB:" + rs.getString(1));
            resultString = rs.getString(parameter);
            System.out.println("HERE COMES THE VALUE: "+ resultString);
        }
        return resultString;
    }



    //method that converts resultSet to hashmap
    public List<HashMap<String, String>> convertToListHashMap(ResultSet rs) throws SQLException {

        List<HashMap<String, String>> result = new ArrayList();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {

            HashMap<String, String> resultsHashMap = new HashMap<>();

            for (int i = 1; i <= columnsNumber; i++) {
                resultsHashMap.put(rsmd.getColumnName(i), rs.getString(i));
            }
            result.add(resultsHashMap);
        }
        return result;

    }
}




