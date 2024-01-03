package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.controller.MemberFormController;
import lk.ijse.LibraSys.dao.custom.Impl.*;

import java.sql.SQLException;

public interface DashboardBO {
    String getBookCount() throws SQLException;
    //for dashboard update
    String getMemberCount() throws SQLException;
    String getSupplierCount() throws SQLException;
    String getAuthorCount() throws SQLException;
    String getBookBorrowCount() throws SQLException;

}
