package lk.ijse.LibraSys.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.bo.BOFactory;
import lk.ijse.LibraSys.bo.custom.MemberBO;
import lk.ijse.LibraSys.bo.custom.Impl.MemberBOImpl;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.MembershipFeeDto;
//import lk.ijse.LibraSys.dto.SignupDto;
import lk.ijse.LibraSys.dto.tm.MemberTm;


import java.io.IOException;
//import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberFormController {

    @FXML
    private JFXComboBox<String> cmbmembershipFeeId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colMembername;

    @FXML
    private TableColumn<?, ?> colMid;

    @FXML
    private TableColumn<?, ?> coltelNumber;

    @FXML
    private TableColumn<?, ?> colEmailAddress;

    @FXML
    private TableColumn<?, ?> colIDNumber;

    @FXML
    private TableColumn<?, ?> colFeeId;

    @FXML
    private TableColumn<?, ?> colSNumber;


    @FXML
    private Label lblPaidDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<MemberTm> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtMid;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSnumber;

    @FXML
    private TextField txtIDNumber;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private TextField txtTel;

//  private ObservableList<MemberTm> obList = FXCollections.observableArrayList();
//  MemberDAO memberDAO = new MemberDAOImpl();

    MemberBO memberBO = (MemberBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBER);
    public  void initialize(){
        generateNextMemberId();
        loadFeeIds();
        loadAllMember();
        setCellValueFactory();
        tableListener();

    }

    private void tableListener() {
        tblMember.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(MemberTm row) {
        if (row != null){
            txtMid.setText(row.getMid());
            txtName.setText(row.getName());
            txtAddress.setText(row.getAddress());
            txtGender.setText(row.getGender());
            txtTel.setText(row.getTel());
            txtEmailAddress.setText(row.getEmailAddress());
            txtIDNumber.setText(row.getIDNumber());
            cmbmembershipFeeId.setValue(row.getFeeId());
            txtSnumber.setText(row.getSNumber());
        }

    }

    private void generateNextMemberId() {
        try {
//          String mid = memberDAO.generateNextId();
            String mid = memberBO.generateNextMemberId();
            txtMid.setText(mid);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colMid.setCellValueFactory(new PropertyValueFactory<>("mid"));
        colMembername.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coltelNumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        colIDNumber.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));
        colFeeId.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        colSNumber.setCellValueFactory(new PropertyValueFactory<>("sNumber"));
    }

    private void loadAllMember() {
        //var model =new MemberModel();
        ObservableList<MemberTm> obList = FXCollections.observableArrayList();

        try {
            List<MemberDto> memberList = memberBO.getAllMember();

            for (MemberDto dto : memberList){
                obList.add(new MemberTm(
                        dto.getMid(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getGender(),
                        dto.getTel(),
                        dto.getEmailAddress(),
                        dto.getIDNumber(),
                        dto.getFeeId(),
                        dto.getSNumber()


                ));

            }
            tblMember.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadFeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<MembershipFeeDto> idList = memberBO.getAllMemberShipFee();

            for (MembershipFeeDto dto : idList ) {
                obList.add(dto.getId());
            }
            cmbmembershipFeeId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextMemberId();
    }

    private void clearFields() {
        cmbmembershipFeeId.setValue("");
        txtSnumber.setText("");
        txtMid.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtGender.setText("");
        txtTel.setText("");
        txtEmailAddress.setText("");
        txtIDNumber.setText("");

    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String mid = txtMid.getText();

        try {
//          MemberDto dto = memberDAO.search(mid);
            MemberDto dto = memberBO.searchMember(mid);

            if (dto != null){
                txtMid.setText(dto.getMid());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtGender.setText(dto.getGender());
                txtTel.setText(dto.getTel());
                txtEmailAddress.setText(dto.getEmailAddress());
                txtIDNumber.setText(dto.getIDNumber());
                cmbmembershipFeeId.setValue(dto.getFeeId());
                txtSnumber.setText(dto.getSNumber());
            }else {
                new Alert(Alert.AlertType.ERROR,"Member not found!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String mid  = txtMid.getText();

        try {
            boolean isDeleted = memberBO.deleteMember(mid);
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"member deleted successfully!!!").show();
                loadAllMember();
                clearFields();
                generateNextMemberId();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnNewMembershipFeeOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/membershipFee_Form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Membership Fee Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateMember();
        if (isValidate){
            String mid  = txtMid.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String gender = txtGender.getText();
            String tel = txtTel.getText();
            String EmailAddress = txtEmailAddress.getText();
            String IDNumber = txtIDNumber.getText();
            String feeId = cmbmembershipFeeId.getValue();
            String sNumber = txtSnumber.getText();




            var dto = new MemberDto(mid,name,address,gender,tel,EmailAddress,IDNumber,feeId,sNumber);

            try {
//              boolean isSaved = memberDAO.save(dto);
                boolean isSaved = memberBO.saveMember(dto);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"member registered successfully!!!!!!").show();
                    clearFields();
                    loadAllMember();
                    setCellValueFactory();
                    generateNextMemberId();
                }else{
                    new Alert(Alert.AlertType.ERROR,"member registration failed!!!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

    }

    private  boolean validateMember(){
        String mid = txtMid.getText();
        Pattern compile = Pattern.compile("[M][0-9]{3,}");
        Matcher matcher = compile.matcher(mid);
        boolean matches = matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid member id").show();
            return  false;
        }

        String name =txtName.getText();
        boolean matches1 = Pattern.matches("[A-Za-z\\s]{3,}", name);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid member name!!!").show();
            return  false;
        }

        String address = txtAddress.getText();
        boolean matches2 = Pattern.matches("[0-9]{1,}\\/[A-Z]\\s[a-zA-Z]+$|[0-9]{1,}[/0-9]{1,}\\s([A-Za-z])\\w+",address);
        if(!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid address!!!").show();
            return  false;
        }

        String gender = txtGender.getText();
        boolean matches3 = Pattern.matches("Female|Male",gender);
        if(!matches3){
            new Alert(Alert.AlertType.ERROR,"Gender type invalid!!!").show();
            return  false;
        }

        String tel = txtTel.getText();
        boolean matches4 = Pattern.matches("(070|071|072|074|075|076|077|078|038)\\d{7}",tel);
        if(!matches4){
            new Alert(Alert.AlertType.ERROR,"Mobile number Invalid!!!").show();
            return  false;
        }

        String EmailAddress = txtEmailAddress.getText();
        boolean matches5 = Pattern.matches("[A-Za-z]{2,}@[A-Za-z]{2,}\\.[A-Za-z]{2,}|[a-zA-Z0-9]{2,}@[a-zA-Z]{2,}\\.[A-Za-z]{2,}|(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$",EmailAddress);
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Invalid email address!!!").show();
            return  false;

        }

        String IDNumber = txtIDNumber.getText();
        boolean matches6 = Pattern.matches("[0-9]{12}|[0-9]{9}[V]",IDNumber);
        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Invalid ID Number!!!").show();
            return false;
        }

       return  true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String mid  = txtMid.getText();
        String name =  txtName.getText();
        String address = txtAddress.getText();
        String gender = txtGender.getText();
        String tel = txtTel.getText();
        String EmailAddress = txtEmailAddress.getText();
        String IDNumber = txtIDNumber.getText();
        String feeId = cmbmembershipFeeId.getValue();
        String sNumber = txtSnumber.getText();


        var dto = new MemberDto(mid,name,address,gender,tel,EmailAddress,IDNumber,feeId,sNumber);

        try {
            boolean isUpdated = memberBO.updateMember(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"member updated successfully!!!").show();
                clearFields();
                loadAllMember();
                generateNextMemberId();
            }else{
                new  Alert(Alert.AlertType.ERROR,"not updated!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void cmbMembershipFeeOnAction(ActionEvent event) {
        String id = String.valueOf(cmbmembershipFeeId.getValue());
        try {
            MembershipFeeDto membershipFeeDto =memberBO.searchMembershipFee(id);
            if (membershipFeeDto != null){
                lblPaidDate.setText(String.valueOf(membershipFeeDto.getDate()));
                txtName.setText(membershipFeeDto.getName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_Form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.show();
    }*/
}
