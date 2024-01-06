package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.SignupDto;
import lk.ijse.LibraSys.entity.Signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SignupDAO extends CrudDAO<Signup> {
    boolean registerLibrarian(SignupDto dto) throws SQLException;
}
