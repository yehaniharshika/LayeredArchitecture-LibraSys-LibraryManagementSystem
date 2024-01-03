package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    boolean checkCredentials(String sNumber,String  username,String pw) throws SQLException;

}
