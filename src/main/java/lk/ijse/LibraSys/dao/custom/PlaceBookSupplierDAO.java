package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.PlaceBooksSupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public interface PlaceBookSupplierDAO {
    boolean placeBooksOrder(PlaceBooksSupplierOrderDto palceBooksSupplierOrderDto) throws SQLException;
}
