package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.entity.BookSupplierDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BooksSupplierDetailsDAO extends CrudDAO<BookSupplierDetail> {
    boolean saveBooksSupplierDetail(String supplierId, LocalDate supplierDate, List<SupplierCartTm> tm) throws SQLException;
}
