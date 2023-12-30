package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginDAO {
    boolean checkCredentials(String sNumber,String  username,String pw) throws SQLException;
}
