package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.AuthorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AuthorDAO  extends CrudDAO<AuthorDto> {

     /*String getAuthorCount() throws SQLException;

     String generateNextAuthorId() throws SQLException;

     boolean saveAuthor(AuthorDto dto) throws SQLException;

     boolean updateAuthor(AuthorDto dto) throws SQLException;

     boolean deleteAuthor(String authorId) throws SQLException;

     AuthorDto searchAuthor(String authorId) throws SQLException;

     List<AuthorDto> getAllAuthors() throws SQLException;*/

}
