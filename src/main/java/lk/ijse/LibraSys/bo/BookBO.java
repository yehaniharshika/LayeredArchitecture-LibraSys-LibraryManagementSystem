package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.dto.BookDto;
import java.sql.SQLException;
import java.util.List;

public interface BookBO {


    String generateNextBookISBN() throws SQLException;

    boolean saveBook(BookDto dto) throws SQLException;

    boolean updateBook(BookDto dto) throws SQLException;

    boolean deleteBook(String ISBN) throws SQLException;

    BookDto searchBook(String ISBN) throws SQLException;

    List<BookDto> getAllBooks() throws SQLException;

    //transaction ekata
    boolean updateBooks(List<SupplierCartTm> supplierCartTmList) throws SQLException;

    boolean updateQty(String ISBN,int qtyOnHand) throws SQLException;

    List<AuthorDto> getAllAuthors() throws SQLException;

    List<BookRackDto> getAllBookRack() throws SQLException;

    AuthorDto searchAuthor(String authorId) throws SQLException;

    BookRackDto searchBookRack(String rackCode) throws SQLException;
    boolean updateQtyBooks(String rackCode,int qtyBooks) throws SQLException;


}
