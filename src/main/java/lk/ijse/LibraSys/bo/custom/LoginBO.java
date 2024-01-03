package lk.ijse.LibraSys.bo.custom;

import java.sql.SQLException;

public interface LoginBO {
    boolean checkCredentials(String sNumber,String  username,String pw) throws SQLException;

}
