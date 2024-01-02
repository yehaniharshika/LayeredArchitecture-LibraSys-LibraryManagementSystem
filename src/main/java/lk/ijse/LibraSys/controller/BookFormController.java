package lk.ijse.LibraSys.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.LibraSys.bo.*;
import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dao.custom.Impl.AuthorDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.BookDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.BookRackDAOImpl;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.dto.tm.BookTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookFormController {

    @FXML
    private JFXComboBox<String> cmbRackCode;

    @FXML
    private JFXComboBox<String> cmbAuthorId;


    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colISBN;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colAuthorId;

    @FXML
    private TableColumn<?, ?> colRackCode;


    @FXML
    private Label lblCategoryType;

    @FXML
    private Label lblAuthorName;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private AnchorPane Root;
    /*private BookRackDAOImpl bookRackModel = new BookRackDAOImpl();
    private BookDAOImpl bookModel = new BookDAOImpl();

    private AuthorDAOImpl authorModel = new AuthorDAOImpl();
    */
//  BookRackDAO bookRackDAO = new BookRackDAOImpl();
//  AuthorDAO authorDAO =  new AuthorDAOImpl();
//  BookDAO bookDAO = new BookDAOImpl();

    BookRackBO bookRackBO =  new BookRackBOImpl();
    AuthorBO authorBO = new AuthorBOImpl();
    BookBO bookBO =  new BookBOImpl();


    public void initialize(){
        generateNextBookISBN();
        loadRackCodes();
        loadAllBooks();
        loadAllAuthorIds();
        setCellValueFactory();
        tableListener();

    }

    private void tableListener() {
        tblBook.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(BookTm newValue) {
        if (newValue != null){
            txtISBN.setText(newValue.getISBN());
            txtBookName.setText(newValue.getBookName());
            txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
            txtCategory.setText(newValue.getCategory());
            cmbAuthorId.setValue(newValue.getAuthorId());
            cmbRackCode.setValue(newValue.getRackCode());
        }
    }


    private void generateNextBookISBN() {
        try {
            String ISBN = bookBO.generateNextBookISBN();
            txtISBN.setText(ISBN);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void setCellValueFactory() {
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colAuthorId.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        colRackCode.setCellValueFactory(new PropertyValueFactory<>("rackCode"));
    }


    private void loadAllBooks() {
        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        try {
//          List<BookDto> bookList = bookDAO.getAll();
            List<BookDto> bookList = bookBO.getAllBooks();


            for(BookDto dto : bookList){
                obList.add(new BookTm(
                        dto.getISBN(),
                        dto.getBookName(),
                        dto.getCategory(),
                        dto.getQtyOnHand(),
                        dto.getAuthorId(),
                        dto.getRackCode()
                ));
            }
            tblBook.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllAuthorIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<AuthorDto> authorDtos = authorBO.getAllAuthors();

            for (AuthorDto dto : authorDtos){
                obList.add(dto.getAuthorId());

            }

            cmbAuthorId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRackCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
//          List<BookRackDto> codeList  = bookRackDAO.getAll();
            List<BookRackDto> codeList  = bookRackBO.getAllBookRack();


            for (BookRackDto bookRackDto : codeList){
                obList.add(bookRackDto.getRackCode());
            }
            cmbRackCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextBookISBN();
    }

    private void clearFields() {
        txtISBN.setText("");
        txtBookName.setText("");
        txtCategory.setText("");
        txtQtyOnHand.setText("");
        if (cmbRackCode != null) {
            cmbRackCode.setValue("");
        }

        if (cmbAuthorId != null) {
            cmbAuthorId.setValue("");
        }
        lblAuthorName.setText("");
        lblCategoryType.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String ISBN = txtISBN.getText();

        try {
            boolean isDeleted = bookBO.deleteBook(ISBN);
            
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Book deleted Successfully!!!").show();
                loadAllBooks();
                clearFields();
                generateNextBookISBN();
            }else{
                new Alert(Alert.AlertType.ERROR,"not book deleted!!!").show();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void txtISBNOnAction(ActionEvent event) {
        String ISBN = txtISBN.getText();
        
        try {
//          BookDto dto = bookDAO.search(ISBN);
            BookDto dto = bookBO.searchBook(ISBN);


            if(dto != null){
                txtISBN.setText(dto.getISBN());
                txtBookName.setText(dto.getBookName());
                txtCategory.setText(dto.getCategory());
                txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
                cmbRackCode.setValue(dto.getRackCode());
                cmbAuthorId.setValue(dto.getAuthorId());
            }else{
                new Alert(Alert.AlertType.ERROR,"Book not found!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateBooks();
        if (isValidate){
            String ISBN = txtISBN.getText();
            String bookName = txtBookName.getText();
            String category = txtCategory.getText();
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
            String rackCode = cmbRackCode.getValue();
            String authorId = cmbAuthorId.getValue();

            var dto = new BookDto(ISBN,bookName,category,qtyOnHand,rackCode,authorId);

            try {
                boolean isSaved = bookBO.saveBook(dto);
                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Book saved successfully!!!").show();
                    clearFields();
                    loadAllBooks();
                    setCellValueFactory();
                    generateNextBookISBN();
                    bookRackBO.updateQtyBooks(rackCode, Integer.parseInt(String.valueOf(qtyOnHand)));
                    //bookRackModel.updatenameOfBooks(rackCode, bookName);
                }else{
                    new Alert(Alert.AlertType.ERROR,"ohh,Book not Saved!!!").show();
                }
            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                e.printStackTrace();
            }
        }
    }

    private  boolean validateBooks(){
        String ISBN = txtISBN.getText();
        Pattern compile = Pattern.compile("[B][0-9]{3,}");
        Matcher matcher = compile.matcher(ISBN);
        boolean matches = matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid book ID!!!").show();
            return  false;
        }

        String  bookName = txtBookName.getText();
        boolean matches1 = Pattern.matches("[A-Za-z\\s]{2,}", bookName);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid book name!!!").show();
            return  false;
        }

        String  category = txtCategory.getText();
        boolean matches2 = Pattern.matches("[A-Za-z\\s]{3,}" , category);
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid book category!!!").show();
            return false;
        }


        return  true;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String ISBN = txtISBN.getText();
        String bookName = txtBookName.getText();
        String category = txtCategory.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        String rackCode = cmbRackCode.getValue();
        String authorId = cmbAuthorId.getValue();

        var dto = new BookDto(ISBN,bookName,category,qtyOnHand,rackCode,authorId);

        try {
//          boolean isUpdated = bookDAO.update(dto);
            boolean isUpdated = bookBO.updateBook(dto);

            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"book updated successfully").show();
                clearFields();
                loadAllBooks();
                generateNextBookISBN();
            }else{
                new Alert(Alert.AlertType.ERROR,"book not updated!!!").show();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void cmbRackCodeOnAction(ActionEvent event) {
        String rackCode = cmbRackCode.getValue();

        try {
            BookRackDto bookRackDto = bookRackBO.searchBookRack(rackCode);
            if (bookRackDto != null){
                lblCategoryType.setText(bookRackDto.getCategoryOfBooks());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbAuthorIdOnAction(ActionEvent event) {
        String authorId = cmbAuthorId.getValue();

        try {
//          AuthorDto authorDto = authorDAO.search(authorId);
            AuthorDto authorDto = authorBO.searchAuthor(authorId);

            if (authorDto != null){
                lblAuthorName.setText(authorDto.getAuthorName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
