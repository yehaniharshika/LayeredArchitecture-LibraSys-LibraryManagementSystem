package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public String getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(authorId) FROM author");

        String Count = null;
        if (resultSet.next()){
            Count = resultSet.getString(1);
        }
        return  Count;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT authorId FROM author ORDER BY authorId DESC LIMIT 1;");

        if (resultSet.next()){

            return splitAuthorId(resultSet.getString("authorId"));
        }
        return splitAuthorId(null);

    }


    private String splitAuthorId(String currentAuthorId) {
        if(currentAuthorId != null) {
            String[] strings = currentAuthorId.split("A0");
            int authorid = Integer.parseInt(strings[1]);
            authorid++;
            String ID = String.valueOf(authorid);
            int length = ID.length();
            if (length < 2){
                return "A00"+authorid;
            }else {
                if (length < 3){
                    return "A0"+authorid;
                }else {
                    return "A"+authorid;
                }
            }
        }
        return "A001";
    }

    @Override
    public boolean save(Author entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO author VALUES (?,?,?,?,?)",
                entity.getAuthorId(),entity.getAuthorName(),entity.getText(),entity.getNationality(),entity.getCurrentlyBooksWrittenQty()
        );

    }

    @Override
    public  boolean update(Author entity) throws SQLException {

        return SQLUtil.execute("UPDATE author SET authorName=?,text=?,nationality=?,currentlyBooksWrittenQty=? WHERE authorId=?",
                entity.getAuthorName(),entity.getText(),entity.getNationality(),entity.getCurrentlyBooksWrittenQty(),entity.getAuthorId()
        );
    }

    @Override
    public boolean delete(String authorId) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  author WHERE authorId =?",authorId);

    }

    @Override
    public Author search(String authorId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM  author WHERE authorId=?",
                authorId
        );
        Author entity = null;
        if (resultSet.next()){
            entity = new Author(
                    resultSet.getString("authorId"),
                    resultSet.getString("authorName"),
                    resultSet.getString("text"),
                    resultSet.getString("nationality"),
                    resultSet.getInt("currentlyBooksWrittenQty")
                    );
        }
        return entity;

    }

    @Override
    public ArrayList<Author> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM author");
        ArrayList<Author> authorDtoList = new ArrayList<>();

        while (resultSet.next()){
            Author entity = new Author(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)

            );
            authorDtoList.add(entity);

        }
        return authorDtoList;

    }

}
