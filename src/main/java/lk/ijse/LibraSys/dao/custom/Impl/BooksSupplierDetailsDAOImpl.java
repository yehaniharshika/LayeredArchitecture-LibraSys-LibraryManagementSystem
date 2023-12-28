package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BooksSupplierDetailsDAOImpl {

    public  boolean saveBooksSupplierDetail(String supplierId,LocalDate supplierDate, List<SupplierCartTm> supplierCartTmList) throws SQLException {
        for (SupplierCartTm tm : supplierCartTmList){
            if (!saveBooksSupplierDetail(supplierId,supplierDate, tm)){
                return false;
            }
        }

        return true;
    }

    public boolean saveBooksSupplierDetail(String supplierId,LocalDate supplierDate,SupplierCartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        //PreparedStatement pstm = connection.prepareStatement("INSERT INTO booksSupplier_details VALUES(?,?,?,?) ");
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO booksSupplier_detail VALUES(?,?,?,?,?)");
        pstm.setString(1,supplierId);
        pstm.setString(2,tm.getISBN());
        pstm.setString(3,tm.getBookName());
        pstm.setInt(4,tm.getQty());
        pstm.setString(5, String.valueOf(supplierDate));

        return pstm.executeUpdate() > 0;
    }

   /* public boolean saveBooksSupplierDetail(BookSupplierDetailDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        //PreparedStatement pstm = connection.prepareStatement("INSERT INTO booksSupplier_details VALUES(?,?,?,?) ");
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO booksSupplier_detail VALUES(?,?,?,?,?)");
        pstm.setString(1, dto.getSupplierId());
        pstm.setString(2,tm.getISBN());
        pstm.setString(3,tm.getBookName());
        pstm.setInt(4,tm.getQty());
        pstm.setString(5, String.valueOf(supplierDate));


        return pstm.executeUpdate() > 0;
    }*/




}
