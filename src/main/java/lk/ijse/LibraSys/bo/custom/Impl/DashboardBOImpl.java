package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.DashboardBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.*;
import lk.ijse.LibraSys.dao.custom.Impl.*;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    MemberDAO memberDAO= (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    AuthorDAO authorDAO = (AuthorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.AUTHOR);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

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
