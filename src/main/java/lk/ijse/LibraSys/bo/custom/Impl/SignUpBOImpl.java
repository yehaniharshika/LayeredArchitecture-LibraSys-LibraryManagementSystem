package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.SignUpBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.Impl.SignupDAOImpl;
import lk.ijse.LibraSys.dao.custom.SignupDAO;
import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    //SignupDAO signupDAO = new SignupDAOImpl();
    SignupDAO signupDAO = (SignupDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);

    @Override
    public boolean registerLibrarian(SignupDto dto) throws SQLException {
        return signupDAO.registerLibrarian(new SignupDto(dto.getSNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEAddress(),
                dto.getUsername(),
                dto.getPw())
        );
    }
}
