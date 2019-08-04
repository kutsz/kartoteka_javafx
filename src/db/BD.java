package db;

import java.sql.*;

public class BD {
    private final String url = "jdbc:mysql://localhost:3306/BD";
    private final String username = "root";
    private final String password = "123";
    Connection conn;
    String query;


//    BD(String url, String username, String password) {
//        this.url = url;
//        this.username = username;
//        this.password = password;
//    }
        //Connection conn;
        BD() {
        try {

            //Driver driver = new FabricMySQLDriver();
            //DriverManager.registerDriver(driver);
            Class.forName("com.mysql.jdbc.Driver");

            //Class.forName("com.mysql.jdbc.Driver").newInstance();


            //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);

            System.out.println("Connection to DB succesfull!");
            this.conn = conn;

        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }


    }

    public void update2(String query){
        try {
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(query);
            System.out.printf("Updated %d rows", rows);

        } catch (Exception ex ) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void update1(String text){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Person SET name = ?,surname = 'nazwisko',birthDate = 'Rok_urodzenia' WHERE id = 1");
            ps.setString(1,text);
             ps.executeUpdate();
            //System.out.printf("Updated %d rows", rows);

        } catch (Exception ex ) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void update(String name,String surname,String birth,int id){
        try {
            String query = "UPDATE Person SET name = ?,surname = ?,birthDate = ? WHERE id = ?";
            //PreparedStatement ps = conn.prepareStatement("UPDATE Person SET name = ?,surname = ?,birthDate = ? WHERE id = ?");
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setString(3,birth);
            ps.setInt(4,id);
            ps.executeUpdate();
            //System.out.printf("Updated %d rows", rows);

        } catch (Exception ex ) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void insert(String name,String surname,String birth){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Person(name,surname,birthDate) VALUES (?,?,?)");
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setString(3,birth);
            ps.executeUpdate();
            //ps.executeQuery();
            //System.out.printf("Updated %d rows", rows);

        } catch (Exception ex ) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void delete(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Person WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
            //ps.executeQuery();
            //System.out.printf("Updated %d rows", rows);

        } catch (Exception ex ) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void show(int limit){
        try {
            query = "SELECT * FROM Person LIMIT "+limit;
            //Class.forName("com.mysql.jdbc.Driver");

            //Connection conn = DriverManager.getConnection(url, username, password);
            //Statement  stmt = conn.createStatement();
            Statement stmt = conn.createStatement();
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

    }

    public void find(String surName){
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Person WHERE surname = ?");
            ps.setString(1,surName);
            ResultSet rs = ps.executeQuery();

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

    }

}
