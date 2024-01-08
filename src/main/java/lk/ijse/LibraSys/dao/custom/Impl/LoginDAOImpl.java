package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.LoginDAO;
import lk.ijse.LibraSys.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAOImpl implements LoginDAO {
    @Override
    public  boolean checkCredentials(String sNumber,String  username,String pw) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM librarian WHERE sNumber =?  and username=?  and pw=?",sNumber,username,pw);

        String ServiceNumber = null;
        String UserName = null;
        String Password = null;

        while (resultSet.next()){
            ServiceNumber = resultSet.getString(1);
            UserName = resultSet.getString(6);
            Password = resultSet.getString(7);
        }
        if (sNumber.equals(ServiceNumber) && username.equals(UserName) && pw.equals(Password)){
            return  true;
        }

        return false;
    }
}
