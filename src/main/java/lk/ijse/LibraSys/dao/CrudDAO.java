package lk.ijse.LibraSys.dao;

import lk.ijse.LibraSys.dto.BookDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    String getCount() throws SQLException;

    String generateNextId() throws SQLException;

    boolean save(T dto) throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    T search(String id) throws SQLException;

    List<T> getAll() throws SQLException;

}
