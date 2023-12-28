package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.custom.PlaceBookSupplierDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookSupplierDetailDto;
import lk.ijse.LibraSys.dto.PlaceBooksSupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacebookSupplierDAOImpl implements PlaceBookSupplierDAO {
    private SupplierDAOImpl supplierModel = new SupplierDAOImpl();
    private BookDAOImpl bookModel = new BookDAOImpl();
    private BooksSupplierDetailsDAOImpl booksSupplierDetailModel = new BooksSupplierDetailsDAOImpl();
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
            boolean isSupplierSaved = supplierModel.saveSupplier(supplierId,supName,contactNumber,email);

            if(isSupplierSaved){
                boolean isUpdated = bookModel.updateBooks(palceBooksSupplierOrderDto.getSupplierCartTmList());

                if (isUpdated){

                    boolean isBooksSupplierDetailSaved = booksSupplierDetailModel.saveBooksSupplierDetail(palceBooksSupplierOrderDto.getSupplierId(),palceBooksSupplierOrderDto.getSupplierDate(),palceBooksSupplierOrderDto.getSupplierCartTmList());
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
