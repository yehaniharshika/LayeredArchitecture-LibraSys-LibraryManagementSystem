package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.LoginBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.Impl.LoginDAOImpl;
import lk.ijse.LibraSys.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public boolean checkCredentials(String sNumber, String username, String pw) throws SQLException {
        return loginDAO.checkCredentials(sNumber,username,pw);
    }
}
