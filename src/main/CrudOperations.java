package main;

import java.sql.*;

/**
 * Created by tayfer01 on 7/26/2016.
 */
public class CrudOperations {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    boolean isValid;

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

            isValid = retrieveData();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return isValid;
    }

    public boolean retrieveData()
    {

        isValid = false;
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT  * FROM MANAGER");
            while(rs.next())
            {
                System.out.println("Employee Number : " + rs.getNString("EMPNO")
                + "\nName : " + rs.getNString("Name"));
                isValid = true;
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return isValid;
    }

    public boolean updateRecord(String department)
    {
        /*Do not forget the single quotes when inserting a string to DB*/
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("UPDATE MANAGER SET DEPARTMENT = '" + department + "' WHERE EMPNO = 1234");
            isValid = rs.rowUpdated();
            retrieveData();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return isValid;
    }

    public boolean removeRecord()
    {
        String removeRecord = "DELETE FROM MANAGER WHERE EMPNO = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(removeRecord);
            preparedStatement.setString(1, "1234");
            preparedStatement .executeUpdate();
            isValid = retrieveData();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return isValid;
    }

    public boolean createTable()
    {
        isValid = false;

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

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            rs = databaseMetaData.getTables(null, null, "MANAGER", null);

            if(rs.next())
                isValid = true;

        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return isValid;
    }
}
