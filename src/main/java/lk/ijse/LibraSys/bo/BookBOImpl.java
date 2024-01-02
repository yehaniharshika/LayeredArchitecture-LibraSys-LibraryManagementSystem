package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.Impl.BookDAOImpl;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements  BookBO{
    BookDAO bookDAO  = new BookDAOImpl();
    @Override
    public String getBookCount() throws SQLException {
        return bookDAO.getCount();
    }

    @Override
    public String generateNextBookISBN() throws SQLException {
        return bookDAO.generateNextId();
    }

    @Override
    public boolean saveBook(BookDto dto) throws SQLException {
        return bookDAO.save(new BookDto(dto.getISBN(),
                dto.getBookName(),
                dto.getCategory(),
                dto.getQtyOnHand(),
                dto.getRackCode(),
                dto.getAuthorId())
        );
    }

    @Override
    public boolean updateBook(BookDto dto) throws SQLException {
        return bookDAO.update(new BookDto(dto.getISBN(),
                dto.getBookName(),
                dto.getCategory(),
                dto.getQtyOnHand(),
                dto.getRackCode(),
                dto.getAuthorId())
        );
    }

    @Override
    public boolean deleteBook(String ISBN) throws SQLException {
        return bookDAO.delete(ISBN);
    }

    @Override
    public BookDto searchBook(String ISBN) throws SQLException {
        return bookDAO.search(ISBN);
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        List<BookDto> booksList =  bookDAO.getAll();

        for (BookDto dto : booksList){
            bookDtos.add(new BookDto(dto.getISBN(),
                    dto.getBookName(),
                    dto.getCategory(),
                    dto.getQtyOnHand(),
                    dto.getRackCode(),
                    dto.getAuthorId())
            );
        }
        return bookDtos;
    }

    @Override
    public boolean updateBooks(List<SupplierCartTm> supplierCartTmList) throws SQLException {
        return bookDAO.updateBooks(supplierCartTmList);
    }

    @Override
    public boolean updateQty(String ISBN, int qtyOnHand) throws SQLException {
        return bookDAO.updateQty(ISBN,qtyOnHand);
    }
}
