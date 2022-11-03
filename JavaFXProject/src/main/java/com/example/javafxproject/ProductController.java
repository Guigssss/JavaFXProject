package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

public class ProductController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> productTypesValues = new ArrayList<String>();
        productTypesValues.add("Clothes");
        productTypesValues.add("Shoes");
        productTypesValues.add("Accessories");
        ObservableList<String> productTypes = FXCollections.observableArrayList(productTypesValues);
        CBox_ProductTypeStore.setItems(productTypes);
        CBox_ProductTypeManagement.setItems(productTypes);

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
        }
    }
    private void displayProductDetailsManagement(Product selectedProduct) {
        if(selectedProduct!=null){
            TF_ProductNameManagement.setText(selectedProduct.getName());
            TF_QuantityManagement.setText(Integer.toString(selectedProduct.getNbItems()));
            TF_PriceManagement.setText(Double.toString(selectedProduct.getPrice()));
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
}