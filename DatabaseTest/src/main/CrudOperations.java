package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tayfer01 on 7/26/2016.
 */
public class CrudOperations {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;


    public CrudOperations() {
         connection = EstablishConnection.getConnection();
    }

    public boolean insertData()
    {

        String insertTableSQL = "INSERT INTO MANAGER"
                + "(EMPNO, NAME, SURNAME, DEPARTMENT) VALUES"
                + "(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, "1234");
            preparedStatement.setString(2, "Ferin");
            preparedStatement.setString(3, "Taylor");
            preparedStatement.setString(4, "HR");
            preparedStatement .executeUpdate();
        }
        catch(Exception ex) {

        }
        return false;
    }

    public boolean retrieveData()
    {
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT  * FROM MANAGER");
            while(rs.next())
            {
                System.out.println("Employee Number : " + rs.getNString("EMPNO")
                + "\nName : " + rs.getNString("Name"));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void updateRecord(String department)
    {
        /*Do not forget the single quotes when inserting a string to DB*/
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("UPDATE MANAGER SET DEPARTMENT = '" + department + "' WHERE EMPNO = 1234");
            retrieveData();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeRecord()
    {
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery("DELETE FROM MANAGER WHERE EMPNO = 1234");
            retrieveData();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean createTable()
    {
        boolean isValid = true;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("CREATE TABLE MANAGER" +
                    "(" +
                    "  EMPNO VARCHAR2(20) NOT NULL," +
                    "  NAME VARCHAR2(20)," +
                    "  SURNAME VARCHAR2(20)," +
                    "  DEPARTMENT VARCHAR2(20)" +
                    ", CONSTRAINT MANAGER_PK PRIMARY KEY" +
                    "  (" +
                    "    EMPNO" +
                    "  )" +
                    "  ENABLE)");
            if(rs.wasNull())
                isValid = false;

        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
