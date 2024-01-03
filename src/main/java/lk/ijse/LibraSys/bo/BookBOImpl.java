package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dao.custom.Impl.AuthorDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.BookDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.BookRackDAOImpl;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements  BookBO{
    BookDAO bookDAO  = new BookDAOImpl();
    AuthorDAO authorDAO = new AuthorDAOImpl();
    BookRackDAO bookRackDAO = new BookRackDAOImpl();

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

    @Override
    public List<AuthorDto> getAllAuthors() throws SQLException {
        ArrayList<AuthorDto> authorDtos = new ArrayList<>();
        List<AuthorDto> authorsList = authorDAO.getAll();

        for (AuthorDto dto : authorsList){
            authorDtos.add(new AuthorDto(dto.getAuthorId(), dto.getAuthorName(), dto.getText(), dto.getNationality(), dto.getCurrentlyBooksWrittenQty()));
        }
        return authorDtos;
    }

    @Override
    public List<BookRackDto> getAllBookRack() throws SQLException {
        ArrayList<BookRackDto> bookRackDtos = new ArrayList<>();
        List<BookRackDto> bookRackList = bookRackDAO.getAll();

        for (BookRackDto dto : bookRackList){
            bookRackDtos.add(new BookRackDto(dto.getRackCode(),
                    dto.getQtyBooks(),
                    dto.getCategoryOfBooks(),
                    dto.getNameOfBooks())
            );
        }
        return bookRackDtos;
    }

    @Override
    public AuthorDto searchAuthor(String authorId) throws SQLException {
        return authorDAO.search(authorId);
    }

    @Override
    public BookRackDto searchBookRack(String rackCode) throws SQLException {
        return bookRackDAO.search(rackCode);
    }

    @Override
    public boolean updateQtyBooks(String rackCode, int qtyBooks) throws SQLException {
        return bookRackDAO.updateQtyBooks(rackCode,qtyBooks);
    }
}
