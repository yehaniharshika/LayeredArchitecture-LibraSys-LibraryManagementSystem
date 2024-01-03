package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.Impl.LoginDAOImpl;
import lk.ijse.LibraSys.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO{
    LoginDAO loginDAO = new LoginDAOImpl();
    @Override
    public boolean checkCredentials(String sNumber, String username, String pw) throws SQLException {
        return loginDAO.checkCredentials(sNumber,username,pw);
    }
}
