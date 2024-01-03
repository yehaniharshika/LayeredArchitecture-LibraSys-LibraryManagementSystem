package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dao.custom.Impl.AuthorDAOImpl;
import lk.ijse.LibraSys.dto.AuthorDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorBOImpl implements AuthorBO{

    AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public String generateNextAuthorId() throws SQLException {
        return authorDAO.generateNextId();
    }

    @Override
    public boolean saveAuthor(AuthorDto dto) throws SQLException {
        return authorDAO.save(new AuthorDto(dto.getAuthorId(),
                dto.getAuthorName(),
                dto.getText(),
                dto.getNationality(),
                dto.getCurrentlyBooksWrittenQty())
        );
    }

    @Override
    public boolean updateAuthor(AuthorDto dto) throws SQLException {
        return authorDAO.update(new AuthorDto(dto.getAuthorId(),
                dto.getAuthorName(),
                dto.getText(),
                dto.getNationality(),
                dto.getCurrentlyBooksWrittenQty())
        );
    }

    @Override
    public boolean deleteAuthor(String authorId) throws SQLException {
        return authorDAO.delete(authorId);
    }

    @Override
    public AuthorDto searchAuthor(String authorId) throws SQLException {
        return authorDAO.search(authorId);
    }

    @Override
    public List<AuthorDto> getAllAuthors() throws SQLException {
        ArrayList<AuthorDto> authorDtos = new ArrayList<>();
        List<AuthorDto> authorsList = authorDAO.getAll();

        for (AuthorDto dto : authorsList){
            authorDtos.add(new AuthorDto(dto.getAuthorId(), dto.getAuthorName(), dto.getText(), dto.getNationality(), dto.getCurrentlyBooksWrittenQty()));
        }
        return authorDtos;
    }

}
