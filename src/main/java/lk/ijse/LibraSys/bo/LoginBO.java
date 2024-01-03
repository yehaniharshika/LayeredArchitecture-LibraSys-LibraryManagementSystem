package lk.ijse.LibraSys.bo;

import java.sql.SQLException;

public interface LoginBO {
    boolean checkCredentials(String sNumber,String  username,String pw) throws SQLException;

}
