package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.entity.BookRack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookRackDAO  extends CrudDAO<BookRack> {
//    String generateNextRackCode() throws SQLException;
//
//    boolean saveBookRack(BookRackDto dto) throws SQLException;
//
//    boolean updateBookRack(BookRackDto dto) throws SQLException;
//
//    boolean deleteBookRack(String rackCode) throws SQLException;
//
//    BookRackDto searchBookRack(String rackCode) throws SQLException;
//
//    List<BookRackDto> getAllBookRack() throws SQLException;

    boolean updateQtyBooks(String rackCode,int qtyBooks) throws SQLException;

    //boolean updatenameOfBooks(String rackCode,String nameOfBooks) throws SQLException;
}
