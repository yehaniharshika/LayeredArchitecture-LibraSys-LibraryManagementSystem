package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.bo.SuperBO;
import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    boolean registerLibrarian(SignupDto dto) throws SQLException;

}
