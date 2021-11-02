package dataBase;

import java.sql.*;
import java.util.*;

public class Helper {


    //method that gets connection to DB, table name and set of condition in hashmap, generates query and returns resultSet
    public static ResultSet selectQuery(Connection connection, String tableName, HashMap<String, String> conditions) throws SQLException {

        ResultSet resultSet = null;
        Statement statement = connection.createStatement();

        int size = conditions.size();
        String query = "select * from " + tableName;

        if (size >= 1){
            int i = 0;
            query += " where ";
            for(Map.Entry<String, String> pair : conditions.entrySet()) {
                query += String.format("%s = '%s'", pair.getKey(), pair.getValue());
                i++;
                if (i < size){
                    query += " and ";
                }
            }
        }
        System.out.println("----------QUERY IS :"+ query);
        resultSet = statement.executeQuery(query);


        return resultSet;
    }

}
