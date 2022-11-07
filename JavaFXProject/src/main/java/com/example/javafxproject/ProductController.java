package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.List;

public class ProductController implements Initializable {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    static double initialPrice;
    static List<Product> listProducts = new ArrayList<>();
    static List<Integer> cartQuantity = new ArrayList<>();
    static List<Boolean> isDiscounted = new ArrayList<>();
    static double totalOrder;
    static int nbOrder;
    static double totalCost;
    DBManager manager;
    @FXML
    private Button B_Add;

    @FXML
    private Button B_AddToCart;

    @FXML
    private Button B_Clear;

    @FXML
    private Button B_Delete;

    @FXML
    private Button B_Modify;

    @FXML
    private Button B_PayStore;

    @FXML
    private CheckBox CB_Discount;

    @FXML
    private ComboBox<String> CBox_ProductTypeManagement;

    @FXML
    private ComboBox<String> CBox_ProductTypeStore;

    @FXML
    private ListView<Product> LV_Management;

    @FXML
    private ListView<Product> LV_Store;

    @FXML
    private Label L_TotalStore;

    @FXML
    private TextField TF_Cost;

    @FXML
    private TextField TF_Income;

    @FXML
    private TextField TF_Order;

    @FXML
    private TextField TF_PriceManagement;

    @FXML
    private TextField TF_PriceStore;

    @FXML
    private TextField TF_ProductNameManagement;

    @FXML
    private TextField TF_ProductNameStore;

    @FXML
    private TextField TF_QuantityManagement;

    @FXML
    private TextField TF_QuantityStore;

    @FXML
    private TextField TF_SizeManagement;

    @FXML
    private TextField TF_SizeStore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> productTypesValues = new ArrayList<String>();
        productTypesValues.add("Clothes");
        productTypesValues.add("Shoes");
        productTypesValues.add("Accessories");
        ObservableList<String> productTypes = FXCollections.observableArrayList(productTypesValues);
        CBox_ProductTypeStore.setItems(productTypes);
        CBox_ProductTypeManagement.setItems(productTypes);
        CBox_ProductTypeStore.setDisable(true);
        TF_ProductNameStore.setDisable(true);
        TF_PriceStore.setDisable(true);
        TF_SizeStore.setDisable(true);

