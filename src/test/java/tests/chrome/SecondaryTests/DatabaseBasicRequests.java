package tests.chrome.SecondaryTests;

import dataBase.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class DatabaseBasicRequests {

    String query21 = "select first_name, last_name \n" +
            "from manager \n" +
            "left join department\n" +
            "on manager.department_id = department.id\n" +
            "where name = 'Комната добра'";

    String query22 = "select first_name, last_name\n" +
            "from manager where id in (select person_id from email where email_address like '%@gmail.com')\n" +
            "and phone is not null";

    String query23 = "select ticket.id from ticket\n" +
            "left join category\n" +
            "on ticket.category_id = category.id\n" +
            "where creation_timestamp BETWEEN '2018-04-19 00:00:00' and '2018-05-03 00:00:00'\n" +
            "and category.name = 'Финансы'";

    String query24 = "select title from ticket \n" +
            "left join manager \n" +
            "on ticket.assignee_id = manager.id \n" +
            "where manager.first_name = 'Татьяна' and manager.last_name = 'Алымова'\n" +
            "and ticket.priority = 'P4'";

    String query31 = "select name from department order by RANDOM() limit 1";

    String query32 = "select * from manager where first_name = 'Татьяна' and last_name = 'Алымова'";

    String query33 = "select * from manager \n" +
            "inner join department\n" +
            "on manager.department_id = department.id \n" +
            "where department.name = 'Комната добра'";

    protected final Logger logger = LogManager.getLogger(getClass());

    @Test
    public void task18() throws SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //initialize new instance of database help class
        DataBase dataBase = new DataBase();

        System.out.print("2.1.1 Select all Full name from the table on Managers page where Department = Комната добра (using left join):");
        System.out.println(dataBase.getHashMap(query21) + "\n");

        System.out.print("2.2.1 Select the Full name from the table on Managers page where phone is not empty and email contains @gmail.com " +
                "+ selecting from the same table, since it also has email field:");
        System.out.println(dataBase.getHashMap(query22) + "\n");

        System.out.print("2.3.1 Select the ID from the table on dashboard page Done Deadline tab where Created from 19.04.2018 to 03.05.2018 and Category = Финансы via left join:");
        System.out.println(dataBase.getList(query23) + "\n");

        System.out.print("2.4.1 Select the Title from table on dashboard page Done Deadline tab where Priority=P4 and Assigned = Татьяна Алимова via left join:");
        System.out.println(dataBase.getList(query24) + "\n");

        System.out.print("3.1. Search the Title on the departments page:");
        System.out.println(dataBase.getValue(query31) + "\n");

        System.out.print("3.2. Search by criteria First Name and Last Name on the managers page:");
        System.out.println(dataBase.getList(query32) + "\n");

        System.out.print("3.3.1. Search by criteria Department name on the managers page :");
        System.out.println(dataBase.getList(query33) + "\n");

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }

}
