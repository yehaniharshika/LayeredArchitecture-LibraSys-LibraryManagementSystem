package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.SignupDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.SignupDto;
import lk.ijse.LibraSys.entity.Signup;

import java.sql.SQLException;
import java.util.ArrayList;

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
    public boolean save(Signup entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Signup entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Signup search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Signup> getAll() throws SQLException {
        return null;
    }
}
