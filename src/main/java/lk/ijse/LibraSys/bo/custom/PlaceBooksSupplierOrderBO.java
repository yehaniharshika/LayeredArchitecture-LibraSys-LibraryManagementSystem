package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.bo.SuperBO;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.PlaceBooksSupplierOrderDto;
import lk.ijse.LibraSys.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceBooksSupplierOrderBO extends SuperBO {
    boolean placeBooksOrder(PlaceBooksSupplierOrderDto palceBooksSupplierOrderDto) throws SQLException;
    String generateNextSupplierId() throws SQLException;


    boolean saveSupplier(String supplierId,String supName,String contactNumber,String email) throws SQLException;

    boolean updateSupplier(SupplierDto dto) throws SQLException;

    boolean deleteSupplier(String supplierId) throws SQLException;

    SupplierDto searchSupplier(String supplierId) throws SQLException;

    List<SupplierDto> getAllSupplier() throws SQLException;

    BookDto searchBook(String ISBN) throws SQLException;

    List<BookDto> getAllBooks() throws SQLException;
}
