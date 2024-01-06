package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.BookBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.entity.Author;
import lk.ijse.LibraSys.entity.Book;
import lk.ijse.LibraSys.entity.BookRack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO  = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    //AuthorDAO authorDAO = new AuthorDAOImpl();
    AuthorDAO authorDAO = (AuthorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.AUTHOR);
    BookRackDAO bookRackDAO = (BookRackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKRACK);

    @Override
    public String generateNextBookISBN() throws SQLException {
        return bookDAO.generateNextId();
    }

    @Override
    public boolean saveBook(BookDto dto) throws SQLException {
        return bookDAO.save(new Book(dto.getISBN(),
                dto.getBookName(),
                dto.getCategory(),
                dto.getQtyOnHand(),
                dto.getRackCode(),
                dto.getAuthorId())
        );
    }

    @Override
    public boolean updateBook(BookDto dto) throws SQLException {
        return bookDAO.update(new Book(dto.getISBN(),
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
        Book book = bookDAO.search(ISBN);

        if (book != null){
            return new BookDto(book);
        }
        return null;
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ArrayList<Book> allBooks = bookDAO.getAll();
        ArrayList<BookDto> bookDtos = new ArrayList<>();
//        List<BookDto> booksList =  bookDAO.getAll();

        for (Book book : allBooks){
            bookDtos.add(new BookDto(
                    book.getISBN(),
                    book.getBookName(),
                    book.getCategory(),
                    book.getQtyOnHand(),
                    book.getRackCode(),
                    book.getAuthorId())
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
        ArrayList<Author> allAuthors = authorDAO.getAll();
        ArrayList<AuthorDto> authorDtos = new ArrayList<>();
        //List<AuthorDto> authorsList = authorDAO.getAll();

        for (Author entity : allAuthors){
            authorDtos.add(new AuthorDto(entity.getAuthorId(),
                    entity.getAuthorName(),
                    entity.getText(),
                    entity.getNationality(),
                    entity.getCurrentlyBooksWrittenQty())
            );
        }
        return authorDtos;
    }

    @Override
    public List<BookRackDto> getAllBookRack() throws SQLException {
        List<BookRack> allBookRacks = bookRackDAO.getAll();
        ArrayList<BookRackDto> bookRackDtos = new ArrayList<>();

        for (BookRack bookRack :allBookRacks){
            bookRackDtos.add(new BookRackDto(
                    bookRack.getRackCode(),
                    bookRack.getQtyBooks(),
                    bookRack.getCategoryOfBooks(),
                    bookRack.getNameOfBooks())
            );
        }
        return bookRackDtos;
    }

    @Override
    public AuthorDto searchAuthor(String authorId) throws SQLException {
        Author author = authorDAO.search(authorId);

        if (author != null){
            return new AuthorDto(author);
        }else {
            return null;
        }
    }

    @Override
    public BookRackDto searchBookRack(String rackCode) throws SQLException {
        BookRack bookRack = bookRackDAO.search(rackCode);

        if (bookRack != null){
            return new BookRackDto(bookRack);
        }
        return null;
    }

    @Override
    public boolean updateQtyBooks(String rackCode, int qtyBooks) throws SQLException {
        return bookRackDAO.updateQtyBooks(rackCode,qtyBooks);
    }
}
