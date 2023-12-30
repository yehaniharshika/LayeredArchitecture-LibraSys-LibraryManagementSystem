package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BooksSupplierDetailsDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BooksSupplierDetailsDAOImpl implements BooksSupplierDetailsDAO {

    public  boolean saveBooksSupplierDetail(String supplierId,LocalDate supplierDate, List<SupplierCartTm> supplierCartTmList) throws SQLException {
        for (SupplierCartTm tm : supplierCartTmList){
            if (!saveBooksSupplierDetail(supplierId,supplierDate, tm)){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean saveBooksSupplierDetail(String supplierId,LocalDate supplierDate,SupplierCartTm tm) throws SQLException {
        return SQLUtil.execute("INSERT INTO booksSupplier_detail VALUES(?,?,?,?,?)",
                supplierId,
                tm.getISBN(),
                tm.getBookName(),
                tm.getQty(),
                supplierDate
        );
      /*  Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO booksSupplier_detail VALUES(?,?,?,?,?)");
        pstm.setString(1,supplierId);
        pstm.setString(2,tm.getISBN());
        pstm.setString(3,tm.getBookName());
        pstm.setInt(4,tm.getQty());
        pstm.setString(5, String.valueOf(supplierDate));

        return pstm.executeUpdate() > 0;*/
    }

    @Override
    public String getCount() throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(BookSupplierDetailDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BookSupplierDetailDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public BookSupplierDetailDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<BookSupplierDetailDto> getAll() throws SQLException {
        return null;
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
