package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.controller.MemberFormController;
import lk.ijse.LibraSys.dao.custom.*;
import lk.ijse.LibraSys.dao.custom.Impl.*;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO{
    MemberDAO memberDAO= new MemberDAOImpl();

    ReservationDAO reservationDAO = new ReservationDAOImpl();
    BookDAO bookDAO = new BookDAOImpl();
    AuthorDAO authorDAO = new AuthorDAOImpl();
    SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public String getBookCount() throws SQLException {
        return bookDAO.getCount();
    }

    @Override
    public String getMemberCount() throws SQLException {
        return memberDAO.getCount();
    }

    @Override
    public String getSupplierCount() throws SQLException {
        return supplierDAO.getCount();
    }

    @Override
    public String getAuthorCount() throws SQLException {
        return authorDAO.getCount();
    }

    @Override
    public String getBookBorrowCount() throws SQLException {
        return reservationDAO.getCount();
    }
}
