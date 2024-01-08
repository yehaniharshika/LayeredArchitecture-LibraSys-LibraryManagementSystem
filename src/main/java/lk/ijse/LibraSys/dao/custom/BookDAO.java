package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookDAO extends CrudDAO<Book> {

    //transaction ekata
    boolean updateBooks(List<SupplierCartTm> supplierCartTmList) throws SQLException;

    boolean updateQty(String ISBN,int qtyOnHand) throws SQLException;

}
