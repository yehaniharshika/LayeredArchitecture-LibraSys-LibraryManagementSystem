package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.AuthorBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.entity.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorBOImpl implements AuthorBO {

    AuthorDAO authorDAO = (AuthorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.AUTHOR);

    @Override
    public String generateNextAuthorId() throws SQLException {
        return authorDAO.generateNextId();
    }

    @Override
    public boolean saveAuthor(AuthorDto dto) throws SQLException {
        return authorDAO.save(new Author(dto.getAuthorId(),
                dto.getAuthorName(),
                dto.getText(),
                dto.getNationality(),
                dto.getCurrentlyBooksWrittenQty())
        );
    }

    @Override
    public boolean updateAuthor(AuthorDto dto) throws SQLException {
        return authorDAO.update(new Author(dto.getAuthorId(),
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
        Author author = authorDAO.search(authorId);

        if (author != null){
            return new AuthorDto(author);
        }else {
            return null;
        }
    }

    @Override
    public List<AuthorDto> getAllAuthors() throws SQLException {
        ArrayList<Author> allAuthors = authorDAO.getAll();
        ArrayList<AuthorDto> authorDtos = new ArrayList<>();


        for (Author  author : allAuthors){
            authorDtos.add(new AuthorDto(author.getAuthorId(),
                    author.getAuthorName(),
                    author.getText(),
                    author.getNationality(),
                    author.getCurrentlyBooksWrittenQty())
            );
        }
        return authorDtos;
    }

}
