package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.PlaceBooksSupplierOrderBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.BooksSupplierDetailsDAO;
import lk.ijse.LibraSys.dao.custom.Impl.BookDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.BooksSupplierDetailsDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.SupplierDAOImpl;
import lk.ijse.LibraSys.dao.custom.SupplierDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.PlaceBooksSupplierOrderDto;
import lk.ijse.LibraSys.dto.SupplierDto;
import lk.ijse.LibraSys.entity.Book;
import lk.ijse.LibraSys.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceBooksSupplierOrderBOImpl implements PlaceBooksSupplierOrderBO {

//    SupplierDAO supplierDAO = new SupplierDAOImpl();
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    BooksSupplierDetailsDAO booksSupplierDetailsDAO = (BooksSupplierDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS_SUPPLIER_DETAILS);
    //BookSupplierDetailDto bookSupplierDetailDto = new BookSupplierDetailDto();

    @Override
    public  boolean placeBooksOrder(PlaceBooksSupplierOrderDto palceBooksSupplierOrderDto) throws SQLException {
        String supplierId = palceBooksSupplierOrderDto.getSupplierId();
        String supName = palceBooksSupplierOrderDto.getSupName();
        String contactNumber = palceBooksSupplierOrderDto.getContactNumber();
        String email = palceBooksSupplierOrderDto.getEmail();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);



            //man methana wenas kara
            boolean isSupplierSaved = supplierDAO.saveSupplier(supplierId,supName,contactNumber,email);

            if(isSupplierSaved){
                boolean isUpdated = bookDAO.updateBooks(palceBooksSupplierOrderDto.getSupplierCartTmList());

                if (isUpdated){

                    boolean isBooksSupplierDetailSaved = booksSupplierDetailsDAO.saveBooksSupplierDetail(palceBooksSupplierOrderDto.getSupplierId(),palceBooksSupplierOrderDto.getSupplierDate(),palceBooksSupplierOrderDto.getSupplierCartTmList());
                    if (isBooksSupplierDetailSaved){
                        connection.commit();
                    }else {
                        connection.rollback();
                    }
                }else{
                    connection.rollback();
                }

            }else{
                connection.rollback();
            }
            //connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public String generateNextSupplierId() throws SQLException {
        return supplierDAO.generateNextId();
    }


    @Override
    public boolean saveSupplier(String supplierId, String supName, String contactNumber, String email) throws SQLException {
        return supplierDAO.saveSupplier(supplierId,supName,contactNumber,email);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),
                dto.getSupplierName(),
                dto.getContactNumber(),
                dto.getEmail())
        );
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public SupplierDto searchSupplier(String supplierId) throws SQLException {
        Supplier supplier = supplierDAO.search(supplierId);

        if (supplier != null){
            return new SupplierDto(supplier);
        }
        return null;
    }

    @Override
    public List<SupplierDto> getAllSupplier() throws SQLException {
        List<Supplier> allSuppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : allSuppliers){
            supplierDtos.add(new SupplierDto(
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getContactNumber(),
                    supplier.getEmail())
            );
        }
        return supplierDtos;
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

}
