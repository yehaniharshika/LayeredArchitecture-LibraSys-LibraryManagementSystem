package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.bo.SuperBO;
import lk.ijse.LibraSys.controller.MemberFormController;
import lk.ijse.LibraSys.dao.custom.Impl.*;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    String getBookCount() throws SQLException;
    //for dashboard update
    String getMemberCount() throws SQLException;
    String getSupplierCount() throws SQLException;
    String getAuthorCount() throws SQLException;
    String getBookBorrowCount() throws SQLException;

}
