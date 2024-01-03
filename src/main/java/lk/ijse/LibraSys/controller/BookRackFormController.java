package lk.ijse.LibraSys.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.LibraSys.bo.BookRackBO;
import lk.ijse.LibraSys.bo.BookRackBOImpl;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.dto.tm.BookRackTm;
import lk.ijse.LibraSys.dao.custom.Impl.BookRackDAOImpl;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookRackFormController {

    @FXML
    private AnchorPane Pane;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colNameOfBooks;

    @FXML
    private TableColumn<?, ?> colQuantityBooks;

    @FXML
    private TableView<BookRackTm> tblBookrack;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtCategoryOfBooks;

    @FXML
    private TextArea txtNameOfBooks;
//  private BookRackDAO bookRackDAO = new BookRackDAOImpl();
    BookRackBO bookRackBO =  new BookRackBOImpl();

    public void initialize(){
        loadAllBookRacks();
        setCellValueFactory();
        tableListener();
        generateNextRackCode();
        //setQuantity;
    }

    private void generateNextRackCode() {
        try {
            String rackCode = bookRackBO.generateNextRackCode();
            txtCode.setText(rackCode);
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void tableListener() {
        tblBookrack.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(BookRackTm row) {
        if (row != null){
            txtCode.setText(row.getRackCode());
            txtQuantity.setText(String.valueOf(row.getQtyBooks()));
            txtCategoryOfBooks.setText(row.getCategoryOfBooks());
            txtNameOfBooks.setText(row.getNameOfBooks());
        }
    }
    @FXML
    void txtQuantityOfBooksOnAction(ActionEvent event) {
//        ArrayList<Integer> qty = new ArrayList<>();
//
//        for (Integer i : qty){
//
//        }
//        int qty += txtNameOfBooks;
//        int txtQuantity = qty;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextRackCode();

    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("rackCode"));
        colQuantityBooks.setCellValueFactory(new PropertyValueFactory<>("qtyBooks"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryOfBooks"));
        colNameOfBooks.setCellValueFactory(new PropertyValueFactory<>("nameOfBooks"));
    }

    private void loadAllBookRacks() {
        ObservableList<BookRackTm> obList = FXCollections.observableArrayList();

        try {
//          List<BookRackDto> rackList = bookRackDAO.getAll();
            List<BookRackDto> rackList = bookRackBO.getAllBookRack();

            for(BookRackDto dto : rackList){
                obList.add(
                        new BookRackTm(
                                dto.getRackCode(),
                                dto.getQtyBooks(),
                                dto.getCategoryOfBooks(),
                                dto.getNameOfBooks()

                ));
            }
            tblBookrack.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtCode.setText("");
        txtQuantity.setText("");
        txtCategoryOfBooks.setText("");
        txtNameOfBooks.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String rackCode = txtCode.getText();

        try {
            boolean isDeleted = bookRackBO.deleteBookRack(rackCode);

            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Deleting Successfully!!!").show();
                loadAllBookRacks();
                clearFields();
                generateNextRackCode();
            }else{
                new Alert(Alert.AlertType.ERROR,"not deleted!!!").show();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateBookRack();
        if (isValidate){
            String rackCode = txtCode.getText();
            int qtyBooks = Integer.parseInt(txtQuantity.getText());
            String categoryOfBooks = txtCategoryOfBooks.getText();
            String nameOfBooks = txtNameOfBooks.getText();

            var dto = new BookRackDto(rackCode,qtyBooks,categoryOfBooks,nameOfBooks);

            try {
                boolean isSaved = bookRackBO.saveBookRack(dto);
                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Book Rack Adding Successfully!!!").show();
                    clearFields();
                    loadAllBookRacks();
                    generateNextRackCode();
                    setCellValueFactory();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Book rack Adding failed!!!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

    }

    private boolean validateBookRack(){
        String rackCode = txtCode.getText();
        Pattern compile = Pattern.compile("[R][0-9]{3,}");
        Matcher matcher = compile.matcher(rackCode);
        boolean matches = matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Rack code invalid!!!").show();
            return  false;
        }

        String qtyBooks = txtQuantity.getText();
        boolean matches1 = Pattern.matches("[0-9]{1,}",qtyBooks);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"books quantity invalid!!!").show();
            return  false;
        }

        String categoryOfBooks = txtCategoryOfBooks.getText();
        boolean matches2 = Pattern.matches("[A-Za-z\\s]{2,}",categoryOfBooks);
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Books category invalid!!!").show();
            return  false;
        }

      return  true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String rackCode = txtCode.getText();
        int qtyBooks = Integer.parseInt(txtQuantity.getText());
        String categoryOfBooks = txtCategoryOfBooks.getText();
        String nameOfBooks = txtNameOfBooks.getText();

        var dto = new BookRackDto(rackCode,qtyBooks,categoryOfBooks,nameOfBooks);

        try {
            boolean isUpdated = bookRackBO.updateBookRack(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Updated successfully!!!").show();
                clearFields();
                loadAllBookRacks();
                generateNextRackCode();
            }else{
                new Alert(Alert.AlertType.ERROR,"not updated!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtCodeOnAction(ActionEvent event) {
        String rackCode = txtCode.getText();

        try {
//          BookRackDto dto = bookRackDAO.search(rackCode);
            BookRackDto dto = bookRackBO.searchBookRack(rackCode);


            if (dto != null){
                txtCode.setText(dto.getRackCode());
                txtQuantity.setText(String.valueOf(dto.getQtyBooks()));
                txtCategoryOfBooks.setText(dto.getCategoryOfBooks());
                txtNameOfBooks.setText(dto.getNameOfBooks());
            }else{
                new Alert(Alert.AlertType.ERROR,"not found!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
