package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.AuthorDto;

import java.sql.SQLException;
import java.util.List;

public interface AuthorBO {
    String getAuthorCount() throws SQLException;

    String generateNextAuthorId() throws SQLException;

    boolean saveAuthor(AuthorDto dto) throws SQLException;

    boolean updateAuthor(AuthorDto dto) throws SQLException;

    boolean deleteAuthor(String authorId) throws SQLException;

    AuthorDto searchAuthor(String authorId) throws SQLException;

    List<AuthorDto> getAllAuthors() throws SQLException;
}
