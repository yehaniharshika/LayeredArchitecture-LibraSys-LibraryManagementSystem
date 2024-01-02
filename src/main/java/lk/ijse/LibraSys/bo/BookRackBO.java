package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.BookRackDto;

import java.sql.SQLException;
import java.util.List;

public interface BookRackBO {
     String generateNextRackCode() throws SQLException;

    boolean saveBookRack(BookRackDto dto) throws SQLException;

    boolean updateBookRack(BookRackDto dto) throws SQLException;

    boolean deleteBookRack(String rackCode) throws SQLException;

    BookRackDto searchBookRack(String rackCode) throws SQLException;

    List<BookRackDto> getAllBookRack() throws SQLException;

    boolean updateQtyBooks(String rackCode,int qtyBooks) throws SQLException;
}