        LV_Store.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetailsStore((Product) LV_Store.getSelectionModel().getSelectedItem()));
        LV_Management.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetailsManagement((Product) LV_Management.getSelectionModel().getSelectedItem()));
        manager = new DBManager();
        fetchProducts();
    }
    private void displayProductDetailsStore(Product selectedProduct) {
        if(selectedProduct!=null){
            TF_ProductNameStore.setText(selectedProduct.getName());
            TF_QuantityStore.setText(Integer.toString(selectedProduct.getNbItems()));
            TF_PriceStore.setText(Double.toString(selectedProduct.getPrice()));
            if(selectedProduct instanceof Clothes){
                TF_SizeStore.setText(Integer.toString(((Clothes) selectedProduct).getSize()));
                CBox_ProductTypeStore.getSelectionModel().select(0);
            }
            else if(selectedProduct instanceof Shoes){
                TF_SizeStore.setText(Integer.toString(((Shoes) selectedProduct).getShoeSize()));
                CBox_ProductTypeStore.getSelectionModel().select(1);
            }
            else{
                TF_SizeStore.setText("Unique Size");
                CBox_ProductTypeStore.getSelectionModel().select(2);
            }
            if(CB_Discount.isSelected()) applyDiscountCheck();
        }
    }
    private void displayProductDetailsManagement(Product selectedProduct) {
        if(selectedProduct!=null){
            TF_ProductNameManagement.setText(selectedProduct.getName());
            TF_QuantityManagement.setText(Integer.toString(selectedProduct.getNbItems()));
            TF_PriceManagement.setText(Double.toString(selectedProduct.getPrice()));
            if(selectedProduct instanceof Clothes){
                TF_SizeManagement.setText(Integer.toString(((Clothes) selectedProduct).getSize()));
                CBox_ProductTypeManagement.getSelectionModel().select(0);
            }
            else if(selectedProduct instanceof Shoes){
                TF_SizeManagement.setText(Integer.toString(((Shoes) selectedProduct).getShoeSize()));
                CBox_ProductTypeManagement.getSelectionModel().select(1);
            }
            else{
                TF_SizeManagement.setText("Unique Size");
                CBox_ProductTypeManagement.getSelectionModel().select(2);
            }
        }
    }
    public void fetchProducts(){
        List<Product> listProducts = manager.loadProducts();
        if(listProducts!=null){
            ObservableList<Product> products;
            products = FXCollections.observableArrayList(listProducts);
            LV_Store.setItems(products);
            LV_Management.setItems(products);
        }
    }
    public void clearSelection(){
        LV_Management.getSelectionModel().clearSelection();
        TF_ProductNameManagement.setText(null);
        TF_QuantityManagement.setText(null);
        TF_PriceManagement.setText(null);
        TF_SizeManagement.setText(null);
        CBox_ProductTypeManagement.getSelectionModel().clearSelection();
    }
    public void addProduct() throws IllegalArgumentException{
        if(TF_ProductNameManagement != null && TF_PriceManagement!=null && TF_SizeManagement != null && TF_SizeManagement != null){
            if(Objects.equals(this.CBox_ProductTypeManagement.getSelectionModel().getSelectedItem(), "Clothes")){
                Clothes c = new Clothes(TF_ProductNameManagement.getText(),Double.parseDouble(TF_PriceManagement.getText()),Integer.parseInt(TF_QuantityManagement.getText()), Integer.parseInt(TF_SizeManagement.getText()));
                manager.addProduct(c);
            }
            else if(Objects.equals(this.CBox_ProductTypeManagement.getSelectionModel().getSelectedItem(), "Shoes")){
                Shoes s = new Shoes(TF_ProductNameManagement.getText(),Double.parseDouble(TF_PriceManagement.getText()),Integer.parseInt(TF_QuantityManagement.getText()), Integer.parseInt(TF_SizeManagement.getText()));
                manager.addProduct(s);
            }
            else if(Objects.equals(this.CBox_ProductTypeManagement.getSelectionModel().getSelectedItem(), "Accessories")){
                Accessories a = new Accessories(TF_ProductNameManagement.getText(),Double.parseDouble(TF_PriceManagement.getText()),Integer.parseInt(TF_QuantityManagement.getText()));
                manager.addProduct(a);
            }
            else{
                throw new IllegalArgumentException("There is no product type specified, can't add the product");
            }
            totalCost+=(Double.parseDouble(TF_PriceManagement.getText())*0.5)*Double.parseDouble(TF_QuantityManagement.getText());
            TF_Cost.setText(String.valueOf(df.format(totalCost)));
        }
        else{
            throw new IllegalArgumentException("There is one or more row empty");
        }
        fetchProducts();
    }
    public void modifyProduct() throws  IllegalArgumentException{
        if(TF_ProductNameManagement!=null){
            Product p = (Product)LV_Management.getSelectionModel().getSelectedItem();
            p.setPrice(Double.parseDouble(TF_PriceManagement.getText()));
            p.setNbItems(Integer.parseInt(TF_QuantityManagement.getText()));
            if(p instanceof Clothes){
                ((Clothes)p).setSize(Integer.parseInt(TF_SizeManagement.getText()));
            }
            if(p instanceof Shoes){
                ((Shoes)p).setShoeSize(Integer.parseInt(TF_SizeManagement.getText()));
            }
            manager.updateProduct(p);
        }
        else{
            throw new IllegalArgumentException("There is no product specify, can't modify it");
        }
        clearSelection();
        fetchProducts();
    }
    public void deleteProduct() throws  IllegalArgumentException{
        if(TF_ProductNameManagement != null){
            manager.deleteProduct((Product)LV_Management.getSelectionModel().getSelectedItem());
        }
        else{
            throw  new IllegalArgumentException("There is no product specify, can't delete it");
        }
        clearSelection();
        fetchProducts();
    }
    public void addProductToCart(){
        Product p = (Product)LV_Store.getSelectionModel().getSelectedItem();
        cartQuantity.add(Integer.parseInt(TF_QuantityStore.getText()));
        listProducts.add(p);
        if(CB_Discount.isSelected()){
            totalOrder += Integer.parseInt(TF_QuantityStore.getText())*p.getDiscountPrice();
            isDiscounted.add(true);
        }
        else{
            totalOrder += Integer.parseInt(TF_QuantityStore.getText())*p.getPrice();
            isDiscounted.add(false);
        }
        L_TotalStore.setText("Total : "+df.format(totalOrder)+" €");
    }
    public void payOrder(){
        if(!listProducts.isEmpty()){
            int indexSelectedItem = LV_Store.getSelectionModel().getSelectedIndex();
            totalOrder = 0;
            L_TotalStore.setText("Total : "+df.format(totalOrder)+" €");
            int lengthListProducts = listProducts.size();
            for(int i = 0; i<lengthListProducts;i++){
                listProducts.get(0).sell(cartQuantity.get(0), isDiscounted.get(0));
                manager.updateProduct(listProducts.get(0));
                if(listProducts.get(0).getNbItems()==0){
                    listProducts.get(0).purchase(10);
                    totalCost += 10*(listProducts.get(0).getPrice()*0.5);
                    TF_Cost.setText(String.valueOf(df.format(totalCost)));
                    manager.updateProduct(listProducts.get(0));
                }
                cartQuantity.remove(0);
                isDiscounted.remove(0);
                listProducts.remove(0);
            }
            TF_Income.setText(String.valueOf(df.format(Product.getIncome())));
            fetchProducts();
            LV_Store.getSelectionModel().select(indexSelectedItem);
            displayProductDetailsStore(LV_Store.getSelectionModel().getSelectedItem());
            nbOrder++;
            TF_Order.setText(String.valueOf(nbOrder));
        }
    }
    /*public void applyDiscountCheck(){
        Product p = (Product)LV_Store.getSelectionModel().getSelectedItem();
        int indexSelectedItem = LV_Store.getSelectionModel().getSelectedIndex();
        if(!LV_Store.getSelectionModel().isEmpty()){
            if(CB_Discount.isSelected()){
                initialPrice=p.getPrice();
                p.applyDiscount();
                manager.updateProduct(p);
            }
            else{
                p.setPrice(initialPrice);
                manager.updateProduct(p);
            }
        }
        fetchProducts();
        LV_Store.getSelectionModel().select(indexSelectedItem);
        displayProductDetailsStore(p);
    }*/
    public void applyDiscountCheck(){
        Product p = (Product)LV_Store.getSelectionModel().getSelectedItem();
        if(!LV_Store.getSelectionModel().isEmpty()){
            if(CB_Discount.isSelected()){
                p.applyDiscount();
                TF_PriceStore.setText(String.valueOf(p.getDiscountPrice()));
            }
            else{
                TF_PriceStore.setText(String.valueOf(p.getPrice()));
            }
        }
    }
}