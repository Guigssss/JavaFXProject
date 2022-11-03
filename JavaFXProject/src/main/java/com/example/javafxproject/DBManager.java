package com.example.javafxproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DBManager {

    public List<Product> loadProducts(){
        List<Product> productAll= new ArrayList<Product>();
        Connection myConn= this.Connector();
        try {
            Statement myStmt= myConn.createStatement();
            String sql = "select * from storetable";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Clothes c = new Clothes(myRs.getString("name"),myRs.getDouble("price"), myRs.getInt("nbItems"), myRs.getInt("size"));
                productAll.add(c);
            }
            this.close(myConn, myStmt, myRs);
            return productAll;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    public Connection Connector(){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=Europe%2FParis", "root","password");
            return connection;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myStmt != null)
                myStmt.close();
            if (myRs != null)
                myRs.close();
            if (myConn != null)
                myConn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addClothes(Clothes clothe){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO clothestable (name,price,nbItems,size) VALUES (?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, clothe.getName());
            myStmt.setDouble(2, clothe.getPrice());
            myStmt.setInt(3, clothe.getNbItems());
            myStmt.setInt(4, clothe.getSize());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
}