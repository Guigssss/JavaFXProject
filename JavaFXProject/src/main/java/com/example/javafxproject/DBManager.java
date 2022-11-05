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
            String sql = "SELECT * FROM clothestable";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Clothes c = new Clothes(myRs.getString("name"), myRs.getDouble("price"), myRs.getInt("nbItems"), myRs.getInt("size"));
                productAll.add(c);
            }
            sql = "SELECT * FROM shoestable";
            myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Shoes s = new Shoes(myRs.getString("name"), myRs.getDouble("price"), myRs.getInt("nbItems"), myRs.getInt("shoeSize"));
                productAll.add(s);
            }
            sql = "SELECT * FROM accessoriestable";
            myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Accessories a = new Accessories(myRs.getString("name"), myRs.getDouble("price"), myRs.getInt("nbItems"));
                productAll.add(a);
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
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/product?serverTimezone=Europe%2FParis", "root","password");
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
    public void addProduct(Product p){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        String sql="";
        try {
            myConn = this.Connector();
            if(p instanceof Clothes){
                sql = "INSERT INTO clothestable (name,price,nbItems,size) VALUES (?, ?, ?, ?)";
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(4, ((Clothes)p).getSize());
            }
            else if(p instanceof Shoes){
                sql = "INSERT INTO shoestable (name,price,nbItems,shoeSize) VALUES (?, ?, ?, ?)";
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(4, ((Shoes)p).getShoeSize());
            }
            else if(p instanceof Accessories){
                sql = "INSERT INTO accessoriestable (name,price,nbItems) VALUES (?, ?, ?)";
                myStmt = myConn.prepareStatement(sql);
            }
            myStmt.setString(1, p.getName());
            myStmt.setDouble(2, p.getPrice());
            myStmt.setInt(3, p.getNbItems());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void deleteProduct(Product p){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "";
            if(p instanceof Clothes){
                sql = "DELETE FROM clothestable WHERE name = ?";
            }
            else if (p instanceof Shoes) {
                sql="DELETE FROM shoestable WHERE name = ?";
            }
            else if (p instanceof Accessories) {
                sql="DELETE FROM accessoriestable WHERE name = ?";
            }
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,p.getName());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void updateProduct(Product p){
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "";
            if(p instanceof Clothes){
                sql="UPDATE clothestable SET price = ?, nbItems = ?, size = ? WHERE name = ?";
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(3, ((Clothes)p).getSize());
                myStmt.setString(4,p.getName());
            }
            else if (p instanceof Shoes) {
                sql="UPDATE shoestable SET price = ?, nbItems = ?, shoeSize = ? WHERE name = ?";
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(3, ((Shoes)p).getShoeSize());
                myStmt.setString(4,p.getName());
            }
            else if (p instanceof Accessories) {
                sql="UPDATE accessoriestable SET price = ?, nbItems = ? WHERE name = ?";
                myStmt = myConn.prepareStatement(sql);
                myStmt.setString(3,p.getName());
            }
            myStmt.setDouble(1,p.getPrice());
            myStmt.setInt(2,p.getNbItems());
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