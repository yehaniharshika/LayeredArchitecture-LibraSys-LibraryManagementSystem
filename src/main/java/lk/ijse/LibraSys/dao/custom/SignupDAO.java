package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SignupDAO {
    boolean registerLibrarian(SignupDto dto) throws SQLException;
}
