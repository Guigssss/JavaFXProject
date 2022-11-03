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
    private ListView<?> LV_Management;

    @FXML
    private ListView<?> LV_Store;

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
        //CBox_ProductTypeManagement.setItems(productTypes);
    }
}