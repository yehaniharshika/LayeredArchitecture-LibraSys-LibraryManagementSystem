package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.SQLException;
import java.time.LocalDate;

public interface BooksSupplierDetailsDAO extends CrudDAO<BookSupplierDetailDto> {
    boolean saveBooksSupplierDetail(String supplierId, LocalDate supplierDate, SupplierCartTm tm) throws SQLException;
}
