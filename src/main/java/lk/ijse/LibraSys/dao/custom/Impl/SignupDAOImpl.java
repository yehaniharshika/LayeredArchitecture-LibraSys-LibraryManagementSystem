package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.SignupDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SignupDAOImpl implements SignupDAO {

    @Override
    public  boolean registerLibrarian(SignupDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO librarian VALUES(?,?,?,?,?,?,?)",
                dto.getSNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEAddress(),
                dto.getUsername(),
                dto.getPw()
        );

   /*   Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO librarian VALUES(?,?,?,?,?,?,?)");
        pstm.setString(1, dto.getSNumber());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getNic());
        pstm.setString(5, dto.getEAddress());
        pstm.setString(6, dto.getUsername());
        pstm.setString(7, dto.getPw());

        boolean isRegistered = pstm.executeUpdate() > 0;

        return isRegistered;*/
    }

    @Override
    public String getCount() throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(SignupDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SignupDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public SignupDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<SignupDto> getAll() throws SQLException {
        return null;
    }
}
