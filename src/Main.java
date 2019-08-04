import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
//import com.mysql.jdbc.Statement;

public class Main {
    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/BD";
//        String username = "root";
//        String password = "123";
//
//        BD db = new BD(url, username, password);
        BD db = new BD();

        String query = "UPDATE Person SET name = 'imie',surname = 'nazwisko',birthDate = 'Rok_urodzenia' WHERE id = 1";
        db.update(query);
        query = "SELECT * FROM Person";

       // Statement stmt = db.conn.createStatement();


        try {

            //Class.forName("com.mysql.jdbc.Driver");

            //Connection conn = DriverManager.getConnection(url, username, password);
            //Statement  stmt = conn.createStatement();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String birth = rs.getString("birthDate");

                System.out.println("Name: " + name + " Surname: " + surname + " Birth date: " + birth);

            }
        } catch (Exception e ) {
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            //if (stmt != null) { stmt.close(); }
        }

        db.show(1);


        AddPerson a = new AddPerson();
        a.run();

    }



}
