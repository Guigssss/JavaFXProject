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
            String sql = "select * from clothestable";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Clothes c = new Clothes(myRs.getString("name"), myRs.getDouble("price"), myRs.getInt("nbItems"), myRs.getInt("size"));
                productAll.add(c);
            }
            sql = "select * from shoestable";
            myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Shoes s = new Shoes(myRs.getString("name"), myRs.getDouble("price"), myRs.getInt("nbItems"), myRs.getInt("shoeSize"));
                productAll.add(s);
            }
            sql = "select * from accessoriestable";
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
    public void addShoes(Shoes shoe){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO shoestable (name,price,nbItems,shoeSize) VALUES (?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, shoe.getName());
            myStmt.setDouble(2, shoe.getPrice());
            myStmt.setInt(3, shoe.getNbItems());
            myStmt.setInt(4, shoe.getShoeSize());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void addAccessories(Accessories accessory){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO accessoriestable (name,price,nbItems) VALUES (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, accessory.getName());
            myStmt.setDouble(2, accessory.getPrice());
            myStmt.setInt(3, accessory.getNbItems());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void delete(String type, String name ){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "DELETE FROM clothestable WHERE name = ?";
            if(type=="Clothes");
            else if (type=="Shoes") {
                sql="DELETE FROM shoestable WHERE name = ?";
            }
            else if (type=="Accessories") {
                sql="DELETE FROM accessoriestable WHERE name = ?";
            }
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,name);
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void update(String type, String name,int change ,boolean ajout ){
        Connection myConn= this.Connector();
        try {
            String sql = "select name,nbItems from clothestable where name = ?";
            if(type=="Shoes"){
                sql="select name,nbItems from shoestable where name = ?";
            } else if (type=="Accessories") {
                sql="select name,nbItems from accessoriestable where name = ?";
            }
            PreparedStatement myStmt= myConn.prepareStatement(sql);
            myStmt.setString(1,name);
            ResultSet myRs= myStmt.executeQuery();
            while(myRs.next()) {
                if (ajout) {
                    change += myRs.getInt("nbItems");
                } else {
                    if ((myRs.getInt("nbItems") - change) < 0) {
                        System.out.println("Manque de stock");
                        change = (myRs.getInt("nbItems"));
                    } else {
                        change = myRs.getInt("nbItems") - change;
                    }
                }
            String sql2="update clothestable set nbItems = ? where name = ?";
            if(type=="shoes"){
                sql2="update shoestable set nbItems = ? where name = ?";
            } else if (type=="accessories") {
                sql2="update accessoriestable set nbItems = ? where name = ?";
            }
            myStmt=myConn.prepareStatement(sql2);
            myStmt.setInt(1,change);
            myStmt.setString(2,name);
            myStmt.execute();
            }
            this.close(myConn, myStmt, myRs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}