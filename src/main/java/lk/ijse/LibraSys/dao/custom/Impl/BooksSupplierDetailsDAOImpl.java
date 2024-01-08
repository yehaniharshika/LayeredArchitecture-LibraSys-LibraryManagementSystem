package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BooksSupplierDetailsDAO;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.entity.BookSupplierDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    private boolean saveBooksSupplierDetail(String supplierId, LocalDate supplierDate, SupplierCartTm tm) throws SQLException {
        return SQLUtil.execute("INSERT INTO booksSupplier_detail VALUES(?,?,?,?,?)",
                supplierId,
                tm.getISBN(),
                tm.getBookName(),
                tm.getQty(),
                supplierDate
        );
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
    public boolean save(BookSupplierDetail entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BookSupplierDetail entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public BookSupplierDetail search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<BookSupplierDetail> getAll() throws SQLException {
        return null;
    }

}
