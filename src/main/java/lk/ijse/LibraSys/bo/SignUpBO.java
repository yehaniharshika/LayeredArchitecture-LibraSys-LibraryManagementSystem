package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.SQLException;

public interface SignUpBO {
    boolean registerLibrarian(SignupDto dto) throws SQLException;

}
