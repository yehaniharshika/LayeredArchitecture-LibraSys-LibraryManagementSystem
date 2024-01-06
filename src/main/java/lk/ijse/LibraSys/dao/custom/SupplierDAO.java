package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.SupplierDto;
import lk.ijse.LibraSys.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
//    String generateNextSupplierId() throws SQLException;
//
//    String getSupplierCount() throws SQLException;

    boolean saveSupplier(String supplierId,String supName,String contactNumber,String email) throws SQLException;

//    boolean updateSupplier(SupplierDto dto) throws SQLException;
//
//    boolean deleteSupplier(String supplierId) throws SQLException;
//
//    SupplierDto searchSupplier(String supplierId) throws SQLException;
//
//    List<SupplierDto> getAllSupplier() throws SQLException;

}
