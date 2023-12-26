package lk.ijse.LibraSys.dao;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SignupDAO {
    public  boolean registerLibrarian(SignupDto dto) throws SQLException;
}
