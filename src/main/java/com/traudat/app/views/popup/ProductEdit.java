package com.traudat.app.views.popup;

import com.traudat.app.ElicApplication;
import com.traudat.app.entity.Category;
import com.traudat.app.entity.Product;
import com.traudat.app.model.ElicException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProductEdit {

    @FXML
    private ComboBox<Category> category;
    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextArea remark;

    private Product product;

    private Consumer<Product> consumer;
    private Consumer<Product> saveHandler;

    public static void addNew(Consumer<Product> saveHandler, Supplier<List<Category>> categoryListSupplier) {
        edit(null, saveHandler, categoryListSupplier);
    }

    public static void edit(Product product, Consumer<Product> saveHandler, Supplier<List<Category>> categoryListSupplier) {
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(ElicApplication.class.getClassLoader().getResource("ProductEdit.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            ProductEdit controller = loader.getController();
            controller.init(product, saveHandler, categoryListSupplier);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save() {
        try{
            product.setCategory(category.getValue());
            product.setName(name.getText());
            product.setPrice(Integer.parseInt(price.getText()));
            product.setRemark(remark.getText());

            saveHandler.accept(product);
            close();
        } catch(ElicException e) {
            message.setText(e.getMessage());
        } catch (NumberFormatException e) {
            message.setText("Please enter Price with digit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close() {
        remark.getScene().getWindow().hide();
    }

    private void init(Product product, Consumer<Product> saveHandler, Supplier<List<Category>> categoryListSupplier) {

        this.product = product;
        this.saveHandler = saveHandler;
        category.getItems().addAll(categoryListSupplier.get());


        if (null == product) {
            title.setText("Add new product");
            this.product = new Product();
        } else {
            title.setText("Edit product");
            this.product = product;
        }

        category.setValue(this.product.getCategory());
        name.setText(this.product.getName());
        price.setText(String.valueOf(this.product.getPrice()));
        remark.setText(this.product.getRemark());
    }

}
