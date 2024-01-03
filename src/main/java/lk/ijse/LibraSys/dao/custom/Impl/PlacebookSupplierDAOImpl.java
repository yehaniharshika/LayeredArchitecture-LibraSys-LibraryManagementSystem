package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.BooksSupplierDetailsDAO;
import lk.ijse.LibraSys.dao.custom.PlaceBookSupplierDAO;
import lk.ijse.LibraSys.dao.custom.SupplierDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.PlaceBooksSupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacebookSupplierDAOImpl implements PlaceBookSupplierDAO {
    private SupplierDAO supplierDAO = new SupplierDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
    private BooksSupplierDetailsDAO booksSupplierDetailsDAO = new BooksSupplierDetailsDAOImpl();
    private BookSupplierDetailDto bookSupplierDetailDto = new BookSupplierDetailDto();

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
}
